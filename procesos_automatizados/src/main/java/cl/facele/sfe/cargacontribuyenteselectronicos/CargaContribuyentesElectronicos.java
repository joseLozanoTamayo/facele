
package cl.facele.sfe.cargacontribuyenteselectronicos;

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
@WebService(name = "CargaContribuyentesElectronicos", targetNamespace = "http://facele.cl/sfe/CargaContribuyentesElectronicos/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CargaContribuyentesElectronicos {


    /**
     * 
     * @param parameters
     * @return
     *     returns cl.facele.sfe.cargacontribuyenteselectronicos.DoCargaContribuyentesElectronicosResponse
     */
    @WebMethod(action = "http://facele.cl/sfe/CargaContribuyentesElectronicos/doCargaContribuyentesElectronicos")
    @WebResult(name = "doCargaContribuyentesElectronicosResponse", targetNamespace = "http://facele.cl/sfe/CargaContribuyentesElectronicos/", partName = "parameters")
    public DoCargaContribuyentesElectronicosResponse doCargaContribuyentesElectronicos(
        @WebParam(name = "doCargaContribuyentesElectronicos", targetNamespace = "http://facele.cl/sfe/CargaContribuyentesElectronicos/", partName = "parameters")
        DoCargaContribuyentesElectronicos parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cl.facele.sfe.cargacontribuyenteselectronicos.GetContribuyenteElectronicoResponse
     */
    @WebMethod(action = "http://facele.cl/sfe/CargaContribuyentesElectronicos/getContribuyenteElectronico")
    @WebResult(name = "getContribuyenteElectronicoResponse", targetNamespace = "http://facele.cl/sfe/CargaContribuyentesElectronicos/", partName = "parameters")
    public GetContribuyenteElectronicoResponse getContribuyenteElectronico(
        @WebParam(name = "getContribuyenteElectronico", targetNamespace = "http://facele.cl/sfe/CargaContribuyentesElectronicos/", partName = "parameters")
        GetContribuyenteElectronico parameters);

}
