package cl.facele.robot.logica;

import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import cl.facele.robot.bean.EstadoActualizacionBean;
import cl.facele.robot.business.ActualizacionEstadoBusiness;
import cl.facele.robot.utilidades.Constantes;

/**
 * The Class RunneableEstadoActualizacion.
 */
public class RunneableEstadoActualizacion implements Runnable{

	/** The logger. */
	private Logger logger = Logger.getLogger(getClass()); 
	
	/** The lista ambiente. */
	private List<String> listaAmbiente; 
	
	/** The estado actuaizacion bean. */
	private EstadoActualizacionBean estadoActuaizacionBean;

	/**
	 * Instantiates a new runneable estado actualizacion.
	 *
	 * @param listaAmbiente the lista ambiente
	 */
	public RunneableEstadoActualizacion(List<String> listaAmbiente) {
		this.listaAmbiente = listaAmbiente;
	}
	
	/**
	 * Instantiates a new runneable estado actualizacion.
	 *
	 * @param estadoActualizacionBean the estado actualizacion bean
	 */
	public RunneableEstadoActualizacion(EstadoActualizacionBean estadoActualizacionBean) {
		this.estadoActuaizacionBean = estadoActualizacionBean;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ActualizacionEstadoBusiness actualizacionEstado = null;
		try {
			logger.info("INICIO : RunneableEstadoActualizacion.run");
			actualizacionEstado = new ActualizacionEstadoBusiness();
			actualizacionEstado.loginCertificado();

			actualizacionEstado.iniciarActualizacionEstado(this.estadoActuaizacionBean);

		} catch (Exception e) {
			logger.error("RunneableCargaContribuyente.run : " + e.getMessage());
		} finally {
			Constantes.EXECUTOR.shutdown();
			if( Objects.nonNull(actualizacionEstado) )
				actualizacionEstado.cerrar();
		}
		logger.info("FIN : RunneableCargaContribuyente.run");
	}

}
