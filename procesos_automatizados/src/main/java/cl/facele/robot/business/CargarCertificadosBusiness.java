package cl.facele.robot.business;

import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import cl.facele.robot.logica.CargarCertificadosLogica;
import cl.facele.robot.logica.RobotLogica;
import cl.facele.robot.logica.ViewVentanaInformacion;
import cl.facele.robot.utilidades.Constantes;
import cl.facele.sfe.cargacontribuyenteselectronicos.CargaContribuyentesElectronicos_Service;

/**
 * Clase que administra el proceso de dte recibidos
 */
public class CargarCertificadosBusiness extends RobotLogica {


	private Logger logger = Logger.getLogger(getClass());
	
	public CargarCertificadosBusiness() throws Exception {
		super(Constantes.GENERAL_CONSTANTES
				.getString("DIR.DESCARGA.CONTRIBUYENTE"));
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

	/**
	 * metodo que gestiona proceso de respaldo dte, tomando de valores estaticos
	 * la fecha y embiente y/o rut
	 */
	public void iniciarCargarContribuyente(List<String> ambientes) throws Exception {
		ViewVentanaInformacion.abrirVentana();
		ViewVentanaInformacion.setInformacion("INICIO PROCESO CARGA CONTRIBUYENTE \n");
		logger.info("CargarCertificadosBusiness.iniciarCargarContribuyente");
		
		CargarCertificadosLogica logica = null;
		try {
			
			logica = new CargarCertificadosLogica(getConexion());
			logica.downloadContribuyente();
			List<Path> listaArchivo = logica.procesarFileCsv();
			ViewVentanaInformacion.setInformacion("");

			if(ambientes.contains("TODO"))
				ambientes = Arrays.asList(Constantes.AMBIENTE);
			
			for(String nombreAmbiente: ambientes) {
				
				if(nombreAmbiente.toUpperCase().equals("TODO"))
					continue;
				
				logger.info("AMBIENTE : " + nombreAmbiente + "\n"
						+ "Conectandose : " + Constantes.GENERAL_CONSTANTES.getString(nombreAmbiente+".CARGAR.PROVEEDORES"));
				
				ViewVentanaInformacion.setInformacion("AMBIENTE : " + nombreAmbiente);
				ViewVentanaInformacion.setInformacion("Conectandose : " + Constantes
						.GENERAL_CONSTANTES.getString(nombreAmbiente+".CARGAR.PROVEEDORES"));
				
				CargaContribuyentesElectronicos_Service service = 
						new CargaContribuyentesElectronicos_Service(
								new URL(Constantes.GENERAL_CONSTANTES.getString(nombreAmbiente+".CARGAR.PROVEEDORES")));
				logica.obtenerServicioWeb(service);
				ViewVentanaInformacion.setInformacion("Conexion Exitosa");
				logica.cargarContribuyente(listaArchivo);
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
