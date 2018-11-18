
package cl.docele.ws.documentosrecibidos;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="cantidadRegistros" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="registros" type="{http://ws.docele.cl/DocumentosRecibidos/}RegistroType" maxOccurs="unbounded" minOccurs="0"/>
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
    "cantidadRegistros",
    "registros"
})
@XmlRootElement(name = "ConsultarResponse")
public class ConsultarResponse {

    @XmlElement(required = true)
    protected RespuestaOperacionType respuestaOperacion;
    protected Integer cantidadRegistros;
    protected List<RegistroType> registros;

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
     * Obtiene el valor de la propiedad cantidadRegistros.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadRegistros() {
        return cantidadRegistros;
    }

    /**
     * Define el valor de la propiedad cantidadRegistros.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadRegistros(Integer value) {
        this.cantidadRegistros = value;
    }

    /**
     * Gets the value of the registros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegistros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegistroType }
     * 
     * 
     */
    public List<RegistroType> getRegistros() {
        if (registros == null) {
            registros = new ArrayList<RegistroType>();
        }
        return this.registros;
    }

}
