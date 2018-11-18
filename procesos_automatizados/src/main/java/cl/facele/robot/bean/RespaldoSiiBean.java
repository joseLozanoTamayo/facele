package cl.facele.robot.bean;

import java.io.Serializable;

/**
 * Bean para el manejo de datos
 */
@SuppressWarnings("serial")
public class RespaldoSiiBean implements Serializable {


	private StringBuilder informacion;
	private String totalFolios;
	private long cantidadFolios;
	private String texto;
	private String documentoOrigen;
	private String tipoDocumento;
	private Long folioDesde;
	private Long folioHasta;
	private String foliosSinProcesar;
	
	
	/**
	 * @return the informacion
	 */
	public StringBuilder getInformacion() {
		return informacion;
	}

	/**
	 * @param informacion the informacion to set
	 */
	public void setInformacion(StringBuilder informacion) {
		this.informacion = informacion;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the folioDesde
	 */
	public Long getFolioDesde() {
		return folioDesde;
	}
	/**
	 * @param folioDesde the folioDesde to set
	 */
	public void setFolioDesde(Long folioDesde) {
		this.folioDesde = folioDesde;
	}
	/**
	 * @return the folioHasta
	 */
	public Long getFolioHasta() {
		return folioHasta;
	}
	/**
	 * @param folioHasta the folioHasta to set
	 */
	public void setFolioHasta(Long folioHasta) {
		this.folioHasta = folioHasta;
	}
	/**
	 * @return the totalFolios
	 */
	public String getTotalFolios() {
		return totalFolios;
	}
	/**
	 * @param totalFolios the totalFolios to set
	 */
	public void setTotalFolios(String totalFolios) {
		this.totalFolios = totalFolios;
	}
	/**
	 * @return the cantidadFolios
	 */
	public long getCantidadFolios() {
		return cantidadFolios;
	}
	/**
	 * @param cantidadFolios the cantidadFolios to set
	 */
	public void setCantidadFolios(long cantidadFolios) {
		this.cantidadFolios = cantidadFolios;
	}
	/**
	 * @return the documentoOrigen
	 */
	public String getDocumentoOrigen() {
		return documentoOrigen;
	}
	/**
	 * @param documentoOrigen the documentoOrigen to set
	 */
	public void setDocumentoOrigen(String documentoOrigen) {
		this.documentoOrigen = documentoOrigen;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the foliosSinProcesar
	 */
	public String getFoliosSinProcesar() {
		return foliosSinProcesar;
	}
	/**
	 * @param foliosSinProcesar the foliosSinProcesar to set
	 */
	public void setFoliosSinProcesar(String foliosSinProcesar) {
		this.foliosSinProcesar = foliosSinProcesar;
	}

}
