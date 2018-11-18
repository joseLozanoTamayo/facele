
package cl.docele.ws.documentosrecibidos;

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
 *         &lt;element name="rutContribuyente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rutEmisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDTE" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="folioDTE" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="periodoEmision" type="{http://www.w3.org/2001/XMLSchema}gYearMonth"/>
 *           &lt;element name="fechaEmision" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;/choice>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="periodoRecepcion" type="{http://www.w3.org/2001/XMLSchema}gYearMonth"/>
 *           &lt;element name="fechaRecepcion" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;/choice>
 *         &lt;element name="offset" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "rutContribuyente",
    "rutEmisor",
    "tipoDTE",
    "folioDTE",
    "periodoEmision",
    "fechaEmision",
    "periodoRecepcion",
    "fechaRecepcion",
    "offset"
})
@XmlRootElement(name = "Consultar")
public class Consultar {

    @XmlElement(required = true)
    protected String rutContribuyente;
    protected String rutEmisor;
    protected Integer tipoDTE;
    protected Long folioDTE;
    @XmlSchemaType(name = "gYearMonth")
    protected XMLGregorianCalendar periodoEmision;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEmision;
    @XmlSchemaType(name = "gYearMonth")
    protected XMLGregorianCalendar periodoRecepcion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRecepcion;
    protected Integer offset;

    /**
     * Obtiene el valor de la propiedad rutContribuyente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutContribuyente() {
        return rutContribuyente;
    }

    /**
     * Define el valor de la propiedad rutContribuyente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutContribuyente(String value) {
        this.rutContribuyente = value;
    }

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
     * Obtiene el valor de la propiedad tipoDTE.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoDTE() {
        return tipoDTE;
    }

    /**
     * Define el valor de la propiedad tipoDTE.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoDTE(Integer value) {
        this.tipoDTE = value;
    }

    /**
     * Obtiene el valor de la propiedad folioDTE.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFolioDTE() {
        return folioDTE;
    }

    /**
     * Define el valor de la propiedad folioDTE.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFolioDTE(Long value) {
        this.folioDTE = value;
    }

    /**
     * Obtiene el valor de la propiedad periodoEmision.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPeriodoEmision() {
        return periodoEmision;
    }

    /**
     * Define el valor de la propiedad periodoEmision.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPeriodoEmision(XMLGregorianCalendar value) {
        this.periodoEmision = value;
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
     * Obtiene el valor de la propiedad periodoRecepcion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPeriodoRecepcion() {
        return periodoRecepcion;
    }

    /**
     * Define el valor de la propiedad periodoRecepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPeriodoRecepcion(XMLGregorianCalendar value) {
        this.periodoRecepcion = value;
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
     * Obtiene el valor de la propiedad offset.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * Define el valor de la propiedad offset.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOffset(Integer value) {
        this.offset = value;
    }

}
