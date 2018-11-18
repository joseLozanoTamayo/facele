package cl.facele.robot.utilidades;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Clase de valores constantes que se usaran en el sistema
 * como datos predeterminados
 */
public class Constantes {

	public static final ResourceBundle GENERAL_CONSTANTES = ResourceBundle.getBundle("constantes");
	public static final AtomicLong LONG = new AtomicLong(1);
	public static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
	public static final String DIR_DRIVER_LINUX = System.getProperty("user.home") + GENERAL_CONSTANTES.getString("DRIVER.CHROME");
	public static final String DIR_DRIVER_WINDOW = System.getProperty("user.home") + GENERAL_CONSTANTES.getString("DRIVER.CHROME.WINDOW");
	public static final String SISTEMA_OP = System.getProperty("os.name");
	public static final Path HOME_DIR = Paths.get(System.getProperty("user.home"), "Facele", "Respaldo");

	public static String[] AMBIENTE = null;
	public static Object[][] TABLE_AMBIENTE = null;

	static {
		AMBIENTE = GENERAL_CONSTANTES.getString("LISTA.AMBIENTE")
				.replaceAll(" ", "").split("\\,"); 
		
		int lentAmbiente = AMBIENTE.length;
		TABLE_AMBIENTE = new Object[lentAmbiente][2];
		for(int rowAmbiente = 0; rowAmbiente < lentAmbiente; rowAmbiente++) {
			TABLE_AMBIENTE[rowAmbiente][0] = new Boolean(false);
			TABLE_AMBIENTE[rowAmbiente][1] = AMBIENTE[rowAmbiente];
		}
	}

	public static enum MARCA_TIEMPO{
		FECHA,
		PERIODO;
	}

}
