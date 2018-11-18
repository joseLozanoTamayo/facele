
package cl.docele.ws.documentosrecibidos;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para RegistroType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RegistroType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rutEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoDTE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="folioDTE" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fechaEmision" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="fechaRecepcion" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="montoTotal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="estadoRecepcion">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Pendiente_De_Recepcion"/>
 *               &lt;enumeration value="Recepcion_OK"/>
 *               &lt;enumeration value="Recepcion_Rechazada"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="glosaRecepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoAprobacion">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Pendiente_De_Aprobacion"/>
 *               &lt;enumeration value="Aprobado"/>
 *               &lt;enumeration value="Aprobado_Con_Discrepancia"/>
 *               &lt;enumeration value="Rechazado"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="glosaAprobacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acuseRecibo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistroType", propOrder = {
    "rutEmisor",
    "razonSocial",
    "tipoDTE",
    "folioDTE",
    "fechaEmision",
    "fechaRecepcion",
    "montoTotal",
    "estadoRecepcion",
    "glosaRecepcion",
    "estadoAprobacion",
    "glosaAprobacion",
    "acuseRecibo"
})
public class RegistroType {

    @XmlElement(required = true)
    protected String rutEmisor;
    @XmlElement(required = true)
    protected String razonSocial;
    protected int tipoDTE;
    protected long folioDTE;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEmision;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRecepcion;
    @XmlElement(required = true)
    protected BigDecimal montoTotal;
    @XmlElement(required = true)
    protected String estadoRecepcion;
    protected String glosaRecepcion;
    @XmlElement(required = true)
    protected String estadoAprobacion;
    protected String glosaAprobacion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar acuseRecibo;

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
     * Obtiene el valor de la propiedad folioDTE.
     * 
     */
    public long getFolioDTE() {
        return folioDTE;
    }

    /**
     * Define el valor de la propiedad folioDTE.
     * 
     */
    public void setFolioDTE(long value) {
        this.folioDTE = value;
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
     * Obtiene el valor de la propiedad fechaRecepcion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    /**
     * Define el valor de la propiedad fechaRecepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRecepcion(XMLGregorianCalendar value) {
        this.fechaRecepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotal.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    /**
     * Define el valor de la propiedad montoTotal.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoTotal(BigDecimal value) {
        this.montoTotal = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoRecepcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoRecepcion() {
        return estadoRecepcion;
    }

    /**
     * Define el valor de la propiedad estadoRecepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoRecepcion(String value) {
        this.estadoRecepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad glosaRecepcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlosaRecepcion() {
        return glosaRecepcion;
    }

    /**
     * Define el valor de la propiedad glosaRecepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlosaRecepcion(String value) {
        this.glosaRecepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoAprobacion() {
        return estadoAprobacion;
    }

    /**
     * Define el valor de la propiedad estadoAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoAprobacion(String value) {
        this.estadoAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad glosaAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlosaAprobacion() {
        return glosaAprobacion;
    }

    /**
     * Define el valor de la propiedad glosaAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlosaAprobacion(String value) {
        this.glosaAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad acuseRecibo.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAcuseRecibo() {
        return acuseRecibo;
    }

    /**
     * Define el valor de la propiedad acuseRecibo.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAcuseRecibo(XMLGregorianCalendar value) {
        this.acuseRecibo = value;
    }

}
