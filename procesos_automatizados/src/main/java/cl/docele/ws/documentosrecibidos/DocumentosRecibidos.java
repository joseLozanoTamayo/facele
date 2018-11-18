
package cl.docele.ws.documentosrecibidos;

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
@WebService(name = "DocumentosRecibidos", targetNamespace = "http://ws.docele.cl/DocumentosRecibidos/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DocumentosRecibidos {


    /**
     * 
     * @param parameters
     * @return
     *     returns cl.docele.ws.documentosrecibidos.ConsultarResponse
     */
    @WebMethod(operationName = "Consultar", action = "http://ws.docele.cl/DocumentosRecibidos/Consultar")
    @WebResult(name = "ConsultarResponse", targetNamespace = "http://ws.docele.cl/DocumentosRecibidos/", partName = "parameters")
    public ConsultarResponse consultar(
        @WebParam(name = "Consultar", targetNamespace = "http://ws.docele.cl/DocumentosRecibidos/", partName = "parameters")
        Consultar parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cl.docele.ws.documentosrecibidos.ObtenerResponse
     */
    @WebMethod(operationName = "Obtener", action = "http://ws.docele.cl/DocumentosRecibidos/Obtener")
    @WebResult(name = "ObtenerResponse", targetNamespace = "http://ws.docele.cl/DocumentosRecibidos/", partName = "parameters")
    public ObtenerResponse obtener(
        @WebParam(name = "Obtener", targetNamespace = "http://ws.docele.cl/DocumentosRecibidos/", partName = "parameters")
        Obtener parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cl.docele.ws.documentosrecibidos.AprobarResponse
     */
    @WebMethod(operationName = "Aprobar", action = "http://ws.docele.cl/DocumentosRecibidos/Aprobar")
    @WebResult(name = "AprobarResponse", targetNamespace = "http://ws.docele.cl/DocumentosRecibidos/", partName = "parameters")
    public AprobarResponse aprobar(
        @WebParam(name = "Aprobar", targetNamespace = "http://ws.docele.cl/DocumentosRecibidos/", partName = "parameters")
        Aprobar parameters);

}