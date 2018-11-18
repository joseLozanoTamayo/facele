
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
 *         &lt;element name="emisoresElectronicos" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "emisoresElectronicos"
})
@XmlRootElement(name = "doCargaContribuyentesElectronicos")
public class DoCargaContribuyentesElectronicos {

    @XmlElement(required = true)
    protected String emisoresElectronicos;

    /**
     * Obtiene el valor de la propiedad emisoresElectronicos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisoresElectronicos() {
        return emisoresElectronicos;
    }

    /**
     * Define el valor de la propiedad emisoresElectronicos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisoresElectronicos(String value) {
        this.emisoresElectronicos = value;
    }

}
