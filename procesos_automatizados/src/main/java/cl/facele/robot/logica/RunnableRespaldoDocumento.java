package cl.facele.robot.logica;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import cl.facele.robot.business.DTERecibidoSIIBusiness;
import cl.facele.robot.utilidades.Constantes;

public class RunnableRespaldoDocumento implements Runnable {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void run() {
		logger.info("****** INICIO APP ROBOT RESPALDO ****");
		DTERecibidoSIIBusiness dteRecibidosBusiness = new DTERecibidoSIIBusiness(); 
		
		try {

			int estado = dteRecibidosBusiness.ventanaInicio();
			if(estado == JOptionPane.CANCEL_OPTION || estado == JOptionPane.CLOSED_OPTION){
				logger.info("RESPALDO A SIDO CANCELADO, CERRANDO VENTANA PROCESO");
				System.exit(0);
			}
			dteRecibidosBusiness.login();
			dteRecibidosBusiness.iniciarRespaldo();
		
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} 
		finally {
			try {
				Constantes.EXECUTOR.shutdown();
				dteRecibidosBusiness.cerrar();
			} catch (Exception e) {
				logger.info("Chrome cerrado de forma manual");
			}
		}
		logger.info("****** FIN APP ROBOT RESPALDO ****");
		
	}

}
