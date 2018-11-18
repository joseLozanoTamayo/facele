
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
 *         &lt;element name="respuestaOperacion" type="{http://ws.docele.cl/DocumentosRecibidos/}RespuestaOperacionType"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="XML" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="PDF" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;/choice>
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
    "respuestaOperacion",
    "xml",
    "pdf"
})
@XmlRootElement(name = "ObtenerResponse")
public class ObtenerResponse {

    @XmlElement(required = true)
    protected RespuestaOperacionType respuestaOperacion;
    @XmlElement(name = "XML")
    protected String xml;
    @XmlElement(name = "PDF")
    protected byte[] pdf;

    /**
     * Obtiene el valor de la propiedad respuestaOperacion.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaOperacionType }
     *     
     */
    public RespuestaOperacionType getRespuestaOperacion() {
        return respuestaOperacion;
    }

    /**
     * Define el valor de la propiedad respuestaOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaOperacionType }
     *     
     */
    public void setRespuestaOperacion(RespuestaOperacionType value) {
        this.respuestaOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad xml.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXML() {
        return xml;
    }

    /**
     * Define el valor de la propiedad xml.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXML(String value) {
        this.xml = value;
    }

    /**
     * Obtiene el valor de la propiedad pdf.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPDF() {
        return pdf;
    }

    /**
     * Define el valor de la propiedad pdf.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPDF(byte[] value) {
        this.pdf = value;
    }

}
