package cl.facele.robot.logica;



import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import cl.facele.robot.bean.RespaldoSiiBean;

@SuppressWarnings("serial")
public class ViewVentanaInformacion extends JFrame {

	private static JTextArea area;
	private static StringBuffer informacionProceso;
	
	/**
	 * Constructor de la Clase
	 */
	public ViewVentanaInformacion(){}
	
	private JComponent buildPanel() {
		JTabbedPane tabbedPane = new JTabbedPane();
		JTextArea area = createArea("", true, 0, new Dimension(100, 50)); 
        JScrollPane scrollPanel = new JScrollPane(area);
		JComponent componet =buildTab("",
				"fill:default:grow", scrollPanel); 
		
		tabbedPane.add("Información", componet);
		return tabbedPane;
	}

	private JTextArea createArea(
			String text,
			boolean lineWrap, 
			int columns,
			Dimension minimumSize) {
		area  = new JTextArea(text);
		area.setEditable(false);
		area.setBorder(new CompoundBorder(
				new LineBorder(Color.GRAY),
				new EmptyBorder(1, 3, 1, 1)));
		area.setLineWrap(lineWrap);
		area.setWrapStyleWord(true);
		area.setColumns(columns);
		if (minimumSize != null) {
			area.setMinimumSize(new Dimension(100, 32));
		}
		return area;
	}

	private JComponent buildTab(String title, String columnSpec, JScrollPane area) {
		JLabel columnSpecLabel = new JLabel("Respaldo Automático");
		columnSpecLabel.setHorizontalAlignment(JLabel.CENTER);

		FormLayout layout = new FormLayout(
				columnSpec, "pref, 9dlu, pref, 3dlu, fill:default:grow, 9dlu, pref");
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();
		builder.addTitle(title,           cc.xy(1, 1));
		builder.add(columnSpecLabel,      cc.xy(1, 3));
		builder.add(area,                 cc.xy(1, 5));

		return builder.getPanel();
	}

	public static void abrirVentana() {

		ViewVentanaInformacion ventana = new ViewVentanaInformacion();
		informacionProceso = new StringBuffer("").append("\n");
		JFrame frame = new JFrame();
		frame.setTitle("Proceso Respaldo Automático");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JComponent panel = ventana.buildPanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setSize(600, 500);
		frame.setVisible(true);
	}
	

	public static void setInformacion(String texto){
		informacionProceso.append(texto).append("\n");
		getArea().setText(informacionProceso.toString());
	}
	
	
	public static void setInformacionError(Map<String, String> informacionError){
		informacionProceso.append("\n").append("\n").append("\n");
		informacionProceso.append("INFORMACION ERROR RESPALDO : ").append("\n");
		for(Map.Entry<String, String> registro: informacionError.entrySet()) {
			informacionProceso.append(registro.getValue()).append("\n");
		}
		getArea().setText(informacionProceso.toString());
	}
	
	/**
	 * Constructor de la Clase
	 */
	public static void setInformacion(Map<String, RespaldoSiiBean> resultado){
		StringBuffer foliosNoProcesado = new StringBuffer();
		Map<String, RespaldoSiiBean> treeMap = new TreeMap<String, RespaldoSiiBean>(resultado);
		for (Map.Entry<String, RespaldoSiiBean> registro : treeMap.entrySet()) {
			RespaldoSiiBean bean = registro.getValue();
			if(bean.getFoliosSinProcesar() != null){
				foliosNoProcesado.append(String.format(" Documento %1$s : %2$s ( Folios sin procesar : %3$s)\n",
					bean.getDocumentoOrigen(), bean.getTipoDocumento(), bean.getFoliosSinProcesar()));
				continue;
			}
			informacionProceso.append(String.format(" Documento %1$s : %2$s ( Consultados : %3$s - Procesados : %4$d)\n",
					bean.getDocumentoOrigen(), bean.getTipoDocumento(), bean.getTotalFolios(), bean.getCantidadFolios()));
		}
		informacionProceso.append("\nFolios no procesados : \n\n"+foliosNoProcesado.toString());
		getArea().setText(informacionProceso.toString());
	}
	
	/**
	 * @return the area
	 */
	public static JTextArea getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(JTextArea area) {
		this.area = area;
	}


}