package cl.facele.robot.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cl.facele.robot.bean.RespaldoSiiBean;
import cl.facele.robot.conexion.ConexionChrome;
import cl.facele.robot.utilidades.Constantes;
import cl.facele.robot.utilidades.Utils;
import cl.facele.utiles.RUT;

/**
 * Clase logica que administra el generar el respaldo de documentos sii
 */
public class RespaldoSIILogica {

	private ConexionChrome conexion;
	private Map<String, RespaldoSiiBean> resultadoGeneral;
	private int RANGO_FOLIO = 20;

	/**
	 * Constructor de la clase respuesta sii 
	 */
	public RespaldoSIILogica()throws Exception{
		this.conexion = new ConexionChrome(Constantes.GENERAL_CONSTANTES.getString("URL.LOGIN"), 
				Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA"));
	}	

	/**
	 * Constructor de la clase respuesta sii 
	 */
	public RespaldoSIILogica(ConexionChrome conexion)throws Exception{
		this.conexion = conexion;
	}	



	/**
	 * Método que loguea en pagina de impuestos internos
	 */
	public void login() throws Exception {
		try {
			conexion.getDriver().findElement(By.cssSelector("#rutcntr")).sendKeys(Constantes.GENERAL_CONSTANTES.getString("USER.LOGIN"));
			conexion.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			conexion.getDriver().findElement(By.cssSelector("#clave")).sendKeys(Constantes.GENERAL_CONSTANTES.getString("PASS.LOGIN"));
			conexion.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			conexion.getDriver().findElement(By.xpath("//button[contains(.,'Ingresar')]") ).click();	

		} catch (Exception e) {
			throw new Exception("Error Login : " + e.getMessage(), e);
		} 
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void generarRespaldo(String rutAbonado, String proceso) throws Exception {
		try {
			RUT rut = new RUT(rutAbonado);
			conexion.getDriver().navigate().to(Constantes.GENERAL_CONSTANTES.getString("URL.RESPALDO.RUT"));

			conexion.getDriver().findElement(By.xpath("//input[@name='RUT_EMP']")).sendKeys(rut.getPure());
			conexion.getDriver().findElement(By.xpath("//input[@name='DV_EMP']")).sendKeys(rut.getSDigito());
			conexion.getDriver().findElement(By.xpath("//input[@value='Enviar']") ).click();
			Thread.sleep(2000);

			resultadoGeneral = new HashMap<String, RespaldoSiiBean>();
			List<RespaldoSiiBean> tipoDocumentos = obtenerSelectLista("combo");

			marcarComboBox(proceso, "sel_origen");
			for(int x = 1; x < tipoDocumentos.size(); x++) {
				marcarComboBox(tipoDocumentos.get(x).getTexto(), "combo");
				RespaldoSiiBean bean = new RespaldoSiiBean();
				bean.setFolioDesde(0L);
				bean.setFolioHasta(9999999999L);
				bean.setDocumentoOrigen(proceso);
				bean.setTipoDocumento(tipoDocumentos.get(x).getTexto());
				bean.setTexto(proceso+"_"+tipoDocumentos.get(x).getTexto());
				resultadoGeneral.put(bean.getTexto(), bean);

				procesarDocumento(bean);
			}
			ViewVentanaInformacion.abrirVentana();
			ViewVentanaInformacion.setInformacion(resultadoGeneral);
		} catch (Exception e) {
			throw new Exception("Error redirect Page : " + e.getMessage(), e);
		} 
	}



	/**
	 * Metodo que respalda todos los documentos Emitidos por medio del Número de Folio
	 */
	private void procesarDocumento(RespaldoSiiBean beanFolio) throws Exception {
		Boolean proceso = false;
		Long cantidad = 0L;

		conexion.getDriver().findElement(By.id("folio")).clear();
		conexion.getDriver().findElement(By.id("folioHasta")).clear();
		conexion.getDriver().findElement(By.id("folio")).sendKeys(beanFolio.getFolioDesde().toString());
		conexion.getDriver().findElement(By.id("folioHasta")).sendKeys(beanFolio.getFolioHasta().toString());
		conexion.getDriver().findElement(By.xpath("//input[@name='BTN_SUBMIT']")).click();

		cantidad = (long) Utils.validarCantidadConsulta(conexion.getDriver().findElement(By.id("num_docs")).getText());
		if(beanFolio.getFolioDesde()== 0L && beanFolio.getFolioHasta()==9999999999L)
			resultadoGeneral.get(beanFolio.getTexto()).setTotalFolios(cantidad.toString());

		if(cantidad > 0 && cantidad <= RANGO_FOLIO)
			proceso = true;

		if(cantidad > RANGO_FOLIO) {
			if(beanFolio.getFolioDesde() == beanFolio.getFolioHasta()) {
				beanFolio.setFoliosSinProcesar((beanFolio.getFoliosSinProcesar()==null? "":",") + beanFolio.getFolioDesde());
				if(resultadoGeneral.get(beanFolio.getTexto()+"_POR_PROCESAR")==null) 
					resultadoGeneral.put(beanFolio.getTexto()+"_POR_PROCESAR", beanFolio);
				else
					resultadoGeneral.get(beanFolio.getTexto()+"_POR_PROCESAR")
					.setFoliosSinProcesar(beanFolio.getFoliosSinProcesar());
				return;
			}

			List<RespaldoSiiBean> listFolios = Utils.dividirFolios(beanFolio);

			if(listFolios != null)
				for(RespaldoSiiBean bean: listFolios){
					bean.setTexto(beanFolio.getTexto());
					procesarDocumento(bean);
				}
		} 
		if(!proceso) 
			return;
		Long cantidadFolio = resultadoGeneral.get(beanFolio.getTexto()).getCantidadFolios();
		resultadoGeneral.get(beanFolio.getTexto()).setCantidadFolios(cantidadFolio + cantidad);
		conexion.getDriver().findElement(By.id("btn_respaldar")).click();
		Thread.sleep(4000);
	}

	/**
	 * Metodo que obtiene lista de cualquier combobox segun parametro 
	 */
	private List<RespaldoSiiBean> obtenerSelectLista(String comboBox){
		List<RespaldoSiiBean> listaRespaldo = new ArrayList<RespaldoSiiBean>();
		Select elementOptions = new Select(conexion.getDriver().findElement(By.id(comboBox)));
		for(WebElement element: elementOptions.getOptions()){
			RespaldoSiiBean respaldo = new RespaldoSiiBean();
			respaldo.setTexto(element.getText());
			listaRespaldo.add(respaldo);
		}
		return listaRespaldo;
	}

	/**
	 * 
	 * @param texto
	 */
	private void marcarComboBox(String texto, String idSelect){
		Select options = new Select(conexion.getDriver().findElement(By.id(idSelect)));
		options.selectByVisibleText(texto);
	}

	/**
	 * metodo el cual cierra conexion de webdriver chrome
	 */
	public void cerrar(){
		this.conexion.quit();
	}

}
