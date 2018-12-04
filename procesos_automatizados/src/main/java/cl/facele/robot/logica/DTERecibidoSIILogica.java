package cl.facele.robot.logica;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cl.facele.robot.conexion.ConexionChrome;
import cl.facele.robot.utilidades.Constantes;
import cl.facele.sfe.cargapropiedadescontribuyente.CargaPropiedadesContribuyente;
import cl.facele.sfe.cargapropiedadescontribuyente.CargaPropiedadesContribuyente_Service;
import cl.facele.sfe.cargapropiedadescontribuyente.Consultar;
import cl.facele.sfe.cargapropiedadescontribuyente.ConsultarResponse;
import cl.facele.sfe.cargapropiedadescontribuyente.ContribuyenteType;
import cl.facele.sfe.cargapropiedadescontribuyente.EstadoType;
import cl.facele.sfe.cargapropiedadescontribuyente.Obtener;
import cl.facele.sfe.cargapropiedadescontribuyente.ObtenerResponse;
import cl.facele.sfe.recepciondte.RecepcionDTE;
import cl.facele.sfe.recepciondte.RecepcionDTE_Service;
import cl.facele.sfe.recepciondte.UpLoadRegistroSII;
import cl.facele.sfe.recepciondte.UpLoadRegistroSIIResponse;
import cl.facele.utiles.FormatoFecha;
import cl.facele.utiles.RUT;

/**
 * Clase logica que administra el generar el respaldo de documentos sii
 */
public class DTERecibidoSIILogica {

