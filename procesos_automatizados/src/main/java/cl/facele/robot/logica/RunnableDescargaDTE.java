package cl.facele.robot.logica;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import cl.facele.robot.utilidades.Constantes;

public class RunnableDescargaDTE implements Runnable{
	private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public void run() {
		RobotLogica robot = null;
		try {
			int estado = ViewDescargaDTE.createAndShowGUI();
			
			if(estado == JOptionPane.CANCEL_OPTION || estado == JOptionPane.CLOSED_OPTION){
				logger.info("DESCARGA A SIDO CANCELADO, CERRANDO VENTANA PROCESO");
				System.exit(0);
			}

			String rutContribuyente = ViewDescargaDTE
					.getRutContribuyente().getText().replaceAll("\\.", ""); 
			
			robot = new RobotLogica(rutContribuyente + "/" + ViewDescargaDTE
					.getListaProceso()[ViewDescargaDTE.getProceso().getSelectedIndex()]+"/");
			Boolean loginCertificado = true;
			robot.login( "", "", loginCertificado);
			Thread.sleep(2000);
			
			if(Constantes.SISTEMA_OP.toLowerCase().contains("win"))
				JOptionPane.showOptionDialog(null, Constantes.GENERAL_CONSTANTES.getString("MESSAGE.INFO.LOGIN"),
						"Información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

			switch(ViewDescargaDTE.getProceso().getSelectedIndex()) {
			case 0:
			case 1:
				RespaldoSIILogica respaldoDTE = new RespaldoSIILogica(robot.getConexion());
				respaldoDTE.generarRespaldo(rutContribuyente, ViewDescargaDTE
						.getListaProceso()[ViewDescargaDTE.getProceso().getSelectedIndex()]);
				break;
			case 2:
				RespaldoLibrosSIILogica respaldoLibros = new RespaldoLibrosSIILogica(robot.getConexion());
				respaldoLibros.generarRespaldo(rutContribuyente);
				break;
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		} 
		finally {
			Constantes.EXECUTOR.shutdown();
			try {
			if(robot != null)
				robot.cerrar();
			robot = null;
			} catch(Exception e){
				logger.info("Chrome cerrado de forma manual o de forma automatica");
			}
		}
		
	}

}
