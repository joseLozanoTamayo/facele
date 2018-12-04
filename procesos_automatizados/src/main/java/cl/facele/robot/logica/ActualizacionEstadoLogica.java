package cl.facele.robot.logica;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
	private final int MAXIMO_LINEAS = 30000;
	private ReportesCSV portReporteCSV;
	private SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
	
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
			ViewVentanaInformacion.setInformacion("Procede a Descargar Archivo");
			conexion.getDriver().navigate().to(Constantes.GENERAL_CONSTANTES.getString("URL.DTE.ACTUALIZACION"));
			Thread.sleep(3000);
		} catch(Exception e) {
			throw new Exception("Error, downloadContribuyente : " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param rut
	 * @throws Exception 
	 */
	public void procesarActualizacionEstado( EstadoActualizacionBean estadoActualizacion ) throws Exception {

		String anio = sdfYear.format(estadoActualizacion.getFecha());
		String mes = sdfMonth.format(estadoActualizacion.getFecha());
		
		TimeUnit.SECONDS.sleep(10);
		Select selectRut = new Select(this.conexion.getDriver().findElement(By.name("rut")));
		selectRut.selectByValue(estadoActualizacion.getRut());
		Select selectMes = new Select(this.conexion.getDriver().findElement(By.id("periodoMes")));
		selectMes.selectByValue(mes);
		Select selectAnio = new Select(this.conexion.getDriver()
				.findElement(By.xpath("//form[@name='formContribuyente']/div[2]/select[2]")));
		selectAnio.selectByValue(anio);
		
		conexion.getDriver().findElement(By.xpath("//button[contains(.,'Consultar')]") ).click();	
		
		TimeUnit.SECONDS.sleep(10);
		
		conexion.getDriver().findElement(By.xpath("//a[contains(.,'VENTA')]") ).click();
		
		TimeUnit.SECONDS.sleep(10);
		ViewVentanaInformacion.setInformacion("Procede a Descargar Archivo");
		conexion.getDriver().findElement(By.xpath("//button[contains(.,'Descargar Detalles')]") ).click();	
		
		TimeUnit.SECONDS.sleep(10);
		waitDownload(Boolean.TRUE);
		TimeUnit.SECONDS.sleep(5);
	}
	
	/**
	 * carga contribuyente al servicio web
	 */
	public void cargarContribuyente(List<Path> listaArchivo, String rutAbonado ) throws Exception {
		ViewVentanaInformacion.setInformacion("Procede a Enviar documento a servicio Web");
		UploadCSVResponse response = null;
		StringBuilder buffer = new StringBuilder();
		for( Path archivo : listaArchivo ) {
			try {

				ViewVentanaInformacion.setInformacion("Cantidad envio : " + archivo.toFile().length());
				
				Base64.Encoder enc = Base64.getEncoder();
				byte[] fileArray = Files.readAllBytes(archivo);

				UploadCSV parameters = new UploadCSV();
				parameters.setInline(Boolean.TRUE);
				parameters.setRutAbonado(rutAbonado);
				parameters.setBytesCSV(enc.encode(fileArray));
				
				response = portReporteCSV.uploadCSV(parameters );
				
			} catch (Exception e) {
				throw new Exception("Error cargarContribuyente : " + e.getMessage());
			}
			buffer.delete(0, buffer.length());
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
				List<String> archivo =  Files.readAllLines(file, StandardCharsets.ISO_8859_1);
				String encabezado = archivo.get(0);
				StringBuffer buffer = new StringBuffer();
				int inicioSegmento = 0;
				int finalSegmento  = MAXIMO_LINEAS;
				int cantidadLineas = archivo.size();
				logger.info("Cantidad Registros : " + cantidadLineas);
				ViewVentanaInformacion.setInformacion("Cantidad Registros a Procesar : " + cantidadLineas);
				
				for(String linea: archivo) {
					buffer.append(linea).append("\n");
					if(inicioSegmento == finalSegmento) {
						
						Path dirArchivo = rutaDescarga
								.resolve(System.currentTimeMillis()+"_"+file.getFileName().toString().replaceAll(".csv", "txt")); 
						Files.write( dirArchivo, buffer.toString().getBytes(), 
								StandardOpenOption.CREATE, StandardOpenOption.APPEND);
						
						buffer.delete(0, buffer.length());
						buffer.append(encabezado).append("\n");
						finalSegmento += MAXIMO_LINEAS;
						
						if(finalSegmento > cantidadLineas)
							finalSegmento = cantidadLineas-1;
						
						listaPath.add(dirArchivo);
					}
					inicioSegmento++;
				}
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
