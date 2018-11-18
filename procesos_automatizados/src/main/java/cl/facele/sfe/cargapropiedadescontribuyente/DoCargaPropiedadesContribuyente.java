
package cl.facele.sfe.cargapropiedadescontribuyente;

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
 *         &lt;element name="contribuyente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="propiedades" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "contribuyente",
    "propiedades"
})
@XmlRootElement(name = "doCargaPropiedadesContribuyente")
public class DoCargaPropiedadesContribuyente {

    @XmlElement(required = true)
    protected String contribuyente;
    @XmlElement(required = true)
    protected String propiedades;

    /**
     * Obtiene el valor de la propiedad contribuyente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContribuyente() {
        return contribuyente;
    }

    /**
     * Define el valor de la propiedad contribuyente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContribuyente(String value) {
        this.contribuyente = value;
    }

    /**
     * Obtiene el valor de la propiedad propiedades.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropiedades() {
        return propiedades;
    }

    /**
     * Define el valor de la propiedad propiedades.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropiedades(String value) {
        this.propiedades = value;
    }

}
