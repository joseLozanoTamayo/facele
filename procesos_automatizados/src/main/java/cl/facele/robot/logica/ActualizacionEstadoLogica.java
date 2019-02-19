package cl.facele.robot.logica;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cl.facele.docele.sfe.services.reportescsv.ReportesCSV;
import cl.facele.docele.sfe.services.reportescsv.ReportesCSV_Service;
import cl.facele.docele.sfe.services.reportescsv.UploadCSV;
import cl.facele.docele.sfe.services.reportescsv.UploadCSVResponse;
import cl.facele.robot.bean.EstadoActualizacionBean;
import cl.facele.robot.conexion.ConexionChrome;
import cl.facele.robot.utilidades.Constantes;

/**
 * 
 */
public class ActualizacionEstadoLogica {

	private ConexionChrome conexion;
	private Logger logger = Logger.getLogger(getClass());
	private ReportesCSV portReporteCSV;
	private SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
	private final static String ANIO = "Año";
	private final static String MES = "Mes";
	private final static String EMPRESA = "Empresa";

	/**
	 * 
	 */
	public ActualizacionEstadoLogica(ConexionChrome conexion){
		this.conexion = conexion;
	}

	/**
	 * 
	 */
	public void obtenerServicioWeb(ReportesCSV_Service service) throws Exception {
		this.portReporteCSV = service.getReportesCSVSOAP();
	}


