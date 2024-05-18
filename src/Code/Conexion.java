package Code;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {

	// "jdbc:sqlite:C:/Users/tutaa/Workspace/Java/Projects/EcoSpot/db/es.db";
	static String url = "jdbc:sqlite:C:\\Users\\tutaa\\Workspace\\Data Base SQL\\SQLite\\EcoSpot\\es.db";

	static Connection connect;
	static PreparedStatement pSt;
	static ResultSet result = null;

	public static void conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN:\n" + e.getMessage());
		}
	}

	public static void registrar(String query) throws SQLException {
		conectar();
		try {
			pSt = connect.prepareStatement(query);
			pSt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR INFORMACION:\n" + e.getMessage());
		} finally {
			if (pSt != null) {
				pSt.close();
			}
		}
	}

	public static ArrayList<Object> seleccionar(String query, String[] columnas) throws SQLException {
		conectar();
		ArrayList<Object> datos = new ArrayList<>();

		try {
			pSt = connect.prepareStatement(query);
			result = pSt.executeQuery();

			while (result.next()) {
				for (var i : columnas) {
					datos.add(result.getObject(i));
				}
			}
		} catch (SQLException e) {
			System.out.println("ERROR AL SELECCIONAR INFORMACIÓN:\n" + e.getMessage());
		} finally {
			pSt.close();
			result.close();
		}
		return datos;
	}

	public static void actualizar(String query) throws SQLException {
		conectar();
		try {
			pSt = connect.prepareStatement(query);
			pSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ERROR AL ACTUALIZAR INFORMACIÓN:\n" + e.getMessage());
		} finally {
			if (pSt != null) {
				pSt.close();
			}
		}
	}

	public static void eliminar(String query) throws SQLException {
		conectar();
		try {
			pSt = connect.prepareStatement(query);
			pSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ERROR AL BORRAR INFORMACIÓN:\n" + e.getMessage());
		} finally {
			if (pSt != null) {
				pSt.close();
			}
		}
	}

	public static void operaciones() throws SQLException {

		// ^ REGISTRAR
		Conexion.registrar(
				"INSERT INTO usuarios (correo,contraseña,nombre,departamento,ciudad,edad,"
						+ "fecha_registro ) VALUES ('laura@gmail.com','Laura1234!','Laura Gomez',"
						// cSpell:disable-next-line
						+ "'Antioquia','Medellín',25,DATE ('now'));");

		// ^ ELIMINAR
		Conexion.eliminar("delete from usuarios where correo like '%@example%'");

		// ^ SELECCIONAR
		System.out.println(Conexion.seleccionar("select * from usuarios",
				new String[] { "nombre", "edad", "correo" }));

		// ^ ACTUALIZAR
		Conexion.actualizar("update usuarios set nombre = 'Adrian Camilo Tuta Cortes' where "
				+ "id_usuario = 1");

	}

}
