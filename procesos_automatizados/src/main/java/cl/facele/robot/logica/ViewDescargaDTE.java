package cl.facele.robot.logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

@SuppressWarnings("rawtypes")
public class ViewDescargaDTE extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = -7215321269502496389L;
    private static com.toedter.calendar.JDateChooser fecha;
    private static javax.swing.JLabel labelAmbiente;
    private static javax.swing.JLabel labelFecha;
    private static javax.swing.JLabel labelInfo;
    private static javax.swing.JLabel labelRut;
    private static javax.swing.JLabel notaCertificado;
    private static javax.swing.JComboBox<String> proceso;
    private static javax.swing.JTextField rutContribuyente;

	private static String[] listaProceso = {
			"Emitidos",
			"Recibidos",
			"Libros"
	};

	/**
	 * Creates new form Ventana1
	 */
	public ViewDescargaDTE() {
		initComponents();
	}

//	/**
//	 * init componentes
//	 */
//	@SuppressWarnings("unchecked")
//	private void initComponents() {
//
//		labelRut = new javax.swing.JLabel();
//		rutContribuyente = new javax.swing.JTextField();
//		labelAmbiente = new javax.swing.JLabel();
//		certificadoLogin = new javax.swing.JRadioButton();
//		notaCertificado = new javax.swing.JLabel();
//		labelFecha = new javax.swing.JLabel();
//		fecha = new com.toedter.calendar.JDateChooser();
//		labelInfo = new javax.swing.JLabel();
//
//		proceso = new JComboBox(listaProceso);
//		proceso.setEditable(false);
//		proceso.addActionListener(this);
//
//		certificadoLogin.setText("login certificado");
//		labelRut.setText("RUT ");
//        labelAmbiente.setText("Ambiente");
//
//        certificadoLogin.setText("login certificado");
//        certificadoLogin.setSelected(true);
//        certificadoLogin.setEnabled(false);
//
//        notaCertificado.setForeground(new java.awt.Color(167, 14, 14));
//        notaCertificado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        notaCertificado.setText("Recuerde, carga certificado en su explorador");
//
//        labelFecha.setText("Fecha");
//
//        fecha.setDateFormatString("yyyy-MM-dd");
//
//        labelInfo.setText("Respaldo DTE, ante SII.");
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(notaCertificado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
//                            .addComponent(labelRut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                            .addComponent(labelFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                            .addComponent(labelAmbiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
//                        .addGap(25, 25, 25)
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(proceso, 0, 270, Short.MAX_VALUE)
//                            .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                            .addComponent(rutContribuyente)))
//                    .addGroup(layout.createSequentialGroup()
//                        .addComponent(certificadoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(0, 0, Short.MAX_VALUE)))
//                .addContainerGap())
//            .addComponent(labelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(9, 9, 9)
//                .addComponent(labelInfo)
//                .addGap(18, 18, 18)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(proceso, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(labelAmbiente))
//                .addGap(18, 18, 18)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                        .addComponent(labelFecha)
//                        .addGap(8, 8, 8)))
//                .addGap(18, 18, 18)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(rutContribuyente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(labelRut))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                .addComponent(certificadoLogin)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(notaCertificado)
//                .addContainerGap(56, Short.MAX_VALUE))
//        );
//    }// </editor-fold>//GEN-END:initComponents 
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        labelRut = new javax.swing.JLabel();
        rutContribuyente = new javax.swing.JTextField();
        labelAmbiente = new javax.swing.JLabel();
        proceso = new javax.swing.JComboBox<>();
        notaCertificado = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        labelInfo = new javax.swing.JLabel();

        labelRut.setText("RUT ");

        proceso = new JComboBox(listaProceso);
		proceso.setEditable(false);
		proceso.addActionListener(this);

        labelAmbiente.setText("Ambiente");

        notaCertificado.setForeground(new java.awt.Color(167, 14, 14));
        notaCertificado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notaCertificado.setText("Recuerde, carga certificado en su explorador");

        labelFecha.setText("Fecha");

        fecha.setDateFormatString("yyyy-MM-dd");

        labelInfo.setText("Respaldo DTE, ante SII.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notaCertificado, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelRut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAmbiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proceso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rutContribuyente))
                .addContainerGap())
            .addComponent(labelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(labelInfo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proceso, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAmbiente))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rutContribuyente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRut))
                .addGap(42, 42, 42)
                .addComponent(notaCertificado)
                .addContainerGap(74, Short.MAX_VALUE))
        );
    }// </editor-fold> 

	/**
	 * Metodo inicializador venttana
	 * @return
	 */
	public static int createAndShowGUI() {
		JComponent newContentPane = new ViewDescargaDTE();
		return JOptionPane.showConfirmDialog(null, newContentPane, 
				"Inicio Respaldo Documento", JOptionPane.OK_CANCEL_OPTION);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the listaProceso
	 */
	public static String[] getListaProceso() {
		return listaProceso;
	}


	/**
	 * @param listaProceso the listaProceso to set
	 */
	public static void setListaProceso(String[] listaProceso) {
		ViewDescargaDTE.listaProceso = listaProceso;
	}


	/**
	 * @return the proceso
	 */
	public static javax.swing.JComboBox getProceso() {
		return proceso;
	}


	/**
	 * @param proceso the proceso to set
	 */
	@SuppressWarnings("unchecked")
	public static void setProceso(javax.swing.JComboBox proceso) {
		ViewDescargaDTE.proceso = proceso;
	}

	/**
	 * @return the rutContribuyente
	 */
	public static javax.swing.JTextField getRutContribuyente() {
		return rutContribuyente;
	}

	/**
	 * @param rutContribuyente the rutContribuyente to set
	 */
	public static void setRutContribuyente(javax.swing.JTextField rutContribuyente) {
		ViewDescargaDTE.rutContribuyente = rutContribuyente;
	}

	/**
	 * @return the fecha
	 */
	public static com.toedter.calendar.JDateChooser getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public static void setFecha(com.toedter.calendar.JDateChooser fecha) {
		ViewDescargaDTE.fecha = fecha;
	}

}
