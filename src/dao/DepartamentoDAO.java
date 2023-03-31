package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Departamento;

/**
 * Clase que implementa un CRUD de la base de datos. (Create, Read, Update y
 * Delete).
 * 
 * @author Barbara
 *
 */
public class DepartamentoDAO {

	private ConexionBD conexion;

	/**
	 * Constructor de la clase que crea una nueva conexión (@link ConexionBD) a la
	 * base de datos.
	 */
	public DepartamentoDAO() {
		this.conexion = new ConexionBD();
	}

	/**
	 * Método que recoge todos los departamentos de la base de datos. Tras
	 * determinar la conexión a la base de datos, el método ejecuta una consulta a
	 * ésta. Con un bucle, se recorren todas las filas de la tabla; cada fila es
	 * almacenada como un objeto {@link Departamento} y cada departamento es
	 * almacenado dentro de la ArrayList.
	 * 
	 * @return ArrayList<Departamento> Lista de objetos {@link Departamento}; todos
	 *         los departamentos de la base de datos.
	 * @catch SQLException Error indefinido que se indica específicamente en la
	 *        consola.
	 */
	public ArrayList<Departamento> obtenerTodosDepartamentos() {
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		ArrayList<Departamento> lista = new ArrayList<Departamento>();

		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from departamentos");

			while (resultado.next()) {
				int codDepartamento = resultado.getInt("cod_departamento");
				int codCentro = resultado.getInt("cod_centro");
				String tipoDir = resultado.getString("tipo_dir");
				int presupuesto = resultado.getInt("presupuesto");
				String nombre = resultado.getString("nombre");

				Departamento dpto = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, nombre);

				lista.add(dpto);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: " + e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: " + e.getMessage());
			} catch (Exception e) {

			}
		}
		return lista;
	}

	/**
	 * Método para obtener un objeto departamento (clase {@link Departamento}) en la
	 * base de datos. Realiza una consulta a la base de datos según el objeto
	 * departamento que haya indicado el usuario.
	 * 
	 * @param departamento objeto de la clase {@link Departamento}.
	 * @return Departamento objeto clase {@link Departamento}.
	 */
	public Departamento obtenerUnDepartamento(int codDepartamento) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Departamento dpto = null;

		try {
			consulta = con.prepareStatement("select * from departamentos " + "where cod_departamento = ?");
			consulta.setInt(1, codDepartamento);
			resultado = consulta.executeQuery();

			if (resultado.next()) {
				int codCentro = resultado.getInt("cod_centro");
				String tipoDir = resultado.getString("tipo_dir");
				int presupuesto = resultado.getInt("presupuesto");
				String nombre = resultado.getString("nombre");

				dpto = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, nombre);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: " + e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: " + e.getMessage());
			} catch (Exception e) {

			}
		}
		return dpto;
	}

	/**
	 * Método para insertar un objeto departamento (clase {@link Departamento}) en
	 * la base de datos. Realiza una inserción a la base de datos según el objeto
	 * departamento que haya indicado el usuario.
	 * 
	 * @param departamento objeto de la clase {@link Departamento}.
	 * @return int
	 */
	public int insertarDepartamento(Departamento dpto) throws SQLException {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado = 0;

		try {
			consulta = con.prepareStatement(
					"INSERT INTO departamentos (cod_departamento, cod_centro, tipo_dir, presupuesto, nombre)"
							+ " VALUES (?,?,?,?,?) ");

			consulta.setInt(1, dpto.getCodDepartamento());
			consulta.setInt(2, dpto.getCodCentro());
			consulta.setString(3, dpto.getTipoDir());
			consulta.setInt(4, dpto.getPresupuesto());
			consulta.setString(5, dpto.getNombre());

			resultado = consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar: " + e.getMessage());
			throw e;
			// lo lanzo para que el controlador o la ventana controle la excepción por los
			// errores que nos pueda dar (coddpto repetido o enviar sin datos)
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: " + e.getMessage());
			} catch (Exception e) {

			}
		}
		return resultado;
	}

	/**
	 * Método para actualizar un registro departamento (clase {@link Departamento})
	 * de la base de datos. Se obtiene una conexión a la base de datos; se realiza
	 * la consulta de actualización con los datos del objeto departamento que ha
	 * indicado el usuario.
	 * 
	 * @param dpto clase {@link Departamento}.
	 * @return int
	 */
	public int actualizarDepartamento(Departamento dpto) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado = 0;

		try {
			consulta = con.prepareStatement(
					"UPDATE departamentos\n" + "SET cod_centro = ?\n, tipo_dir = ?\n, presupuesto = ?\n, "
							+ "nombre = ?\n" + "WHERE cod_departamento = ?;");

			consulta.setInt(1, dpto.getCodCentro());
			consulta.setString(2, dpto.getTipoDir());
			consulta.setInt(3, dpto.getPresupuesto());
			consulta.setString(4, dpto.getNombre());
			consulta.setInt(5, dpto.getCodDepartamento());
			resultado = consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: " + e.getMessage());
			} catch (Exception e) {

			}
		}
		return resultado;
	}

	/**
	 * Método para eliminar un registro departamento (clase {@link Departamento}) de
	 * la base de datos según su código de departamento. Se obtiene una conexión a
	 * la base de datos; se realiza la consulta de eliminación con los datos del
	 * objeto departamento que ha indicado el usuario.
	 * 
	 * @param codDepartamento int
	 * @return int
	 */
	public int eliminarDepartamento(int codDepartamento) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado = 0;

		try {
			consulta = con.prepareStatement("DELETE FROM departamentos \n" + "WHERE cod_departamento = ?");

			consulta.setInt(1, codDepartamento);
			resultado = consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: " + e.getMessage());
			} catch (Exception e) {

			}
		}
		return resultado;
	}

}
