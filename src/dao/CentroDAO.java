package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Centro;

/**
 * 
 * Clase que implementa un CRUD de la base datos. (Create, Read, Update y
 * Delete).
 * 
 * @author Barbara Ruiz
 */
public class CentroDAO {

	private ConexionBD conexion;

	/**
	 * Constructor de la clase que crea una nueva conexión (@link ConexionBD) a la
	 * base de datos.
	 */
	public CentroDAO() {
		this.conexion = new ConexionBD();
	}

	/**
	 * Método que recoge todos los centros de la base de datos. Tras determinar la
	 * conexión a la base de datos, el método ejecuta una consulta a ésta. Con un
	 * bucle, se recorren todas las filas de la tabla; cada fila es almacenada como
	 * un objeto {@link Centro} y cada centro es almacenado dentro de la ArrayList.
	 * 
	 * @return ArrayList<Centro> Lista de objetos {@link Centro}; todos los centros
	 *         de la base de datos.
	 * @catch SQLException Error indefinido que se indica específicamente en la
	 *        consola.
	 */
	public ArrayList<Centro> obtenerCentros() {
		// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		ArrayList<Centro> lista = new ArrayList<Centro>();

		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from centros");

			// Bucle para recorrer todas las filas que devuelve la consulta
			while (resultado.next()) {
				int cod_centro = resultado.getInt("cod_centro");
				String nombre = resultado.getString("nombre");
				String direccion = resultado.getString("direccion");

				Centro centro = new Centro(cod_centro, nombre, direccion);
				lista.add(centro);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre centros: " + e.getMessage());
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
	 * Método para obtener un objeto centro (clase {@link Centro}) en la base de
	 * datos. Realiza una consulta a la base de datos según el objeto centro que
	 * haya indicado el usuario.
	 * 
	 * @param centro objeto de la clase {@link Centro}.
	 * @return Centro objeto clase {@link Centro}.
	 */
	public Centro obtenerCentro(int cod_centro) {
		// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Centro c = null;

		try {
			consulta = con.prepareStatement("select * from centros" + " where cod_centro = ?");
			consulta.setInt(1, cod_centro);
			resultado = consulta.executeQuery();

			// Bucle para recorrer todas las filas que devuelve la consulta
			if (resultado.next()) {
				String nombre = resultado.getString("nombre");
				String direccion = resultado.getString("direccion");

				c = new Centro(cod_centro, nombre, direccion);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre centros: " + e.getMessage());
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
		return c;
	}

	/**
	 * Método para insertar un objeto centro (clase {@link Centro}) en la base de
	 * datos. Realiza una inserción a la base de datos según el objeto centro que
	 * haya indicado el usuario.
	 * 
	 * @param centro objeto de la clase {@link Centro}.
	 * @return int
	 */
	public int insertarCentro(Centro centro) {
		// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado = 0;

		try {
			consulta = con.prepareStatement("INSERT INTO Centros (cod_centro,nombre,direccion)" + " VALUES (?,?,?) ");

			consulta.setInt(1, centro.getCod_centro());
			consulta.setString(2, centro.getNombre());
			consulta.setString(3, centro.getDireccion());
			resultado = consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la inserci�n del centro: " + e.getMessage());
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
	 * Método para actualizar un registro centro (clase {@link Centro}) de la base
	 * de datos. Se obtiene una conexión a la base de datos; se realiza la consulta
	 * de actualización con los datos del objeto centro que ha indicado el usuario.
	 * 
	 * @param centro clase {@link Centro}.
	 * @return int
	 */
	public int actualizarAutor(Centro centro) {
		// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado = 0;

		try {
			consulta = con.prepareStatement(
					"UPDATE centros \r\n" + "SET nombre = ?, direccion = ?\r\n" + "WHERE cod_centro = ?;");

			consulta.setString(1, centro.getNombre());
			consulta.setString(2, centro.getDireccion());
			consulta.setInt(3, centro.getCod_centro());
			resultado = consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion de centros: " + e.getMessage());
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
	 * Método para eliminar un registro centro (clase {@link Centro}) de la base de
	 * datos. Se obtiene una conexión a la base de datos; se realiza la consulta de
	 * eliminación con los datos del objeto centro que ha indicado el usuario.
	 * 
	 * @param centro clase {@link Centro}.
	 * @return int
	 */
	public int eliminarCentro(Centro centro) {
		// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado = 0;

		try {
			consulta = con.prepareStatement("DELETE FROM centros\r\n" + "WHERE cod_centro = ?");

			consulta.setInt(1, centro.getCod_centro());
			resultado = consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado de Centros: " + e.getMessage());
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
