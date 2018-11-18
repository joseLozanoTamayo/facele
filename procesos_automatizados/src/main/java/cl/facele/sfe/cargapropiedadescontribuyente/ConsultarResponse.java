
package cl.facele.sfe.cargapropiedadescontribuyente;

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
 *         &lt;element name="estadoProcesamiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="glosaProcesamiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="registros" type="{http://facele.cl/sfe/CargaPropiedadesContribuyente/}ContribuyenteType" maxOccurs="unbounded" minOccurs="0"/>
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
    "glosaProcesamiento",
    "registros"
})
@XmlRootElement(name = "consultarResponse")
public class ConsultarResponse {

    protected int estadoProcesamiento;
    @XmlElement(required = true)
    protected String glosaProcesamiento;
    protected List<ContribuyenteType> registros;

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
     * {@link ContribuyenteType }
     * 
     * 
     */
    public List<ContribuyenteType> getRegistros() {
        if (registros == null) {
            registros = new ArrayList<ContribuyenteType>();
        }
        return this.registros;
    }

}
