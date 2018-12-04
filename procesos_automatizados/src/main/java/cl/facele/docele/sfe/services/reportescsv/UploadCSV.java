
package cl.facele.docele.sfe.services.reportescsv;

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
 *         &lt;element name="rutAbonado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bytesCSV" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="inline" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "rutAbonado",
    "bytesCSV",
    "inline"
})
@XmlRootElement(name = "UploadCSV")
public class UploadCSV {

    @XmlElement(required = true)
    protected String rutAbonado;
    @XmlElement(required = true)
    protected byte[] bytesCSV;
    protected boolean inline;

    /**
     * Obtiene el valor de la propiedad rutAbonado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutAbonado() {
        return rutAbonado;
    }

    /**
     * Define el valor de la propiedad rutAbonado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutAbonado(String value) {
        this.rutAbonado = value;
    }

    /**
     * Obtiene el valor de la propiedad bytesCSV.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBytesCSV() {
        return bytesCSV;
    }

    /**
     * Define el valor de la propiedad bytesCSV.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBytesCSV(byte[] value) {
        this.bytesCSV = value;
    }

    /**
     * Obtiene el valor de la propiedad inline.
     * 
     */
    public boolean isInline() {
        return inline;
    }

    /**
     * Define el valor de la propiedad inline.
     * 
     */
    public void setInline(boolean value) {
        this.inline = value;
    }

}
