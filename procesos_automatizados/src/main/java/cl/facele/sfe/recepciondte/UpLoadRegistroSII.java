
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
 *         &lt;element name="contenidoCSV" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "contenidoCSV"
})
@XmlRootElement(name = "UpLoadRegistroSII")
public class UpLoadRegistroSII {

    @XmlElement(required = true)
    protected String rutReceptor;
    @XmlElement(required = true)
    protected String contenidoCSV;

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
     * Obtiene el valor de la propiedad contenidoCSV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContenidoCSV() {
        return contenidoCSV;
    }

    /**
     * Define el valor de la propiedad contenidoCSV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContenidoCSV(String value) {
        this.contenidoCSV = value;
    }

}
