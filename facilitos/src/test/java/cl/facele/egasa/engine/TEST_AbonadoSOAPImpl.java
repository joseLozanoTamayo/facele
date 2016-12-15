package cl.facele.egasa.engine;

import javax.xml.bind.JAXB;

import cl.facele.egasa.impl.AbonadoSOAPImpl;
import cl.facele.egasa.servicios.abonado.Crear;
import cl.facele.egasa.servicios.abonado.CrearResponse;

public class TEST_AbonadoSOAPImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TEST_AbonadoSOAPImpl it = new TEST_AbonadoSOAPImpl();
		it.crear();
	}

	/**
	 * 
	 */
	private void crear() {
		// TODO Auto-generated method stub
		
		AbonadoSOAPImpl port = new AbonadoSOAPImpl();
		Crear parameters = new Crear();
		CrearResponse response = port.crear(parameters );
		
		JAXB.marshal(response, System.out);
	}

	
	
	
}
