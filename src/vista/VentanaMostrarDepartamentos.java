package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Departamento;
import net.miginfocom.swing.MigLayout;

public class VentanaMostrarDepartamentos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Controlador controlador;

	/**
	 * Se crea la ventana con sus características y funciones.
	 */
	public VentanaMostrarDepartamentos() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][grow][grow]"));

		JLabel lblNewLabel = new JLabel("Listado de Centros:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNewLabel, "cell 0 0 2 1");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 1,grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo Dpto", "C\u00F3digo Centro", "Tipo Dir", "Presupuesto", "Nombre" }) {
			Class[] columnTypes = new Class[] { Integer.class, Integer.class, String.class, Integer.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(77);
		table.getColumnModel().getColumn(1).setPreferredWidth(78);
		table.getColumnModel().getColumn(2).setPreferredWidth(54);
		table.getColumnModel().getColumn(4).setPreferredWidth(133);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 2,grow");

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCerrar.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel.add(btnCerrar);
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
	 * Método que obtiene todos los departamentos de la ArrayList y los coloca en
	 * una fila para poder ser mostrados en la tabla de la interfaz.
	 */
	public void setListaDepartamentos(ArrayList<Departamento> lista) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (Departamento departamento : lista) {
			Object fila[] = { departamento.getCodDepartamento(), departamento.getCodCentro(),
					departamento.getTipoDirTexto(), departamento.getPresupuesto(), departamento.getNombre() };
			modelo.addRow(fila);
		}
	}

}
