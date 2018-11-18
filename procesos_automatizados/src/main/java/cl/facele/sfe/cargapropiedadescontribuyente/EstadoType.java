
package cl.facele.sfe.cargapropiedadescontribuyente;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EstadoType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="EstadoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVO"/>
 *     &lt;enumeration value="DESACTIVADO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EstadoType")
@XmlEnum
public enum EstadoType {

    ACTIVO,
    DESACTIVADO;

    public String value() {
        return name();
    }

    public static EstadoType fromValue(String v) {
        return valueOf(v);
    }

}
