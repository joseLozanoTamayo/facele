
package cl.facele.sfe.recepciondte;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DTEPendienteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DTEPendienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rutEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="razonSocialEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoDTE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fechaEmision" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="montoTotal" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="fechaAprobacionSII" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="trackId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DTEPendienteType", propOrder = {
    "rutEmisor",
    "razonSocialEmisor",
    "tipoDTE",
    "folio",
    "fechaEmision",
    "montoTotal",
    "fechaAprobacionSII",
    "trackId"
})
public class DTEPendienteType {

    @XmlElement(required = true)
    protected String rutEmisor;
    @XmlElement(required = true)
    protected String razonSocialEmisor;
    protected int tipoDTE;
    protected long folio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEmision;
    @XmlElement(required = true)
    protected BigInteger montoTotal;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAprobacionSII;
    protected long trackId;

    /**
     * Obtiene el valor de la propiedad rutEmisor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutEmisor() {
        return rutEmisor;
    }

    /**
     * Define el valor de la propiedad rutEmisor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutEmisor(String value) {
        this.rutEmisor = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocialEmisor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocialEmisor() {
        return razonSocialEmisor;
    }

    /**
     * Define el valor de la propiedad razonSocialEmisor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocialEmisor(String value) {
        this.razonSocialEmisor = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDTE.
     * 
     */
    public int getTipoDTE() {
        return tipoDTE;
    }

    /**
     * Define el valor de la propiedad tipoDTE.
     * 
     */
    public void setTipoDTE(int value) {
        this.tipoDTE = value;
    }

    /**
     * Obtiene el valor de la propiedad folio.
     * 
     */
    public long getFolio() {
        return folio;
    }

    /**
     * Define el valor de la propiedad folio.
     * 
     */
    public void setFolio(long value) {
        this.folio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEmision.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Define el valor de la propiedad fechaEmision.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEmision(XMLGregorianCalendar value) {
        this.fechaEmision = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotal.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMontoTotal() {
        return montoTotal;
    }

    /**
     * Define el valor de la propiedad montoTotal.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMontoTotal(BigInteger value) {
        this.montoTotal = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAprobacionSII.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAprobacionSII() {
        return fechaAprobacionSII;
    }

    /**
     * Define el valor de la propiedad fechaAprobacionSII.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAprobacionSII(XMLGregorianCalendar value) {
        this.fechaAprobacionSII = value;
    }

    /**
     * Obtiene el valor de la propiedad trackId.
     * 
     */
    public long getTrackId() {
        return trackId;
    }

    /**
     * Define el valor de la propiedad trackId.
     * 
     */
    public void setTrackId(long value) {
        this.trackId = value;
    }

}
