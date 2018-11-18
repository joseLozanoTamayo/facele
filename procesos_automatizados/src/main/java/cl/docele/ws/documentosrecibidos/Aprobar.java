
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
 *         &lt;element name="rutContribuyente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rutEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoDTE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="folioDTE" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="aprobacionComercial">
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                   &lt;enumeration value="Aprobado"/>
 *                   &lt;enumeration value="Aprobado_Con_Discrepancia"/>
 *                   &lt;enumeration value="Rechazado"/>
 *                 &lt;/restriction>
 *               &lt;/simpleType>
 *             &lt;/element>
 *             &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="acuseRecibo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *             &lt;element name="recinto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element name="nombreAprobador" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "rutContribuyente",
    "rutEmisor",
    "tipoDTE",
    "folioDTE",
    "aprobacionComercial",
    "motivo",
    "acuseRecibo",
    "recinto",
    "nombreAprobador"
})
@XmlRootElement(name = "Aprobar")
public class Aprobar {

    @XmlElement(required = true)
    protected String rutContribuyente;
    @XmlElement(required = true)
    protected String rutEmisor;
    protected int tipoDTE;
    protected long folioDTE;
    protected String aprobacionComercial;
    protected String motivo;
    protected Boolean acuseRecibo;
    protected String recinto;
    @XmlElement(required = true)
    protected String nombreAprobador;

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

    /**
     * Obtiene el valor de la propiedad rutEmisor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutEmisor() {
        return rutEmisor;
    }

    /**
     * Define el valor de la propiedad rutEmisor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutEmisor(String value) {
        this.rutEmisor = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDTE.
     * 
     */
    public int getTipoDTE() {
        return tipoDTE;
    }

    /**
     * Define el valor de la propiedad tipoDTE.
     * 
     */
    public void setTipoDTE(int value) {
        this.tipoDTE = value;
    }

    /**
     * Obtiene el valor de la propiedad folioDTE.
     * 
     */
    public long getFolioDTE() {
        return folioDTE;
    }

    /**
     * Define el valor de la propiedad folioDTE.
     * 
     */
    public void setFolioDTE(long value) {
        this.folioDTE = value;
    }

    /**
     * Obtiene el valor de la propiedad aprobacionComercial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAprobacionComercial() {
        return aprobacionComercial;
    }

    /**
     * Define el valor de la propiedad aprobacionComercial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAprobacionComercial(String value) {
        this.aprobacionComercial = value;
    }

    /**
     * Obtiene el valor de la propiedad motivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Define el valor de la propiedad motivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Obtiene el valor de la propiedad acuseRecibo.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAcuseRecibo() {
        return acuseRecibo;
    }

    /**
     * Define el valor de la propiedad acuseRecibo.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAcuseRecibo(Boolean value) {
        this.acuseRecibo = value;
    }

    /**
     * Obtiene el valor de la propiedad recinto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecinto() {
        return recinto;
    }

    /**
     * Define el valor de la propiedad recinto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecinto(String value) {
        this.recinto = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreAprobador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAprobador() {
        return nombreAprobador;
    }

    /**
     * Define el valor de la propiedad nombreAprobador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAprobador(String value) {
        this.nombreAprobador = value;
    }

}
