package cl.facele.robot.logica;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import cl.facele.robot.conexion.ConexionChrome;
import cl.facele.robot.utilidades.Constantes;

/**
 * Clase logica que interactua con pagina web a procesar.
 */
public class RobotLogica {

	private ConexionChrome conexion;

	/**
	 * Constructor Clase RobotLogica
	 */
	public RobotLogica(ConexionChrome conexion){
		this.conexion = conexion;
	}

	/**
	 * Constructor por defecto clase RobotLogica
	 */
	public RobotLogica() throws Exception{
		this.conexion = new ConexionChrome(Constantes.GENERAL_CONSTANTES.getString("URL.LOGIN"), 
				Constantes.GENERAL_CONSTANTES.getString("DIR.DESCARGA")	);
	}
	
	/**
	 * Constructor por defecto clase RobotLogica
	 */
	public RobotLogica(String pathDescarga) throws Exception{
		this.conexion = new ConexionChrome(Constantes.GENERAL_CONSTANTES.getString("URL.LOGIN"), pathDescarga);
	}
	
	
	/**
	 * Método que loguea en pagina de impuestos internos
	 */
	protected void login(String usuario, String pass, boolean isCertificado) throws Exception {
		try {
			if(!isCertificado){
				conexion.getDriver().findElement(By.cssSelector("#rutcntr")).sendKeys(usuario);
				conexion.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				conexion.getDriver().findElement(By.cssSelector("#clave")).sendKeys(pass);
				conexion.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				conexion.getDriver().findElement(By.xpath("//button[contains(.,'Ingresar')]") ).click();	
			} else {
				conexion.getDriver().findElement(By.xpath("//a[contains(.,'Acceso con certificado digital')]") ).click();
				Alert alert = conexion.getDriver().switchTo().alert();
				Thread.sleep(2000);
				alert.accept();
			}
		} catch (Exception e) {
			throw new Exception("Error Login : " + e.getMessage(), e);
		} 
	}

	/**
	 * Método que loguea en pagina de impuestos internos
	 */
	public void login() throws Exception {
		try {
			conexion.getDriver().findElement(By.xpath("//a[contains(.,'Acceso con certificado digital')]") ).click();
			Alert alert = conexion.getDriver().switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			throw new Exception("Error Login : " + e.getMessage(), e);
		} 
	}

	/**
	 * 
	 */
	public void cerrar() {
		if(this.conexion != null)
			this.conexion.quit();
	}

	/**
	 * @return the conexion
	 */
	public ConexionChrome getConexion() {
		return conexion;
	}

}
