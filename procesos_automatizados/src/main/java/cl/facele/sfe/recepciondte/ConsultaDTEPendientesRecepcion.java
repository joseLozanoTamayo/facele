
package cl.facele.sfe.recepciondte;

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
 *         &lt;element name="rutReceptor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rutEmisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDTE" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="periodoEmision" type="{http://www.w3.org/2001/XMLSchema}gYearMonth" minOccurs="0"/>
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
    "rutReceptor",
    "rutEmisor",
    "tipoDTE",
    "folio",
    "periodoEmision",
    "offset"
})
@XmlRootElement(name = "ConsultaDTEPendientesRecepcion")
public class ConsultaDTEPendientesRecepcion {

    @XmlElement(required = true)
    protected String rutReceptor;
    protected String rutEmisor;
    protected Integer tipoDTE;
    protected Long folio;
    @XmlSchemaType(name = "gYearMonth")
    protected XMLGregorianCalendar periodoEmision;
    protected Integer offset;

    /**
     * Obtiene el valor de la propiedad rutReceptor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutReceptor() {
        return rutReceptor;
    }

    /**
     * Define el valor de la propiedad rutReceptor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutReceptor(String value) {
        this.rutReceptor = value;
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
     * Obtiene el valor de la propiedad folio.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFolio() {
        return folio;
    }

    /**
     * Define el valor de la propiedad folio.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFolio(Long value) {
        this.folio = value;
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
