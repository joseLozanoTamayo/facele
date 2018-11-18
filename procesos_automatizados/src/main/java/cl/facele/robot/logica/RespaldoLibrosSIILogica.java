package cl.facele.robot.logica;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cl.facele.robot.conexion.ConexionChrome;
import cl.facele.robot.utilidades.Constantes;
import cl.facele.utiles.RUT;

/**
 * Clase logica que administra el generar el respaldo de documentos sii
 */
public class RespaldoLibrosSIILogica {

	private ConexionChrome conexion;
	
	/**
	 * Constructor de la clase respuesta sii 
	 */
	public RespaldoLibrosSIILogica() throws Exception {
		this.conexion = new ConexionChrome(Constantes.GENERAL_CONSTANTES.getString("URL.LOGIN"), 
				Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.LIBROS"));
	}
	
	/**
	 * Constructor de la clase respuesta sii 
	 */
	public RespaldoLibrosSIILogica(ConexionChrome conexion) throws Exception {
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
	public void generarRespaldo(String rutAbonado) throws Exception{
		try {
			RUT rut = new RUT(rutAbonado);
			conexion.getDriver().navigate().to(Constantes.GENERAL_CONSTANTES.getString("URL.RESPALDO.RUT"));

			conexion.getDriver().findElement(By.xpath("//input[@name='RUT_EMP']")).sendKeys(rut.getPure());
			conexion.getDriver().findElement(By.xpath("//input[@name='DV_EMP']")).sendKeys(rut.getSDigito());
			conexion.getDriver().findElement(By.xpath("//input[@value='iecv']")).click();
			conexion.getDriver().findElement(By.xpath("//input[@value='Enviar']") ).click();
			Thread.sleep(2000);

			procesarTablaLibro();
			
		} catch (Exception e) {
			throw new Exception("Error generarReslpado : " + e.getMessage(), e);
		} 
	}
	
	
	/**
	 * Método recursivo
	 */
	private void procesarTablaLibro() throws Exception{
		String paginas[] =  conexion.getDriver().findElement(By.className("KnockoutFooterTD")).getText().split("de");
		int totalPag = Integer.parseInt(paginas[1].trim());
		List<String> listaDato = new ArrayList<String>();
		try {
			for(int pag = 1; pag <= totalPag; pag++) {
				List<WebElement> tr_coleccion = conexion.getDriver().findElements(By.xpath("//table[@class='KnockoutFormTABLE']/tbody/tr"));
				for(int idx = 0; idx < tr_coleccion.size(); idx++){
						List<WebElement> columnas = tr_coleccion.get(idx).findElements(By.tagName("td"));
						if(!columnas.get(4).getText().equals("Libro En Ingreso")) {
							listaDato.add(columnas.get(1).getText()+"-"+columnas.get(2).getText());
							columnas.get(0).findElement(By.tagName("a")).click();
						}	
						Thread.sleep(4000);
				}
				if(pag < totalPag){
					if(pag==1)
						conexion.getDriver().findElement(By.xpath("//table[@class='KnockoutFormTABLE']/tfoot/tr/td/a") ).click();
					else
						conexion.getDriver().findElement(By.xpath("//table[@class='KnockoutFormTABLE']/tfoot/tr/td/a[3]") ).click();
				}
				Thread.sleep(3000);
			}
		}catch (Exception e) {
			throw new Exception("Error en procesarTablaFolio : " + e.getMessage(), e);
		} 
	}

	/**
	 * metodo el cual cierra conexion de webdriver chrome
	 */
	public void cerrar(){
		this.conexion.quit();
	}
	
	
	
	

}
