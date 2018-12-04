package cl.facele.robot.inicio;

import org.apache.log4j.Logger;

import cl.facele.robot.logica.ViewAutomatizacionProceso;

/**
 * Inicio de aplicacion Respaldo
 */
public class ProcesoInicio {

	private Logger logger = Logger.getLogger(this.getClass());
	public static void main(String[] args) {
		ProcesoInicio main = new ProcesoInicio();
		try {
			main.inicio();
		} catch (Exception e) {
			main.logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Método que obtiene loggin de una pagina web
	 */
	public void inicio() throws Exception {
		logger.info("****** INICIO APP ROBOT RESPALDO AUTOMATIZACION ****");
		try {
			new ViewAutomatizacionProceso().setVisible(true);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} 
		logger.info("****** FIN APP ROBOT RESPALDO ****");
	}

}
