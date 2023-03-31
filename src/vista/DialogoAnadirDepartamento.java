package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Centro;
import modelo.Departamento;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

/**
 * Clase que crea una interfaz de usuario. En este caso, se corresponde a un
 * diálogo para añadir un nuevo departamento.
 * 
 * @author Barbara Ruiz
 *
 */
public class DialogoAnadirDepartamento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodDepartamento;
	private JTextField txtNombre;
	private Controlador controlador;
	private static JComboBox comboCentro;
	private final ButtonGroup buttonGroupTipoDir = new ButtonGroup();
	private JSpinner spinner;
	private JRadioButton rdbtnPropiedad;
	private JRadioButton rdbtnFunciones;

	/**
	 * Se crea el diálogo con sus características y funciones.
	 */
	public DialogoAnadirDepartamento() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Detalles del departamento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			((TitledBorder) panel.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(panel, "cell 0 0,grow");
			panel.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][]"));
			{
				JLabel lblNewLabel_3 = new JLabel("");
				lblNewLabel_3
						.setIcon(new ImageIcon(DialogoAnadirDepartamento.class.getResource("/images/editar32.png")));
				panel.add(lblNewLabel_3, "cell 0 0 1 5");
			}
			{
				JLabel lblCodDpto = new JLabel("Cod. Dpto:");
				panel.add(lblCodDpto, "cell 1 0,alignx trailing");
				lblCodDpto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtCodDepartamento = new JTextField();
				panel.add(txtCodDepartamento, "cell 2 0 2 1,growx");
				txtCodDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtCodDepartamento.setColumns(10);
			}
			{
				JLabel lblCodCentro = new JLabel("Cod Centro:");
				lblCodCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel.add(lblCodCentro, "cell 1 1,alignx trailing");
			}
			{
				comboCentro = new JComboBox();
				comboCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel.add(comboCentro, "cell 2 1 2 1,growx");
			}
			{
				JLabel lblTipoDir = new JLabel("Tipo Dirección:");
				panel.add(lblTipoDir, "cell 1 2,alignx trailing");
				lblTipoDir.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				rdbtnPropiedad = new JRadioButton("Propiedad");
				rdbtnPropiedad.setSelected(true);
				buttonGroupTipoDir.add(rdbtnPropiedad);
				rdbtnPropiedad.setActionCommand("p");
				panel.add(rdbtnPropiedad, "cell 2 2");
			}
			{
				rdbtnFunciones = new JRadioButton("En funciones");
				buttonGroupTipoDir.add(rdbtnFunciones);
				rdbtnFunciones.setActionCommand("f");
				panel.add(rdbtnFunciones, "cell 3 2");
			}
			{
				JLabel lblPresupuesto = new JLabel("Presupuesto:");
				lblPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel.add(lblPresupuesto, "cell 1 3,alignx trailing");
			}
			{
				spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(5, 1, 100, 1));
				panel.add(spinner, "cell 2 3");
			}
			{
				JLabel lblSpinnerPresupuesto = new JLabel("(en miles de €)");
				panel.add(lblSpinnerPresupuesto, "cell 3 3");
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				panel.add(lblNombre, "cell 1 4,alignx trailing");
				lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtNombre = new JTextField();
				panel.add(txtNombre, "cell 2 4 2 1,growx");
				txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtNombre.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						recogerDatos();
					}
				});
				btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	/**
	 * Método que obtiene los datos insertados por el usuario, los recoge en un
	 * objeto y los envía al controlador para que se ejecute la acción de
	 * insertarDepartamento.
	 */
	protected void recogerDatos() {
		try {
			Centro c = (Centro) comboCentro.getSelectedItem(); // creo este objeto para escoger de él la opción que el
																// usuario ha elegido del comboBox

			int codDepartamento = Integer.parseInt(txtCodDepartamento.getText());
			int codCentro = c.getCod_centro();
			String tipoDir = buttonGroupTipoDir.getSelection().getActionCommand().toUpperCase();
			// String tipoDir = "F"; if (rdbtnPropiedad.isSelected()){tipoDir="p"}; --otra
			// forma
			int presupuesto = (int) spinner.getValue();
			String nombre = txtNombre.getText();

			Departamento dpto = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, nombre);
			controlador.insertarDepartamento(dpto);

			JOptionPane.showMessageDialog(this, "Departamento insertado correctamente", "Info",
					JOptionPane.INFORMATION_MESSAGE);

			this.setVisible(false);
			vaciarDatos();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					"Hay datos sin introducir. Por favor, introduzca los datos numéricos correctos en código departamento/cento y los que puedan faltar.",
					"Faltan datos", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al insertar." + e.getMessage() + " " + e.getSQLState(),
					"Error al insertar", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método que limpia la ventana para que, al insertarse correctamente un nuevo
	 * departamento, vuelvan a aparecer los campos a insertar vacíos.
	 */
	public void vaciarDatos() {
		txtCodDepartamento.setText("");
		spinner.setValue(5);
		txtNombre.setText("");
		rdbtnPropiedad.setSelected(true);
		rdbtnFunciones.setSelected(false);
	}

	/**
	 * Método que une la interfaz gráfica y sus acciones con el controlador para que
	 * este tome poder y pueda ejecutar las demandas del usuario.
	 * 
	 * @param controlador objeto de la clase {@link Controlador}.
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * Método que recoja la lista de centros para añadirla al jComboBox.
	 * 
	 * @param listaCentros ArrayList.
	 */
	public static void setListaCentros(ArrayList<Centro> listaCentros) {
		comboCentro.removeAllItems();
		for (Centro c : listaCentros) {
			comboCentro.addItem(c);
		}
	}

}