	private ConexionChrome conexion;
	private CargaPropiedadesContribuyente portCargaContribuyente;
	private RecepcionDTE portRecepcionDTE;
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * Constructor de la clase respuesta sii 
	 */
	public DTERecibidoSIILogica() throws Exception {
		this.conexion = new ConexionChrome(Constantes.GENERAL_CONSTANTES.getString("URL.LOGIN"), 
				Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA"));
	}

	/**
	 * Constructor de la clase respuesta sii 
	 */
	public DTERecibidoSIILogica(ConexionChrome conexion, CargaPropiedadesContribuyente_Service cargaContribuyente, RecepcionDTE_Service recepcionDteService) throws Exception {
		this.conexion = conexion;
		this.portCargaContribuyente = cargaContribuyente.getCargaPropiedadesContribuyenteService();
		this.portRecepcionDTE = recepcionDteService.getRecepcionDTESOAP();
	}	

	/**
	 * metodo el cual consulta los contribuyentes con parametro de estado y offset.
	 */
	public ConsultarResponse consultarContribuyente(String estado, int offset) throws Exception {
		try{
			Consultar parameters = new Consultar();
			parameters.setEstado(EstadoType.fromValue(estado));
			parameters.setOffset(offset);
			ConsultarResponse response = portCargaContribuyente.consultar(parameters);
			return response;
		}catch(Exception e){
			throw new Exception("Error consulta Contribuyente : " +e.getMessage(), e);
		} 
	}

	/**
	 * consulta contribuyente si existe para determinado ambiente por medio del rut
	 **/
	public ObtenerResponse obtenerContribuyente(String rut) throws Exception{
		try {
			Obtener parameters = new Obtener();
			parameters.setRutContribuyente(rut);
			ObtenerResponse response = portCargaContribuyente.obtener(parameters );
			return response;
		} catch (Exception e) {
			throw new Exception("Error obteniendo Contribuyente", e);
		}
	}

	/**
	 * 
	 * Metod que ingresa valor del rut para luego redireccionar 
	 * al modulo que descarga los dte recibidos
	 * 
	 */
	public void iniciarRespaldo( Date fechaConsulta, Date periodoConsulta, List<ContribuyenteType> registrosContribuyente ) throws Exception{
		
		logger.info(" INGRESANDO iniciarReslpadoDTE " );
		RUT rut = null;
		final boolean REINTENTAR_DESCARGA = true;
		Map<String, String> respaldoError = new HashMap<String, String>();
		
		
		for(ContribuyenteType contribuyente: registrosContribuyente) {
			try {
				rut = new RUT(contribuyente.getRut());
				
				logger.info(" RUT A CONSULTAR: " + rut.toString() +" RAZON SOCIAL : " + contribuyente.getRazonSocial());
				ViewVentanaInformacion.setInformacion("RUT : " + rut.toString() +" RAZON SOCIAL : " + contribuyente.getRazonSocial() );
				String fecha = fechaConsulta != null ? FormatoFecha.convertFecha(fechaConsulta) :  FormatoFecha.convertPeriodo(periodoConsulta);
				ViewVentanaInformacion.setInformacion("Fecha o Periodo Descarga (DTE): " + fecha );
				
				conexion.getDriver().navigate().to(Constantes.GENERAL_CONSTANTES.getString("URL.DTE.RECIBIDO"));
				conexion.getDriver().findElement(By.xpath("//input[@name='RUT_EMPRESA']")).sendKeys(contribuyente.getRut().split("-")[0]);
				conexion.getDriver().findElement(By.xpath("//input[@name='DV_EMPRESA']")).sendKeys(contribuyente.getRut().split("-")[1]);
				conexion.getDriver().findElement(By.xpath("//input[@value='Consultar']") ).click();
				
				Thread.sleep(2000);
				
				boolean isProceso = false;
				
				if( fechaConsulta != null )
					isProceso = procesarFechaDTE(fechaConsulta);
				else
					isProceso = procesarPeriodoDTE(periodoConsulta);
				
				if(isProceso) {
					
					String csv = procesarFileCsv(contribuyente.getRut());
					UpLoadRegistroSIIResponse response = uploadRegistroSII(contribuyente.getRut(), csv);
					
					logger.info("RESULTADO (uploadRegistroSII) - Estado : " + response.getEstadoProcesamiento()
					+ " Glosa : " + response.getGlosaProcesamiento());
					ViewVentanaInformacion.setInformacion("Proceso Carga Registro DTE: " + response.getEstadoProcesamiento()
					+ " \n Glosa :" + response.getGlosaProcesamiento());
					
					
					if( response.getEstadoProcesamiento() == 0 
							&& response.getGlosaProcesamiento().contains("incorrecto") ) {
						ViewVentanaInformacion.setInformacion("Reintento Carga Registro SII");
					
						waitDownload(REINTENTAR_DESCARGA);
						csv = procesarFileCsv(rut.toString());
						response = uploadRegistroSII(rut.toString(), csv);
						logger.info("RESULTADO REINTENTO (Registro SII) - Estado : " + response.getEstadoProcesamiento()
							+ " Glosa : " + response.getGlosaProcesamiento());
						ViewVentanaInformacion.setInformacion("RESULTADO REINTENTO (Registro SII) - Estado " + response.getEstadoProcesamiento()
							+ " \n Glosa :" + response.getGlosaProcesamiento());
					}
					
					if( response.getEstadoProcesamiento() == 0 )
						throw new Exception(" Error, UPLOAD : " + response.getEstadoProcesamiento()
								+ " Glosa : " + response.getGlosaProcesamiento());
				} else 
					ViewVentanaInformacion.setInformacion("Perido no Encontrado.\nProceso Respaldo, no Realizado. ");
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				ViewVentanaInformacion.setInformacion("Proceso Respaldo, no Realizado. ");
				if( e.getMessage() != null && e.getMessage().contains("no such element:"))
					respaldoError.put(rut.toString(), "RUT : " + rut.toString() + " INFORMACION : Elementos no encontrado, para Descargar" );
				else	
					respaldoError.put(rut.toString(), "RUT : " + rut.toString() + " INFORMACION : " +e.getMessage());
				logger.error("Error iniciarReslpadoDTE : " + e.getMessage());
			} 
			Thread.sleep(2000);
			ViewVentanaInformacion.setInformacion("");
		}
		logger.info(" FINALIZANDO iniciarReslpadoDTE " );
		ViewVentanaInformacion.setInformacion("Finaliza Proceso Ambiente");
		ViewVentanaInformacion.setInformacionError(respaldoError);
	}

	/**
	 * 
	 * Metod que ingresa valor del rut para luego redireccionar 
	 * al modulo que descarga los dte recibidos
	 * 
	 */
	public void iniciarRespaldoGeneral( List<ContribuyenteType> registrosContribuyente ) throws Exception{
		logger.info("INGRESANDO iniciarReslpadoDTEGeneral sin fecha" );
		Map<String, String> respaldoError = new HashMap<String, String>();
		final boolean REINTENTAR_DESCARGA = true; 
		RUT rut = null;
		
		for(ContribuyenteType contribuyente: registrosContribuyente) {
			try {
				rut = new RUT(contribuyente.getRut());
				
				logger.info(" .RUT A CONSULTAR: " + rut.toString() +" .RAZON SOCIAL : " + contribuyente.getRazonSocial());
				
				ViewVentanaInformacion.setInformacion(" RUT : " + rut.toString() +" RAZON SOCIAL : " + contribuyente.getRazonSocial());
				
				conexion.getDriver().navigate().to(Constantes.GENERAL_CONSTANTES.getString("URL.DTE.RECIBIDO"));
				conexion.getDriver().findElement(By.xpath("//input[@name='RUT_EMPRESA']")).sendKeys(contribuyente.getRut().split("-")[0]);
				conexion.getDriver().findElement(By.xpath("//input[@name='DV_EMPRESA']")).sendKeys(contribuyente.getRut().split("-")[1]);
				conexion.getDriver().findElement(By.xpath("//input[@value='Consultar']") ).click();
				
				Thread.sleep(2000);

				List<WebElement> tr_coleccion = conexion.getDriver().findElements(By.xpath("//table[@class='datos']/tbody/tr"));
				for(int indiceRegistro = 1; indiceRegistro < tr_coleccion.size(); indiceRegistro++) {
					String fechaTabla = null; 
					try {
						List<WebElement> columnas = tr_coleccion.get(indiceRegistro).findElements(By.tagName("td"));
						fechaTabla = columnas.get(1).getText();
						columnas.get(0).findElement(By.tagName("input")).click();
						Thread.sleep(1000);
					} catch (Exception e) {
						ViewVentanaInformacion.setInformacion("Fecha Descarga (DTE): " + fechaTabla
								+ "No Realizada. Error : " + e.getMessage());
					} 
				}

				logger.info("SE PROCEDE A DESCARGAR ARCHIVO. ");
				conexion.getDriver().findElement(By.id("XML")).click();
				
				waitDownload(REINTENTAR_DESCARGA);
				String csv = procesarFileCsv(contribuyente.getRut());
				UpLoadRegistroSIIResponse response = uploadRegistroSII(contribuyente.getRut(), csv);
				
				logger.info("RESULTADO (uploadRegistroSII) - Estado : " + response.getEstadoProcesamiento()
				+ " Glosa : " + response.getGlosaProcesamiento());
				ViewVentanaInformacion.setInformacion("Carga Registro SII: " + response.getEstadoProcesamiento()
				+ " \n Glosa :" + response.getGlosaProcesamiento());

				if( response.getEstadoProcesamiento() == 0 
						&& response.getGlosaProcesamiento().contains("incorrecto") ) {
					ViewVentanaInformacion.setInformacion("Reintento Carga Registro SII");
				
					waitDownload(REINTENTAR_DESCARGA);
					csv = procesarFileCsv(rut.toString());
					response = uploadRegistroSII(rut.toString(), csv);
					
					logger.info("RESULTADO REINTENTO (Registro SII) - Estado : " + response.getEstadoProcesamiento()
						+ " Glosa : " + response.getGlosaProcesamiento());
					ViewVentanaInformacion.setInformacion("RESULTADO REINTENTO (Registro SII) - Estado " + response.getEstadoProcesamiento()
						+ " \n Glosa :" + response.getGlosaProcesamiento());
				}
				
				if( response.getEstadoProcesamiento() == 0 )
					throw new Exception("Error, UPLOAD : " + response.getEstadoProcesamiento()
							+ " Glosa : " + response.getGlosaProcesamiento());
				
			} catch (Exception e) {
				ViewVentanaInformacion.setInformacion("Proceso Respaldo, no Realizado. ");
				if( e.getMessage() != null && e.getMessage().contains("no such element:"))
					respaldoError.put(rut.toString(), "RUT : " + rut.toString() + " INFORMACION : Elementos no encontrado, para Descargar" );
				else	
					respaldoError.put(rut.toString(), "RUT : " + rut.toString() + " INFORMACION : " +e.getMessage());
				logger.error("Error iniciarReslpadoDTE : " + e.getMessage());
			
			} finally {
				eliminarFileCsv();
			}
			Thread.sleep(2000);
			ViewVentanaInformacion.setInformacion("");
		}
		logger.info(" FINALIZANDO iniciarReslpadoDTE " );
		ViewVentanaInformacion.setInformacion("Finaliza Proceso Respaldo");
		ViewVentanaInformacion.setInformacionError(respaldoError);
	}
	
	/**
	 * Metodo que sube archivo csv al sistema.
	 */
	public UpLoadRegistroSIIResponse uploadRegistroSII(String contribuyente, String csvFile) throws Exception {
		UpLoadRegistroSII parameters = new UpLoadRegistroSII();
		UpLoadRegistroSIIResponse response = null;
		try {
			
			logger.info("Constribuyente a upload " + contribuyente);
			parameters.setRutReceptor(contribuyente);
			parameters.setContenidoCSV(csvFile);
			response = portRecepcionDTE.upLoadRegistroSII(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * espera por la descarga que se esta realizando
	 */
	private void waitDownload(boolean reintentoDescarga) throws Exception {
		Path rutaDescarga = Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA"));
		final boolean REINTENTAR_DESCARGA = false; 
		
		boolean wait = true;
		long second = 180; 
		long milisegundosNow = System.currentTimeMillis();
		
		while(wait) {
			for(File file: rutaDescarga.toFile().listFiles()){
				if(!file.getName().contains(".crdownload")) {
					wait = false;
					break;
				}
			}
			
			if(rutaDescarga.toFile().listFiles().length == 0 && wait) {
				if( reintentoDescarga ) {
					logger.info("Reintento de Descarga DTE");
					ViewVentanaInformacion.setInformacion("Reintento Descarga DTE");
					conexion.getDriver().findElement(By.id("XML")).click();
					Thread.sleep(3000);
					waitDownload(REINTENTAR_DESCARGA);
					return;
				}
				throw new Exception("Error, al descargar archivo.");
			}
			
			Thread.sleep(1000);
			long secondNow = (System.currentTimeMillis() - milisegundosNow);
			if( TimeUnit.MILLISECONDS.toSeconds(secondNow) > second) {
				throw new Exception("Error: Descarga a superado limite de tiempo descarga ");
			}
		}
		TimeUnit.SECONDS.sleep(1);
	}
	
	/**
	 * Método que obtiene el documento recibido ante sii
	 */
	private boolean procesarFechaDTE(Date fecha) throws Exception {
		boolean isRegistro = false;
		final boolean REINTENTAR_DESCARGA = false;
		try {
			List<WebElement> tr_coleccion = conexion.getDriver().findElements(By.xpath("//table[@class='datos']/tbody/tr"));

			if(tr_coleccion.size() == 0) {
				logger.info(conexion.getDriver().findElement(By.tagName("body")).getText());
				String informacionPagina = conexion.getDriver().findElement(By.tagName("body")).getText(); 
				ViewVentanaInformacion.setInformacion(informacionPagina);
				throw new Exception(informacionPagina);
			}
			
			for(int registroDTE = 1; registroDTE < tr_coleccion.size(); registroDTE++) {
				
				List<WebElement> columnas = tr_coleccion.get(registroDTE).findElements(By.tagName("td"));
				String fechaLista = columnas.get(1).getText();

				if((fecha.after(FormatoFecha.convertFecha(fechaLista))
						&& !FormatoFecha.convertFecha(fecha).equals(fechaLista))) {
					ViewVentanaInformacion.setInformacion("Fecha a Descargar no Existe en Lista Recibidos");
					throw new Exception("Fecha a Descargar no Existe en Lista Recibidos");
				}

				if(fechaLista.equals(FormatoFecha.convertFecha(fecha))) {
					logger.info("ENCONTRO REGISTRO DTE CON FECHA : " + FormatoFecha.convertFecha(fecha));
					logger.info("SE PROCEDE A DESCARGAR ARCHIVO ");
					columnas.get(0).findElement(By.tagName("input")).click();
					isRegistro = true;
					break;
				}
				Thread.sleep(1000);
			}
			if(isRegistro) {
				conexion.getDriver().findElement(By.id("XML")).click();
				waitDownload(REINTENTAR_DESCARGA);
			}
		} catch (Exception e) {
			isRegistro = false;
			logger.error("Error en procesarFechaDTE : " + e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		} 
		return isRegistro;
	}
	
	/**
	 * Método que obtiene el documento recibido ante sii
	 */
	private boolean procesarPeriodoDTE(Date fecha) throws Exception {
		boolean isRegistro = false;
		final boolean REINTENTAR_DESCARGA = false;
		try {
			List<WebElement> tr_coleccion = conexion.getDriver().findElements(By.xpath("//table[@class='datos']/tbody/tr"));

			if(tr_coleccion.size() == 0) {
				logger.info(conexion.getDriver().findElement(By.tagName("body")).getText());
				String informacionPagina = conexion.getDriver().findElement(By.tagName("body")).getText(); 
				ViewVentanaInformacion.setInformacion(informacionPagina);
				throw new Exception(informacionPagina);
			}
			
			for( int registroDTE = 1; registroDTE < tr_coleccion.size(); registroDTE++ ) {
				
				List<WebElement> columnas = tr_coleccion.get(registroDTE).findElements(By.tagName("td"));
				String periodoLista = FormatoFecha.convertPeriodo(
						FormatoFecha.convertPeriodo(columnas.get(1).getText()));

				if((fecha.after(FormatoFecha.convertPeriodo(periodoLista))
						&& !FormatoFecha.convertPeriodo(fecha).equals(periodoLista))) 
					break;
				
				if(periodoLista.equals(FormatoFecha.convertPeriodo(fecha))) {
					//logger.info("ENCONTRO REGISTRO DTE CON Periodo : " + FormatoFecha.convertPeriodo(fecha));
					columnas.get(0).findElement(By.tagName("input")).click();
					isRegistro = true;
				}
				Thread.sleep(1000);
			}
			if(isRegistro) {
				logger.info("SE PROCEDE A DESCARGAR ARCHIVO ");
				conexion.getDriver().findElement(By.id("XML")).click();
				waitDownload(REINTENTAR_DESCARGA);
			}
		} catch (Exception e) {
			isRegistro = false;
			logger.error("Error en procesarPeriodoDTE : " + e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		} 
		return isRegistro;
	}

	/**
	 * metodo que Procesar archivo csv, leyendo su contenido
	 * y retornando string del mismo.
	 */
	private String procesarFileCsv(String rut) throws Exception {

		logger.info("SE PROCESA ARCHIVO CSV, EN RUTA : " + Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA")));

		StringBuffer stringBuffer = new StringBuffer();
		Path rutaDescarga = Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA"));
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(rutaDescarga, "*.{csv}")) {
			for(Path file: stream) {
				logger.info("NOMBRE ARCHIVO : " + file.getFileName());
				if(file.getFileName().toString().contains(rut)) 
					stringBuffer.append(new String(Files.readAllBytes(file), StandardCharsets.ISO_8859_1));
			}
		} catch(Exception e) {
			logger.error("Error en procesarFileCsv : " + e.getMessage());
		} finally {
			logger.info("ELIMINANDO ARCHIVO : " );
			eliminarFileCsv();
			
		}
		return stringBuffer.toString();
	}
	
	/**
	 * metodo el cual elimina el archivo descargado del browser
	 */
	private void eliminarFileCsv() {
		try {
			Path rutaDescarga = Constantes.HOME_DIR
					.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA"));
			for(File file: rutaDescarga.toFile().listFiles())
				file.delete();
		} catch(Exception e){
			logger.info("Archivo no pudo Eliminarse.");
		}
	}
	
}
