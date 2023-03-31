package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPpal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;

	/**
	 * Se crea la ventana principal con sus características y funciones.
	 */
	public VentanaPpal() {
		setTitle("Gesti\u00F3n de Empresas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][20.00,grow][][grow]", "[grow][grow][grow]"));

		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Gesti\u00F3n de Centros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		((TitledBorder) panel.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(panel, "cell 1 1,grow");
		panel.setLayout(new MigLayout("", "[]", "[::100px,grow][27.00,grow][::100px,grow]"));

		JButton btnVerCentros = new JButton("Ver Centros");
		btnVerCentros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarListaCentros();
			}
		});
		btnVerCentros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnVerCentros, "cell 0 0,grow");
		btnVerCentros.setIcon(new ImageIcon(VentanaPpal.class.getResource("/images/tabla32.png")));

		JButton btnNuevoCentro = new JButton("Nuevo Centro");
		btnNuevoCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarVentanaInsertarCentros();
			}
		});
		btnNuevoCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnNuevoCentro, "cell 0 2,growy");
		btnNuevoCentro.setIcon(new ImageIcon(VentanaPpal.class.getResource("/images/a\u00F1adir32.png")));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Gesti\u00F3n de Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		((TitledBorder) panel_1.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(panel_1, "cell 3 1,grow");
		panel_1.setLayout(new MigLayout("", "[]", "[::100px,grow][36.00,grow][::100px,grow]"));

		JButton btnVerDepartamentos = new JButton("Ver Departamentos");
		btnVerDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarListaDepartamentos();
			}
		});
		btnVerDepartamentos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(btnVerDepartamentos, "cell 0 0,grow");
		btnVerDepartamentos.setIcon(new ImageIcon(VentanaPpal.class.getResource("/images/tabla32.png")));

		JButton btnNuevoDepartamento = new JButton("Nuevo Departamento ");
		btnNuevoDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarVentanaInsertarDepartamentos();
			}
		});
		btnNuevoDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(btnNuevoDepartamento, "cell 0 2,growy");
		btnNuevoDepartamento.setIcon(new ImageIcon(VentanaPpal.class.getResource("/images/a\u00F1adir32.png")));
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

}
