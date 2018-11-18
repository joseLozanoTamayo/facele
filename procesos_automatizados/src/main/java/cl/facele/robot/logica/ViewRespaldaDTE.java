package cl.facele.robot.logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cl.facele.robot.utilidades.Constantes;

/**
 * Clase contenedora View Dialog
 *
 */
@SuppressWarnings("rawtypes")
public class ViewRespaldaDTE extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = -7215321269502496389L;
	private static javax.swing.JComboBox proceso;
	private static com.toedter.calendar.JDateChooser fecha;
	private javax.swing.JLabel labelFecha;
	private javax.swing.JLabel labelInfo;
	private javax.swing.JLabel labelRut;
	private javax.swing.JLabel labelAmbiente;
	private javax.swing.JLabel labelPeriodo;
	private static javax.swing.JTextField rutContribuyente;
	private static javax.swing.JTextField periodo;
	private javax.swing.JLabel notaCertificado;


	/**
	 * Creates new form Ventana1
	 */
	public ViewRespaldaDTE() {
		initComponents();
	}

	/**
	 * init componentes
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		labelRut = new javax.swing.JLabel();
		rutContribuyente = new javax.swing.JTextField();
		labelAmbiente = new javax.swing.JLabel();
		notaCertificado = new javax.swing.JLabel();
		labelFecha = new javax.swing.JLabel();
		fecha = new com.toedter.calendar.JDateChooser();
		labelInfo = new javax.swing.JLabel();
		labelPeriodo = new javax.swing.JLabel();
		periodo = new javax.swing.JTextField();

		periodo.setToolTipText("Ingresar Periodo YYYY-MM");

		proceso = new JComboBox(Constantes.AMBIENTE);
		proceso.setEditable(false);
		proceso.addActionListener(this);

		labelRut.setText("RUT ");
		labelAmbiente.setText("Ambiente");
		labelPeriodo.setText("Período");

		notaCertificado.setForeground(new java.awt.Color(167, 14, 14));
		notaCertificado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		notaCertificado.setText("Recuerde, carga certificado en su explorador");

		labelFecha.setText("Fecha");

		fecha.setDateFormatString("yyyy-MM-dd");

		labelInfo.setText("Respaldo DTE, SII.");

		periodo.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changedStateFecha();
			}
			public void removeUpdate(DocumentEvent e) {
				changedStateFecha();
			}
			public void insertUpdate(DocumentEvent e) {
				changedStateFecha();
			}
		});

		fecha.getDateEditor().getUiComponent().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(fecha.getDate() == null)
					changedStatePeriodo();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(fecha.getDate() == null)
					changedStatePeriodo();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(fecha.getDate() == null)
					changedStatePeriodo();

			}
		});

		fecha.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener () {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						if ("date".equals(e.getPropertyName()) && fecha.getDate() != null) {
							changedStatePeriodo();
						}
					}
				});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(notaCertificado, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addGap(23, 23, 23)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(labelPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(labelRut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(labelFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(labelAmbiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(proceso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(rutContribuyente)
												.addComponent(periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(0, 0, Short.MAX_VALUE))
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
										.addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addGap(18, 18, 18)
										.addComponent(labelFecha)))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(16, 16, 16)
										.addComponent(labelRut))
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(rutContribuyente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(42, 42, 42)
						.addComponent(notaCertificado)
						.addContainerGap(37, Short.MAX_VALUE))
				);
	}// </editor-fold>//GEN-END:initComponents                                        

	/**
	 * 
	 */
	public void changedStateFecha() {
		fecha.setDate(null);
		if (!periodo.getText().equals("")) {
			fecha.setEnabled(false);
		}
		else {
			fecha.setEnabled(true);
		}
	}

	/**
	 * 
	 */
	public void changedStatePeriodo() {
		periodo.setText("");
		if (fecha.getDate() != null) {
			periodo.setEnabled(false);
		}
		else {
			periodo.setEnabled(true);
		}
	}

	/**
	 * Metodo inicializador venttana
	 * @return
	 */
	public static int createAndShowGUI() {
		JComponent newContentPane = new ViewRespaldaDTE();
		return JOptionPane.showConfirmDialog(null, newContentPane, 
				"Inicio Respaldo Documento", JOptionPane.OK_CANCEL_OPTION);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the proceso
	 */
	public static javax.swing.JComboBox getProceso() {
		return proceso;
	}

	/**
	 * @return the rutContribuyente
	 */
	public static javax.swing.JTextField getRutContribuyente() {
		return rutContribuyente;
	}

	/**
	 * @return the fecha
	 */
	public static com.toedter.calendar.JDateChooser getFecha() {
		return fecha;
	}

	/**
	 * @return the periodo
	 */
	public static javax.swing.JTextField getPeriodo() {
		return periodo;
	}

}
