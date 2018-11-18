
package cl.facele.sfe.cargacontribuyenteselectronicos;

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
    "rutContribuyente"
})
@XmlRootElement(name = "getContribuyenteElectronico")
public class GetContribuyenteElectronico {

    @XmlElement(required = true)
    protected String rutContribuyente;

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

}
