package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.CentroDAO;
import dao.DepartamentoDAO;
import modelo.Centro;
import modelo.Departamento;
import vista.DialogoAnadirCentro;
import vista.DialogoAnadirDepartamento;
import vista.VentanaMostrarCentros;
import vista.VentanaMostrarDepartamentos;
import vista.VentanaPpal;

/**
 * Clase base para controlar las interacciones entre clases, ya sean interfaces,
 * modelo o DAO.
 * 
 * @author Barbara Ruiz
 *
 */
public class Controlador {

	private VentanaPpal ventanaPpal;
	private VentanaMostrarCentros ventanaMostrarCentros;
	private DialogoAnadirCentro dialogoAnadirCentro;
	private VentanaMostrarDepartamentos ventanaMostrarDepartamentos;
	private DialogoAnadirDepartamento dialogoAnadirDepartamento;

	private CentroDAO centroDAO;
	private DepartamentoDAO departamentoDAO;

	/**
	 * Método para crear las ventanas de la aplicación; dándole acceso al
	 * controlador desde las vistas y creando los objetos DAO. Se consigue que el
	 * usuario tenga control y ejecute las órdenes asociadas a las funciones.
	 */
	public Controlador() {
		// Creamos las ventanas de la aplicaci�n
		ventanaPpal = new VentanaPpal();
		ventanaMostrarCentros = new VentanaMostrarCentros();
		ventanaMostrarDepartamentos = new VentanaMostrarDepartamentos();
		dialogoAnadirCentro = new DialogoAnadirCentro();
		dialogoAnadirDepartamento = new DialogoAnadirDepartamento();

		// Dando acceso al controlador desde las vistas
		ventanaPpal.setControlador(this);
		ventanaMostrarCentros.setControlador(this);
		dialogoAnadirCentro.setControlador(this);
		ventanaMostrarDepartamentos.setControlador(this);
		dialogoAnadirDepartamento.setControlador(this);

		// Creamos los objetos DAO
		centroDAO = new CentroDAO();
		departamentoDAO = new DepartamentoDAO();
	}

	/**
	 * Método para iniciar el programa.
	 */
	public void inciarPrograma() {
		ventanaPpal.setVisible(true);
	}

	/**
	 * Método que muestra la lista de centros recogidos desde la base de datos.
	 */
	public void mostrarListaCentros() {
		ArrayList<Centro> lista = centroDAO.obtenerCentros();
		ventanaMostrarCentros.setListaCentros(lista);
		ventanaMostrarCentros.setVisible(true);
	}

	/**
	 * Método que muestra la ventana para insertar un nuevo centro (clase
	 * {@link Centro}).
	 */
	public void mostrarVentanaInsertarCentros() {
		dialogoAnadirCentro.setVisible(true);
	}

	/**
	 * Método que inserta el centro indicado en la ventana "Insertar centros",
	 * recogiendo los datos indicados por el usuario.
	 * 
	 * @param centro objeto de clase {@link Centro}.
	 */
	public void insertarCentro(Centro centro) {
		int resultado = centroDAO.insertarCentro(centro);
		if (resultado == 0) {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Error. no se ha podido insertar.");
		} else {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Insercion del centro correcta");
			dialogoAnadirCentro.setVisible(false);
		}
	}

	/**
	 * Método que muestra la lista de departamentos recogidos desde la base de
	 * datos.
	 */
	public void mostrarListaDepartamentos() {
		ArrayList<Departamento> lista = departamentoDAO.obtenerTodosDepartamentos();
		ventanaMostrarDepartamentos.setListaDepartamentos(lista);
		ventanaMostrarDepartamentos.setVisible(true);
	}

	/**
	 * Método que muestra la ventana para insertar un nuevo departamento (clase
	 * {@link Departamento}).
	 */
	public void mostrarVentanaInsertarDepartamentos() {
		ArrayList<Centro> listaCentros = centroDAO.obtenerCentros();
		DialogoAnadirDepartamento.setListaCentros(listaCentros);
		dialogoAnadirDepartamento.setVisible(true);

	}

	/**
	 * Método que inserta el departamento indicado en la ventana "Insertar
	 * departamentos", recogiendo los datos indicados por el usuario.
	 * 
	 * @param dpto objeto de la clase {@link Departamento}.
	 * @throws SQLException - controlada por la ventana de diálogo.
	 */
	public void insertarDepartamento(Departamento dpto) throws SQLException {

		int resultado = departamentoDAO.insertarDepartamento(dpto);
		// no me hace falta hacer el if controlador de que ha fallado la inserción
		// porque la he lanzado a que lo controle al ventana de dialogo
		dialogoAnadirDepartamento.setVisible(false);
	}
}
