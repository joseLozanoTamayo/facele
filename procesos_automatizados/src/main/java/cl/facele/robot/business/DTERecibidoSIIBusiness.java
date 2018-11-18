package cl.facele.robot.business;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import cl.facele.robot.logica.DTERecibidoSIILogica;
import cl.facele.robot.logica.RobotLogica;
import cl.facele.robot.logica.ViewVentanaInformacion;
import cl.facele.robot.logica.ViewRespaldaDTE;
import cl.facele.robot.utilidades.Constantes;
import cl.facele.sfe.cargapropiedadescontribuyente.CargaPropiedadesContribuyente_Service;
import cl.facele.sfe.cargapropiedadescontribuyente.ConsultarResponse;
import cl.facele.sfe.cargapropiedadescontribuyente.ContribuyenteType;
import cl.facele.sfe.cargapropiedadescontribuyente.ObtenerResponse;
import cl.facele.sfe.recepciondte.RecepcionDTE_Service;
import cl.facele.utiles.FormatoFecha;

/**
 * Clase que administra el proceso de dte recibidos
 */
public class DTERecibidoSIIBusiness {

	private Logger logger = Logger.getLogger(getClass());
	private RobotLogica inicioPagina;
	
	/**
	 * metodo iniciador de la pagina web sii a procesar
	 */
	public void login() throws Exception{
		logger.info("***** INICIANDO LOGIN ******");
		try {
			inicioPagina = new RobotLogica();
			inicioPagina.login();
			
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
	 * Ventana Inicial de parametrizacion de proceso
	 */
	public int ventanaInicio() throws Exception {
		logger.info("***** Desplegando Ventana de Proceso ******");
		try{
			return ViewRespaldaDTE.createAndShowGUI(); 
		}catch(Exception e) {
			throw new Exception("Error en memtodo: ventanaInicio - "+ e.getMessage(), e);
		}
	}

	/**
	 * metodo que gestiona proceso de respaldo dte, tomando de valores estaticos
	 * la fecha y embiente y/o rut
	 */
	public void iniciarRespaldoDTE() throws Exception {
		
		try {
			
			String ambiente = Constantes.AMBIENTE[ViewRespaldaDTE.getProceso().getSelectedIndex()];
			String rutContribuyente = ViewRespaldaDTE.getRutContribuyente().getText();
			Date fechaConsulta = ViewRespaldaDTE.getFecha().getDate();
			Date periodoConsulta = ViewRespaldaDTE.getPeriodo().getText().equals("") ?
					null : FormatoFecha.convertPeriodo(ViewRespaldaDTE.getPeriodo().getText());
		
			logger.info("****** INICIANDO PROCESO DE RESPALDO DTE ******");
			logger.info("FECHA RESPALDO RECIBIDO DTE : "+ fechaConsulta);
			logger.info("PERIODO RESPALDO RECIBIDO DTE : "+ periodoConsulta);
			logger.info("AMBIENTE : "+ ambiente);
			logger.info("WS CARGAR CONTRIBUYENTE : "+ Constantes.GENERAL_CONSTANTES.getString(ambiente+".CARGAR.CONTRIBUYENTE"));
			logger.info("WS RECEPCION DTE : "+ Constantes.GENERAL_CONSTANTES.getString(ambiente+".RECEPCION.DTE"));

			CargaPropiedadesContribuyente_Service cargaContribuyente = new CargaPropiedadesContribuyente_Service(new URL(Constantes.GENERAL_CONSTANTES.getString(ambiente+".CARGAR.CONTRIBUYENTE")));
			RecepcionDTE_Service recepcionDTE = new RecepcionDTE_Service(new URL(Constantes.GENERAL_CONSTANTES.getString(ambiente+".RECEPCION.DTE")));
			DTERecibidoSIILogica dteRecibido = new DTERecibidoSIILogica(inicioPagina.getConexion(), cargaContribuyente, recepcionDTE);

			logger.info("****** CONEXION SERVICIOS EXITOSA ******");
			
			List<ContribuyenteType> registrosContribuyente = new ArrayList<ContribuyenteType>();
			boolean continuarConsulta = true;

			if(!rutContribuyente.equals("")) {
				logger.info("CONSULTANDO EMPRESA OPERACION (obtenerContribuyente): " + rutContribuyente);
				ObtenerResponse obtenerResponse = dteRecibido.obtenerContribuyente(rutContribuyente);
				logger.info("RESPUESTA (obtenerContribuyente) - ESTADO : " + obtenerResponse.getEstadoProcesamiento()
					+ "  GLOSA : "+ obtenerResponse.getGlosaProcesamiento());
				if(obtenerResponse.getEstadoProcesamiento() == 0) {
					JOptionPane.showOptionDialog(null, obtenerResponse.getGlosaProcesamiento(),
							"Información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					return;
				}
				ContribuyenteType contribuyente = new ContribuyenteType();
				contribuyente.setRut(rutContribuyente);
				registrosContribuyente.add(contribuyente);
				continuarConsulta = false;
			}
			
			int offSet = 0;
			ConsultarResponse consultarResponse = null;
			while( continuarConsulta ) {
				consultarResponse = dteRecibido
						.consultarContribuyente("ACTIVO", offSet);
				
				if(consultarResponse.getEstadoProcesamiento() == 0 )
					throw new Exception("response iniciarReslpadoDTE Estado : "+consultarResponse.getEstadoProcesamiento()
							+ " Descripcion : " + consultarResponse.getGlosaProcesamiento());
				
				if (consultarResponse.getRegistros().size() == 0 || consultarResponse.getRegistros().size() < 100)
					continuarConsulta = false;
				
				registrosContribuyente.addAll(consultarResponse.getRegistros());
				offSet += 100;
			}
			
			if(registrosContribuyente.size() == 0)
				throw new Exception("response iniciarReslpadoDTE Estado : " + consultarResponse.getEstadoProcesamiento()
						+ " Descripcion : " + consultarResponse.getGlosaProcesamiento());
			
			ViewVentanaInformacion.abrirVentana();
			ViewVentanaInformacion.setInformacion("AMBIENTE : " + ambiente);
			
			if(fechaConsulta != null || periodoConsulta != null)
				dteRecibido.iniciarRespaldo(fechaConsulta, periodoConsulta, registrosContribuyente);
			else
				dteRecibido.iniciarRespaldoGeneral(registrosContribuyente);
			
		} catch(Exception e){
			throw new Exception("Error en iniciarReslpadoDTE : " + e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo inicializador de proceso proceso de respaldo
	 * general con y sin fecha.
	 * 
	 */
	public void iniciarRespaldoDTEGeneral() throws Exception{
		ViewVentanaInformacion.abrirVentana();
		
		for(String ambiente: Constantes.AMBIENTE) {
			try {
				
				if(ambiente.equals("TODO"))
					continue;
				
				String rutContribuyente = ViewRespaldaDTE.getRutContribuyente().getText();
				Date fechaConsulta = ViewRespaldaDTE.getFecha().getDate();
				Date periodoConsulta = ViewRespaldaDTE.getPeriodo().getText().equals("") ?
						null : FormatoFecha.convertPeriodo(ViewRespaldaDTE.getPeriodo().getText());
			
				logger.info("****** INICIANDO PROCESO DE RESPALDO DTE ******");
				logger.info("FECHA RESPALDO RECIBIDO DTE : "+ fechaConsulta);
				logger.info("PERIODO RESPALDO RECIBIDO DTE : "+ periodoConsulta);
				logger.info("AMBIENTE : "+ ambiente);
				logger.info("WS CARGAR CONTRIBUYENTE : "+ Constantes.GENERAL_CONSTANTES.getString(ambiente+".CARGAR.CONTRIBUYENTE"));
				logger.info("WS RECEPCION DTE : "+ Constantes.GENERAL_CONSTANTES.getString(ambiente+".RECEPCION.DTE"));

				CargaPropiedadesContribuyente_Service cargaContribuyente = new CargaPropiedadesContribuyente_Service(new URL(Constantes.GENERAL_CONSTANTES.getString(ambiente+".CARGAR.CONTRIBUYENTE")));
				RecepcionDTE_Service recepcionDTE = new RecepcionDTE_Service(new URL(Constantes.GENERAL_CONSTANTES.getString(ambiente+".RECEPCION.DTE")));
				DTERecibidoSIILogica dteRecibido = new DTERecibidoSIILogica(inicioPagina.getConexion(), cargaContribuyente, recepcionDTE);

				logger.info("****** CONEXION SERVICIOS EXITOSA ******");
				
				List<ContribuyenteType> registrosContribuyente = new ArrayList<ContribuyenteType>();
				boolean continuarConsulta = true;

				if(!rutContribuyente.equals("")) {
					logger.info("CONSULTANDO EMPRESA OPERACION (obtenerContribuyente): " + rutContribuyente);
					ObtenerResponse obtenerResponse = dteRecibido.obtenerContribuyente(rutContribuyente);
					
					logger.info("RESPUESTA (obtenerContribuyente) - ESTADO : " + obtenerResponse.getEstadoProcesamiento()
						+ "  GLOSA : "+ obtenerResponse.getGlosaProcesamiento());
					
					logger.info("");
					if(obtenerResponse.getEstadoProcesamiento() == 0) {
						JOptionPane.showOptionDialog(null, obtenerResponse.getGlosaProcesamiento(),
								"Información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						return;
					}
					ContribuyenteType contribuyente = new ContribuyenteType();
					contribuyente.setRut(rutContribuyente);
					registrosContribuyente.add(contribuyente);
					continuarConsulta = false;
				}
				
				ConsultarResponse consultarResponse = null;
				int offSet = 0;
				while( continuarConsulta ) {
					consultarResponse = dteRecibido
							.consultarContribuyente("ACTIVO", offSet);
					if(consultarResponse.getEstadoProcesamiento() == 0 )
						throw new Exception(
								"response iniciarReslpadoDTE Estado : "+consultarResponse.getEstadoProcesamiento()
								+ " Descripcion : " + consultarResponse.getGlosaProcesamiento());
					if (consultarResponse.getRegistros().size() == 0 || consultarResponse.getRegistros().size() < 100)
						continuarConsulta = false;
					registrosContribuyente.addAll(consultarResponse.getRegistros());
					offSet += 100;
				}
				
				if(registrosContribuyente.size() == 0)
					throw new Exception(
							"response iniciarReslpadoDTE Estado : "+consultarResponse.getEstadoProcesamiento()
							+ " Descripcion : " + consultarResponse.getGlosaProcesamiento());

				ViewVentanaInformacion.setInformacion("AMBIENTE : " + ambiente);
				if(fechaConsulta != null || periodoConsulta != null)
					dteRecibido.iniciarRespaldo(fechaConsulta, periodoConsulta, registrosContribuyente);
				else
					dteRecibido.iniciarRespaldoGeneral(registrosContribuyente);
			
			} catch (Exception e) {
				logger.error("Error en iniciarRespaldoGeneral : " + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * Metodo inicializador 
	 */
	public void iniciarRespaldo() throws Exception{
		if( Constantes.AMBIENTE[ViewRespaldaDTE.getProceso().getSelectedIndex()].equals("TODO")) 
			iniciarRespaldoDTEGeneral();
		else 
			iniciarRespaldoDTE();
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void cerrar() throws Exception {
		try {
			if(inicioPagina != null)
				inicioPagina.cerrar();
			inicioPagina = null;
		} catch(Exception e) {
			throw new Exception("Error al cerrar Web Driver");
		}

	}

}
