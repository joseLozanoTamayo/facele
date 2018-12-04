package cl.facele.robot.logica;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import cl.facele.robot.conexion.ConexionChrome;
import cl.facele.robot.utilidades.Constantes;
import cl.facele.sfe.cargacontribuyenteselectronicos.CargaContribuyentesElectronicos;
import cl.facele.sfe.cargacontribuyenteselectronicos.CargaContribuyentesElectronicos_Service;
import cl.facele.sfe.cargacontribuyenteselectronicos.DoCargaContribuyentesElectronicos;
import cl.facele.sfe.cargacontribuyenteselectronicos.DoCargaContribuyentesElectronicosResponse;

/**
 * 
 */
public class CargarCertificadosLogica {
	
	private ConexionChrome conexion;
	private Logger logger = Logger.getLogger(getClass());
	private final int MAXIMO_LINEAS = 30000;
	private CargaContribuyentesElectronicos portCargaContribuyente;
	
	/**
	 * 
	 */
	public CargarCertificadosLogica(ConexionChrome conexion){
		this.conexion = conexion;
	}
	
	/**
	 * 
	 */
	public void obtenerServicioWeb(CargaContribuyentesElectronicos_Service service) throws Exception {
		 this.portCargaContribuyente = service.getCargaContribuyentesElectronicosService();
	}
	
	/**
	 * Descarga archivo procedimiento
	 */
	public void downloadContribuyente() throws Exception {
		try {
			ViewVentanaInformacion.setInformacion("Procede a Descargar Archivo");
			conexion.getDriver().navigate().to(Constantes.GENERAL_CONSTANTES.getString("URL.DTE.ACTUALIZACION"));
			Thread.sleep(3000);
			conexion.getDriver().findElement(By.partialLinkText("Descargar Listado")).click();
			waitDownload(3);
			ViewVentanaInformacion.setInformacion("Descarga Archivo Exitoso");
		} catch(Exception e) {
			throw new Exception("Error, downloadContribuyente : " + e.getMessage());
		}
	}
	
	/**
	 * carga contribuyente al servicio web
	 */
	public void cargarContribuyente(List<Path> listaArchivo ) throws Exception {
		ViewVentanaInformacion.setInformacion("Procede a Enviar documento a servicio Web");
		DoCargaContribuyentesElectronicosResponse response = null;
		StringBuilder buffer = new StringBuilder();
		for( Path archivoContribuyente : listaArchivo ) {
			try {
				
				List<String> archivo = Files.readAllLines(archivoContribuyente, StandardCharsets.ISO_8859_1);
				
				ViewVentanaInformacion.setInformacion("Cantidad envio : " + archivo.size());
				for( String linea : archivo ){
					buffer.append(linea).append("\n");
				}

				DoCargaContribuyentesElectronicos parameters = new DoCargaContribuyentesElectronicos();
				parameters.setEmisoresElectronicos(buffer.toString());
				response = portCargaContribuyente.doCargaContribuyentesElectronicos(parameters );
				//new DoCargaContribuyentesElectronicosResponse();
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
	private void waitDownload(int reitentoDescarga) throws Exception {
		
		boolean wait = true;
		long second = 180; 
		long milisegundosNow = System.currentTimeMillis();
		
		Path rutaDescarga = Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.CONTRIBUYENTE"));
		
		Thread.sleep(2000);
		while(wait) {
			for(File file: rutaDescarga.toFile().listFiles()){
				if(!file.getName().contains(".crdownload")) {
					wait = false;
					break;
				}
			}
			if(rutaDescarga.toFile().listFiles().length == 0 && wait) {
				if(reitentoDescarga <= 1)
					return;
				logger.info("Reintento de Descarga DTE");
				conexion.getDriver().findElement(By.partialLinkText("Descargar Listado")).click();
				waitDownload(--reitentoDescarga);
			}
			Thread.sleep(1000);
			long secondNow = (System.currentTimeMillis() - milisegundosNow);
			if( TimeUnit.MILLISECONDS.toSeconds(secondNow) > second) {
				throw new Exception("Error: Descarga a superado limite de tiempo descarga ");
			}
		}
		Thread.sleep(1000);
	}
	
	
	/**
	 * metodo que Procesar archivo csv, leyendo su contenido
	 * y retornando string del mismo.
	 */
	public List<Path> procesarFileCsv() throws Exception {

		ViewVentanaInformacion.setInformacion("Procesa Archivo CSV en ruta : " +
				Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.CONTRIBUYENTE"));
		
		logger.info("SE PROCESA ARCHIVO CSV, EN RUTA : " + Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.CONTRIBUYENTE")));
		
		Path rutaDescarga = Constantes.HOME_DIR
				.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.CONTRIBUYENTE"));
		
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
					.resolve(Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA.CONTRIBUYENTE"));
			for(File file: rutaDescarga.toFile().listFiles())
				file.delete();
		} catch(Exception e){
			logger.info("Archivo no pudo Eliminarse.");
		}
	}
	
}
