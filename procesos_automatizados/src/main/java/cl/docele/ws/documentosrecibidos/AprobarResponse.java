
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
    "respuestaOperacion"
})
@XmlRootElement(name = "AprobarResponse")
public class AprobarResponse {

    @XmlElement(required = true)
    protected RespuestaOperacionType respuestaOperacion;

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

}
