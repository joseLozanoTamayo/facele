package cl.facele.robot.logica;

import java.util.List;
import org.apache.log4j.Logger;
import cl.facele.robot.business.CargarCertificadosBusiness;
import cl.facele.robot.utilidades.Constantes;

/**
 * 
 *
 */
public class RunneableCargaContribuyente implements Runnable{

	private Logger logger = Logger.getLogger(getClass()); 
	private List<String> listaAmbiente; 

	/**
	 * 
	 */
	public RunneableCargaContribuyente(List<String> listaAmbiente) {
		this.listaAmbiente = listaAmbiente;
	}

	@Override
	public void run() {
		CargarCertificadosBusiness cargarCertificado = null;
		try {
			logger.info("INICIO : RunneableCargaContribuyente.run");
			cargarCertificado = new CargarCertificadosBusiness();
			cargarCertificado.loginCertificado();

			cargarCertificado.iniciarCargarContribuyente(listaAmbiente);

		} catch (Exception e) {
			logger.error("RunneableCargaContribuyente.run : "
					+ e.getMessage());
		} finally {
			Constantes.EXECUTOR.shutdown();
			if(cargarCertificado != null)
				cargarCertificado.cerrar();
		}
		logger.info("FIN : RunneableCargaContribuyente.run");
	}

}
