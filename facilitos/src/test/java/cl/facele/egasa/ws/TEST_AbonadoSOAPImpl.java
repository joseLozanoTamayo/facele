package cl.facele.egasa.ws;

import java.net.URL;

import javax.xml.bind.JAXB;

import cl.facele.egasa.servicios.abonado.Abonado;
import cl.facele.egasa.servicios.abonado.Abonado_Service;
import cl.facele.egasa.servicios.abonado.Crear;
import cl.facele.egasa.servicios.abonado.CrearResponse;

public class TEST_AbonadoSOAPImpl {

	private static Abonado_Service service;
	private static URL wsdlLocation;
	private static Abonado port;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		wsdlLocation = new URL("http://localhost:8085/facilitos/AbonadoService?wsdl");
		service = new Abonado_Service(wsdlLocation);
		port = service.getAbonadoSOAP();
		TEST_AbonadoSOAPImpl it = new TEST_AbonadoSOAPImpl();
		it.crear();
	}

	/**
	 * 
	 */
	private void crear() {
		// TODO Auto-generated method stub
		Crear parameters = new Crear();
		parameters.setDepartamento("asdf");
		parameters.setDireccion("asdf");
		parameters.setDistrito("asdf");
		parameters.setNombreComercial("adfadsf");
		parameters.setProvincia("asdfadsf");
		parameters.setRazonSocial("adsfdsf");
		parameters.setRuc("adsfadf");
		parameters.setUbigeo(22);
		parameters.setUrbanizacion("asdfasdf");
		CrearResponse response = port.crear(parameters );
		
		JAXB.marshal(response, System.out);
	}

	
	
	
}
