package cl.facele.robot.conexion;

import java.nio.file.Path;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cl.facele.robot.utilidades.Constantes;
import cl.facele.robot.utilidades.Utils;

/**
 * Clase ConexionChrome que administra conexion realizada a la pagina web 
 * por medio de selenium
 */
public class ConexionChrome {

	private WebDriver driver;
	
	/**
	 * Constructor de la clase ConexionChrome
	 */
	public ConexionChrome(String url, String path) throws Exception {
		
		System.out.println("Path " + path);
		Path rutaDescarga = Constantes.HOME_DIR.resolve(path);
		Utils.verificarDireccionDriver();
		Utils.isVerificarFileDescarga(rutaDescarga.toString());
		
		if(Constantes.SISTEMA_OP.toLowerCase().contains("win"))
			System.setProperty("webdriver.chrome.driver",Constantes.DIR_DRIVER_WINDOW);
		else
			System.setProperty("webdriver.chrome.driver",Constantes.DIR_DRIVER_LINUX);
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", rutaDescarga.toString());
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setJavascriptEnabled(true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new ChromeDriver(cap);
		driver.get(url);
	}

	/**
	 * Obtine la propiedad del driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Establece la propiedad de Driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void quit(){
		if(driver != null){
			getDriver().close();
			getDriver().quit();
		}
	}
	
}
