package cl.facele.robot.bean;

import java.util.Date;
import java.util.List;

/**
 * The Class EstadoActualizacionBean.
 */
public class EstadoActualizacionBean {
	
	/** The lista ambiente. */
	private List<String> listaAmbiente;
	
	/** The rut. */
	private String rut;
	
	/** The fecha. */
	private Date fecha;
	
	/** The periodoAnio */
	private String periodoAnio;
	
	/** The periodoMes */
	private String periodoMes;
	
	/**
	 * Gets the lista ambiente.
	 *
	 * @return the lista ambiente
	 */
	public List<String> getListaAmbiente() {
		return listaAmbiente;
	}
	
	/**
	 * Gets the rut.
	 *
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}
	
	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Sets the lista ambiente.
	 *
	 * @param listaAmbiente the new lista ambiente
	 */
	public void setListaAmbiente(List<String> listaAmbiente) {
		this.listaAmbiente = listaAmbiente;
	}
	
	/**
	 * Sets the rut.
	 *
	 * @param rut the new rut
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPeriodoAnio() {
		return periodoAnio;
	}

	public void setPeriodoAnio(String periodoAnio) {
		this.periodoAnio = periodoAnio;
	}

	public String getPeriodoMes() {
		return periodoMes;
	}

	public void setPeriodoMes(String periodoMes) {
		this.periodoMes = periodoMes;
	}
	

}
