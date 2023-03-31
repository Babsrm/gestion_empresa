package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase necesaria para establecer la conexión con la base de datos. El
 * administrador de la base de datos deberá de ser el único capaz de manejar
 * esta información para salvaguardar la integridad y seguridad de la BBDD.
 * 
 * @param database   String Indicar el nombre de la base de datos.
 * @param usuario    String Insertar el usuario para el manejo de la base de
 *                   datos.
 * @param contrasena String Insertar la contraseña para el acceso autentificado
 *                   a la base de datos.
 * @param url        String Insertar la dirección URL de la base de datos.
 * 
 * @author Barbara Ruiz
 *
 */

public class ConexionBD {

	private static final String database = "empresa";
	private static final String usuario = "barbara";
	private static final String contrasena = "1234";
	private static final String url = "jdbc:mysql://localhost/" + database;
	private Connection conexion = null;

	/**
	 * Getter del objeto Connection. En caso de que no haya una conexión previa
	 * activa: -Registra el driver de MySQL. -Conecta a la base de datos.
	 * 
	 * En caso de que haya una conexión previa anterior: -Se mantiene conectado en
	 * dicha conexión.
	 * 
	 * @catch ClassNotFoundException - El driver no ha sido registrado.
	 * @catch SQLException - Error no definido específicamente. Indicado en consola.
	 * @return objeto conexion.
	 */

	public Connection getConexion() {
		if (conexion != null) {
			return conexion;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conexion = DriverManager.getConnection(url, usuario, contrasena);
			System.out.println("Conexion a bilioteca correcta");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no registrado");
		} catch (SQLException e) {
			System.out.println("Error SQLException: " + e.getMessage());
		}
		return conexion;
	}

	/**
	 * Método que desconecta la conexión a la base de datos.
	 * 
	 * @catch SQLException - Error indefinido indicado en la consola.
	 */
	public void desconectar() {
		try {
			conexion.close();
			conexion = null;
		} catch (SQLException e) {
			System.out.println("Error cerrando la conexion " + e.getMessage());
		}
	}

}
