
package cl.facele.sfe.cargacontribuyenteselectronicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estadoProcesamiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="glosaProcesamiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroResolucion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fechaResolucion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="mailIntercambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "estadoProcesamiento",
    "glosaProcesamiento",
    "razonSocial",
    "numeroResolucion",
    "fechaResolucion",
    "mailIntercambio"
})
@XmlRootElement(name = "getContribuyenteElectronicoResponse")
public class GetContribuyenteElectronicoResponse {

    protected int estadoProcesamiento;
    @XmlElement(required = true)
    protected String glosaProcesamiento;
    protected String razonSocial;
    protected Integer numeroResolucion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaResolucion;
    protected String mailIntercambio;

    /**
     * Obtiene el valor de la propiedad estadoProcesamiento.
     * 
     */
    public int getEstadoProcesamiento() {
        return estadoProcesamiento;
    }

    /**
     * Define el valor de la propiedad estadoProcesamiento.
     * 
     */
    public void setEstadoProcesamiento(int value) {
        this.estadoProcesamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad glosaProcesamiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlosaProcesamiento() {
        return glosaProcesamiento;
    }

    /**
     * Define el valor de la propiedad glosaProcesamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlosaProcesamiento(String value) {
        this.glosaProcesamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroResolucion.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroResolucion() {
        return numeroResolucion;
    }

    /**
     * Define el valor de la propiedad numeroResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroResolucion(Integer value) {
        this.numeroResolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaResolucion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaResolucion() {
        return fechaResolucion;
    }

    /**
     * Define el valor de la propiedad fechaResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaResolucion(XMLGregorianCalendar value) {
        this.fechaResolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad mailIntercambio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailIntercambio() {
        return mailIntercambio;
    }

    /**
     * Define el valor de la propiedad mailIntercambio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailIntercambio(String value) {
        this.mailIntercambio = value;
    }

}
