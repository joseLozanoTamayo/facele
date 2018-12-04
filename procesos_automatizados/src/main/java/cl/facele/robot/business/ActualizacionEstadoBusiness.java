  package cl.facele.robot.business;

import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import cl.facele.docele.sfe.services.reportescsv.ReportesCSV_Service;
import cl.facele.robot.bean.EstadoActualizacionBean;
import cl.facele.robot.logica.ActualizacionEstadoLogica;
import cl.facele.robot.logica.RobotLogica;
import cl.facele.robot.logica.ViewVentanaInformacion;
import cl.facele.robot.utilidades.Constantes;

/**
 * Clase que administra el proceso de dte recibidos
 */
public class ActualizacionEstadoBusiness extends RobotLogica {


	private Logger logger = Logger.getLogger(getClass());
	
	public ActualizacionEstadoBusiness() throws Exception {
		super(Constantes.GENERAL_CONSTANTES
				.getString("DIR.DESCARGA.ESTADO"));
	}
	
	/**
	 * metodo iniciador de la pagina web sii a procesar
	 */
	public void loginCertificado() throws Exception {
		logger.info("***** INICIANDO LOGIN ******");
		try {
			login();
			Thread.sleep(2000);
			int respuestaDialog = 0;
			if(Constantes.SISTEMA_OP.toLowerCase().contains("win"))
				respuestaDialog = JOptionPane.showOptionDialog(null, Constantes.GENERAL_CONSTANTES.getString("MESSAGE.INFO.LOGIN"),
					"Información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			else
				respuestaDialog = JOptionPane.showOptionDialog(null, Constantes.GENERAL_CONSTANTES.getString("MESSAGE.INFO.LOGIN.CERTIFICADO"),
						"Información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			
			if(respuestaDialog == JOptionPane.CANCEL_OPTION || respuestaDialog == JOptionPane.CLOSED_OPTION){
				throw new Exception("Cancelo Login");
			}
			
			logger.info("***** FINALIZANDO LOGIN ******");	
		} catch(Exception e) {
			throw new Exception("Error En Login : " + e.getMessage(), e);
		}
	}

//	/**
//	 * metodo que gestiona proceso de respaldo dte, tomando de valores estaticos
//	 * la fecha y embiente y/o rut
//	 */
//	public void iniciarActualizacionEstado(List<String> ambientes) throws Exception {
//		ViewVentanaInformacion.abrirVentana();
//		ViewVentanaInformacion.setInformacion("INICIO PROCESO ACTUALIZACION ESTADO \n");
//		logger.info("ActualizacionEstadoBusiness.iniciarActualizacionEstado");
//		
//		ActualizacionEstadoLogica logica = null;
//		try {
//			
//			logica = new ActualizacionEstadoLogica(getConexion());
//			logica.iniciarActualizacionEstado();
//			
////			List<Path> listaArchivo = logica.procesarFileCsv();
//			ViewVentanaInformacion.setInformacion("");
//
//			if(ambientes.contains("TODO"))
//				ambientes = Arrays.asList(Constantes.AMBIENTE);
//			
//			for(String nombreAmbiente: ambientes) {
//				
//				if(nombreAmbiente.toUpperCase().equals("TODO"))
//					continue;
//				
//				logger.info("AMBIENTE : " + nombreAmbiente + "\n"
//						+ "Conectandose : " + Constantes.GENERAL_CONSTANTES.getString(nombreAmbiente+".ESTADO.ACTUALIZACION"));
//				
//				ViewVentanaInformacion.setInformacion("AMBIENTE : " + nombreAmbiente);
//				ViewVentanaInformacion.setInformacion("Conectandose : " + Constantes
//						.GENERAL_CONSTANTES.getString(nombreAmbiente+".ESTADO.ACTUALIZACION"));
//				
//				ReportesCSV_Service service = new ReportesCSV_Service(
//								new URL(Constantes.GENERAL_CONSTANTES.getString(nombreAmbiente+".ESTADO.ACTUALIZACION")));
//				
//				logica.obtenerServicioWeb(service, );
//				ViewVentanaInformacion.setInformacion("");
//			}
//			
//			logger.info("Proceso terminado Exitoso");
//			ViewVentanaInformacion.setInformacion("FINALIZA AMBIENTE \n");
//		} catch(Exception e){
//			throw new Exception("Error en iniciarReslpadoDTE : " + e.getMessage(), e);
//		} finally{
//			logica.eliminarFiles();
//		}
//		logger.info("FIN DEL PROCESO DE CARGA");
//	}
	
	/**
	 * metodo que gestiona proceso de respaldo dte, tomando de valores estaticos
	 * la fecha y embiente y/o rut
	 */
	public void iniciarActualizacionEstado(final EstadoActualizacionBean ambientes) throws Exception {
		ViewVentanaInformacion.abrirVentana();
		ViewVentanaInformacion.setInformacion("INICIO PROCESO ACTUALIZACION ESTADO \n");
		logger.info("ActualizacionEstadoBusiness.iniciarActualizacionEstado");
		
		ActualizacionEstadoLogica logica = null;
		try {
			
			logica = new ActualizacionEstadoLogica(getConexion());
			logica.iniciarActualizacionEstado();
			logica.procesarActualizacionEstado(ambientes);
			List<Path> listaArchivo = logica.procesarFileCsv();
			ViewVentanaInformacion.setInformacion("");

			if(ambientes.getListaAmbiente().contains("TODO"))
				ambientes.setListaAmbiente(Arrays.asList(Constantes.AMBIENTE));
			
			for(String nombreAmbiente: ambientes.getListaAmbiente()) {
				
				if(nombreAmbiente.toUpperCase().equals("TODO"))
					continue;
				
				logger.info("AMBIENTE : " + nombreAmbiente + "\n"
						+ "Conectandose : " + Constantes.GENERAL_CONSTANTES.getString(nombreAmbiente + ".ESTADO.ACTUALIZACION"));
				
				ViewVentanaInformacion.setInformacion("AMBIENTE : " + nombreAmbiente);
				ViewVentanaInformacion.setInformacion("Conectandose : " + Constantes
						.GENERAL_CONSTANTES.getString(nombreAmbiente+".CARGAR.PROVEEDORES"));
				
				ReportesCSV_Service service = new ReportesCSV_Service(
								new URL(Constantes.GENERAL_CONSTANTES.getString(nombreAmbiente+".ESTADO.ACTUALIZACION")));
				logica.obtenerServicioWeb(service);
				ViewVentanaInformacion.setInformacion("Conexion Exitosa");

 				logica.cargarContribuyente(listaArchivo, ambientes.getRut());
				ViewVentanaInformacion.setInformacion("");
			}
			
			logger.info("Proceso terminado Exitoso");
			ViewVentanaInformacion.setInformacion("FINALIZA AMBIENTE \n");
		} catch(Exception e){
			throw new Exception("Error en iniciarReslpadoDTE : " + e.getMessage(), e);
		} finally{
			logica.eliminarFiles();
		}
		logger.info("FIN DEL PROCESO DE CARGA");
	}
	
}
