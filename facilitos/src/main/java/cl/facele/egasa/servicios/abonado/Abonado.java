
package cl.facele.egasa.servicios.abonado;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b21 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Abonado", targetNamespace = "http://facele.cl/egasa/servicios/Abonado/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Abonado {


    /**
     * 
     * @param parameters
     * @return
     *     returns cl.facele.egasa.servicios.abonado.CrearResponse
     */
    @WebMethod(operationName = "Crear", action = "http://facele.cl/egasa/servicios/Abonado/Crear")
    @WebResult(name = "CrearResponse", targetNamespace = "http://facele.cl/egasa/servicios/Abonado/", partName = "parameters")
    public CrearResponse crear(
        @WebParam(name = "Crear", targetNamespace = "http://facele.cl/egasa/servicios/Abonado/", partName = "parameters")
        Crear parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cl.facele.egasa.servicios.abonado.ObtenerResponse
     */
    @WebMethod(operationName = "Obtener", action = "http://facele.cl/egasa/servicios/Abonado/Obtener")
    @WebResult(name = "ObtenerResponse", targetNamespace = "http://facele.cl/egasa/servicios/Abonado/", partName = "parameters")
    public ObtenerResponse obtener(
        @WebParam(name = "Obtener", targetNamespace = "http://facele.cl/egasa/servicios/Abonado/", partName = "parameters")
        Obtener parameters);

}
