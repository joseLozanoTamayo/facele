package cl.facele.robot.utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import cl.facele.robot.bean.RespaldoSiiBean;

/**
 * Clase contenedora de metodos generales usables en la la aplicacion en general
 */
public class Utils {


	/**
	 * Método que verifica el la ruta del driver
	 */
	public static void verificarDireccionDriver() throws Exception {
		File file = null;
		System.out.println(" RUTA CHOME DRIVER : " + Constantes.DIR_DRIVER_WINDOW);
		if(Constantes.SISTEMA_OP.toLowerCase().contains("win"))
			file = new File(Constantes.DIR_DRIVER_WINDOW);
		else
			file = new File(Constantes.DIR_DRIVER_LINUX);

		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, Constantes.GENERAL_CONSTANTES.getString("MESSAGE.INFO.DRIVER"));
			throw new Exception("Driver no Encontrado.");
		}
	}

	/**
	 * Método que crea directorio de descarga
	 */
	public static void isVerificarFileDescarga(String rutaDescarga){
		System.out.println("Path " + rutaDescarga);
		File rutaDescargaFile = new File(rutaDescarga);
		if(!rutaDescargaFile.exists()){
			rutaDescargaFile.mkdirs();
		}
		for(File file: rutaDescargaFile.listFiles())
			file.delete();
	}
	
	/**
	 * 
	 */
	public static List<RespaldoSiiBean> dividirFolios(RespaldoSiiBean beanFolios){
		List<RespaldoSiiBean> folios = new ArrayList<RespaldoSiiBean>();
		Long media = 0L;

		if(beanFolios.getFolioHasta() <= 0 ) 
			return folios;

		media = (beanFolios.getFolioDesde() + beanFolios.getFolioHasta() ) / 2;

		RespaldoSiiBean bean = new RespaldoSiiBean();
		bean.setTexto(beanFolios.getTexto());
		bean.setDocumentoOrigen(beanFolios.getDocumentoOrigen());
		bean.setTipoDocumento(beanFolios.getTipoDocumento());
		bean.setFolioDesde(beanFolios.getFolioDesde() );
		bean.setFolioHasta(media);
		folios.add(bean);

		bean = new RespaldoSiiBean();
		bean.setTexto(beanFolios.getTexto());
		bean.setDocumentoOrigen(beanFolios.getDocumentoOrigen());
		bean.setTipoDocumento(beanFolios.getTipoDocumento());
		bean.setFolioDesde(media + 1);
		bean.setFolioHasta(beanFolios.getFolioHasta());
		folios.add(bean);

		return folios;
	}

	/**
	 * Metodo booleano que retorna true si en la trama contiene numero mayor a cero
	 * de lo contrario retorna false.
	 */
	public static int validarCantidadConsulta(String resultado){
		String dato[];
		String datoAux[];
		int consulta = 0;
		try{
			dato = resultado.split(":");
			if(dato[1].trim().contains(".")) {
				datoAux = dato[1].trim().split("\\.");
				consulta = Integer.parseInt(datoAux[0].trim()) ;
			} else	
				consulta = Integer.parseInt(dato[1].trim()) ;

		}catch (Exception e) {
			consulta = 0;
		}
		return consulta;
	}

	/**
	 * 
	 */
	public static List<String> verificarListRut(List<String> listaTabla) {
		List<String> listaFolios = new ArrayList<String>();
		boolean existe = false;
		for(String rut: listaTabla){
			existe = false;
			for(String asignacion: listaFolios){
				if(asignacion.equals(rut))
					existe = true;
			}
			if(!existe)
				listaFolios.add(rut);
		}
		return listaFolios;
	}


	/**
	 * 
	 */
	public static void main(String [] args) throws Exception{

		// Call String.format with three integer codes.
		String result = String.format("Registro %1$s : %2$s ( Consultados : %3$d - Procesados : %4$d)",
				"Emitido", "Facturacion Electronica", 9999999999L, 9999999999L);
		
		System.out.println(result);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "Jan");
		map.put("2", "Feb");
		map.put("3", "Mar");
		
		if(map.get("4") == null)
			System.out.println("Id no encontrado");

		//loop a Map
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}
		
	}


}
