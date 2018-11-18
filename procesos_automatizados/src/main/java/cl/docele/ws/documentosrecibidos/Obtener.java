
package cl.docele.ws.documentosrecibidos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="rutEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoDTE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="folioDTE" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="formato">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="PDF"/>
 *               &lt;enumeration value="XML"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
    "formato"
})
@XmlRootElement(name = "Obtener")
public class Obtener {

    @XmlElement(required = true)
    protected String rutContribuyente;
    @XmlElement(required = true)
    protected String rutEmisor;
    protected int tipoDTE;
    protected long folioDTE;
    @XmlElement(required = true)
    protected String formato;

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
     * Obtiene el valor de la propiedad formato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormato() {
        return formato;
    }

    /**
     * Define el valor de la propiedad formato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormato(String value) {
        this.formato = value;
    }

}
