
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
 *         &lt;element name="estadoProcesamiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="glosaProcesamiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "estadoProcesamiento",
    "glosaProcesamiento"
})
@XmlRootElement(name = "UpLoadArchivoIntercambioResponse")
public class UpLoadArchivoIntercambioResponse {

    protected int estadoProcesamiento;
    @XmlElement(required = true)
    protected String glosaProcesamiento;

    /**
     * Obtiene el valor de la propiedad estadoProcesamiento.
     * 
     */
    public int getEstadoProcesamiento() {
        return estadoProcesamiento;
    }

    /**
     * Define el valor de la propiedad estadoProcesamiento.
     * 
     */
    public void setEstadoProcesamiento(int value) {
        this.estadoProcesamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad glosaProcesamiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlosaProcesamiento() {
        return glosaProcesamiento;
    }

    /**
     * Define el valor de la propiedad glosaProcesamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlosaProcesamiento(String value) {
        this.glosaProcesamiento = value;
    }

}