	/**
	 * 
	 */
	public void iniciarActualizacionEstado() throws Exception {
		try {
			ViewVentanaInformacion.setInformacion("Dirigiendo a : " + Constantes.GENERAL_CONSTANTES.getString("URL.DTE.ACTUALIZACION"));
			conexion.getDriver().navigate().to(Constantes.GENERAL_CONSTANTES.getString("URL.DTE.ACTUALIZACION"));
			TimeUnit.SECONDS.sleep(3);
		} catch(Exception e) {
			throw new Exception("Error, downloadContribuyente : " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * Obtiene lista de meses
	 * 
	 * @return
	 */
	public List<String> getListaMeses() {
		List<String> meses = new ArrayList<>();
	    for (WebElement option : 
	    	new Select(this.conexion.getDriver().findElement(By.id("periodoMes"))).getOptions()) {
	        if (option.getAttribute("value") != "") 
	        	meses.add(option.getText());
	    }
		return meses;
	}
	
	/**
	 * 
	 * Obtiene lista de anios
	 * 
	 * @return
	 */
	public List<String> getListaAnios() {
		List<String> anios = new ArrayList<>();
	    for (WebElement option : 
	    	new Select(this.conexion.getDriver().findElement(By.xpath("//form[@name='formContribuyente']/div[2]/select[2]"))).getOptions()) {
	        if (option.getAttribute("value") != "") 
	        	anios.add(option.getText());
	    }
		return anios;
	}

	/**
	 * 
	 * Obtiene lista de anios
	 * 
	 * @return
	 */
	public List<String> getListaRUT() {
		List<String> ruts = new ArrayList<>();
	    for (WebElement option : 
	    	new Select(this.conexion.getDriver().findElement(By.name("rut"))).getOptions()) {
	        if (option.getAttribute("value") != "") 
	        	ruts.add(option.getText());
	    }
		return ruts;
	}
	
	/**
	 * 
	 * @param anio
	 */
	public void seleccionarAnio(String anio ) {
		if ( ANIO.equals(anio))
			return;
		Select selectAnio = new Select(this.conexion.getDriver()
				.findElement(By.xpath("//form[@name='formContribuyente']/div[2]/select[2]")));
		selectAnio.selectByValue(anio);
	}

	/**
	 * 
	 * @param anio
	 */
	public void seleccionarMes(String mes ) {
		if ( MES.equals(mes))
			return;
		Select selectMes = new Select(this.conexion.getDriver().findElement(By.id("periodoMes")));
		selectMes.selectByValue(mes);
	}
	
	/**
	 * 
	 * @param anio
	 */
	public void seleccionarRUT(String rut ) {
		
		if ( EMPRESA.equals(rut))
			return;
		
		Select selectRut = new Select(this.conexion.getDriver().findElement(By.name("rut")));
		selectRut.selectByValue(rut);
	}
	
	/**
	 * 
	 * @param rut
	 * @throws Exception 
	 */
	public void procesarActualizacionEstado( EstadoActualizacionBean estadoActualizacion ) throws Exception {

		String anio = sdfYear.format(estadoActualizacion.getFecha());
		String mes = sdfMonth.format(estadoActualizacion.getFecha());

		TimeUnit.SECONDS.sleep(5);
		Select selectRut = new Select(this.conexion.getDriver().findElement(By.name("rut")));
		selectRut.selectByValue(estadoActualizacion.getRut());
		Select selectMes = new Select(this.conexion.getDriver().findElement(By.id("periodoMes")));
		selectMes.selectByValue(mes);
		Select selectAnio = new Select(this.conexion.getDriver()
				.findElement(By.xpath("//form[@name='formContribuyente']/div[2]/select[2]")));
		selectAnio.selectByValue(anio);
		
		iniciarDescargaFile();
		
	}

	/**
	 * 
	 * Metodo que inicia la descarga del archivo.
	 * 
	 * @throws Exception
	 */
	public void iniciarDescargaFile() throws Exception {

		conexion.getDriver().findElement(By.xpath("//button[contains(.,'Consultar')]") ).click();	
		TimeUnit.SECONDS.sleep(3);

		conexion.getDriver().findElement(By.xpath("//a[contains(.,'VENTA')]") ).click();
		TimeUnit.SECONDS.sleep(3);

		ViewVentanaInformacion.setInformacion("Procede a Descargar Archivo");
		conexion.getDriver().findElement(By.xpath("//button[contains(.,'Descargar Detalles')]") ).click();	

		waitDownload(Boolean.TRUE);
		TimeUnit.SECONDS.sleep(2);
	}
	
	/**
	 * carga contribuyente al servicio web
	 */
	public void cargarContribuyente(List<Path> listaArchivo, String rutAbonado ) throws Exception {
		ViewVentanaInformacion.setInformacion("Procede a Enviar documento a servicio Web");
		UploadCSVResponse response = null;
		for( Path archivo : listaArchivo ) { 
			try {

				ViewVentanaInformacion.setInformacion("Cantidad envio : " + archivo.toFile().length());

				Base64.Encoder enc = Base64.getEncoder();
				byte[] fileArray = Files.readAllBytes(archivo);

				UploadCSV parameters = new UploadCSV();
				parameters.setInline(Boolean.TRUE);
				parameters.setRutAbonado(rutAbonado);
				parameters.setBytesCSV(enc.encodeToString(fileArray));

				response = portReporteCSV.uploadCSV(parameters );

			} catch (Exception e) {
				throw new Exception("Error cargarContribuyente : " + e.getMessage());
			}
			logger.info("Estado : " + response.getEstadoProcesamiento());
			logger.info("Glosa  : " + response.getGlosaProcesamiento() );

			ViewVentanaInformacion.setInformacion("ESTADO MENSAJE : " + response.getEstadoProcesamiento());
			ViewVentanaInformacion.setInformacion("GLOSA  MENSAJE : " + response.getGlosaProcesamiento());
			ViewVentanaInformacion.setInformacion("");
		}

	}

	/**
	 * espera por la descarga que se esta realizando
	 */
	private void waitDownload(boolean reintentoDescarga) throws Exception {
		Path rutaDescarga = Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.ESTADO"));
		final boolean REINTENTAR_DESCARGA = false; 

		boolean wait = true;
		long second = 180; 
		long milisegundosNow = System.currentTimeMillis();

		while(wait) {

			for(File file: rutaDescarga.toFile().listFiles()) {
				if(!file.getName().contains(".crdownload")) {
					wait = false;
					break;
				}
			}

			if(rutaDescarga.toFile().listFiles().length == 0 && wait) {
				if( reintentoDescarga ) {
					logger.info("Reintento de Descarga DTE");
					ViewVentanaInformacion.setInformacion("Reintento Descarga DTE");
					conexion.getDriver().findElement(By.xpath("//button[contains(.,'Descargar Detalles')]") ).click();	
					TimeUnit.SECONDS.sleep(3);
					waitDownload(REINTENTAR_DESCARGA);
					return;
				}
				throw new Exception("Error, al descargar archivo.");
			}

			TimeUnit.SECONDS.sleep(1);
			long secondNow = (System.currentTimeMillis() - milisegundosNow);
			if( TimeUnit.MILLISECONDS.toSeconds(secondNow) > second) {
				throw new Exception("Error: Descarga a superado limite de tiempo descarga ");
			}
		}
		TimeUnit.SECONDS.sleep(1);
	}


	/**
	 * metodo que Procesar archivo csv, leyendo su contenido
	 * y retornando string del mismo.
	 */
	public List<Path> procesarFileCsv() throws Exception {

		ViewVentanaInformacion.setInformacion("Procesa Archivo CSV en ruta : " +
				Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.ESTADO"));

		logger.info("SE PROCESA ARCHIVO CSV, EN RUTA : " + Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.ESTADO")));

		Path rutaDescarga = Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.ESTADO"));

		List<Path> listaPath = new ArrayList<Path>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(rutaDescarga, "*.{csv}")) {
			for(Path file: stream) {

				logger.info("NOMBRE ARCHIVO : " + file.getFileName());
				listaPath.add(file);
			}
		} catch(Exception e) {
			logger.error("Error en procesarFileCsv : " + e.getMessage());
		} 
		ViewVentanaInformacion.setInformacion("Archivo Segmentado, cantidad archivos " + listaPath.size());
		return listaPath;
	}

	/**
	 * metodo el cual elimina el archivo descargado del browser
	 */
	public void eliminarFiles() {
		try {
			Path rutaDescarga = Constantes.HOME_DIR
					.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.ESTADO"));
			for(File file: rutaDescarga.toFile().listFiles())
				file.delete();
		} catch(Exception e){
			logger.info("Archivo no pudo Eliminarse.");
		}
	}

}
