
package cl.facele.sfe.recepciondte;

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
 *         &lt;element name="rutReceptor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xml" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
    "xml"
})
@XmlRootElement(name = "UpLoadArchivoIntercambio")
public class UpLoadArchivoIntercambio {

    @XmlElement(required = true)
    protected String rutReceptor;
    @XmlElement(required = true)
    protected byte[] xml;

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
     * Obtiene el valor de la propiedad xml.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getXml() {
        return xml;
    }

    /**
     * Define el valor de la propiedad xml.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setXml(byte[] value) {
        this.xml = value;
    }

}
