// cSpell:ignore publicacion
package Code;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Screens.Login.Login;

public class Conexion {

    static String url = "jdbc:sqlite:C:/Users/tutaa/Workspace/Java/Projects/EcoGuard/db/es.db";
    public static String rutaEvidencia = "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg";
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

    public static void cerrarConexion() {
        try {
            if (pSt != null) {
                pSt.close();
            }
            if (result != null) {
                result.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static void registrar(String query) throws SQLException {
        conectar();
        try {
            pSt = connect.prepareStatement(query);
            pSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR INFORMACION:\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static void registrarPublicacion(int idUsuario, String tipo, String hora, String departamento, String ciudad,
            byte[] fileData, String informacion) throws SQLException {

        String query = "INSERT INTO incidentes_ambientales(id_usuario, tipo, fecha, hora, departamento, ciudad, evidencia, informacion) "
                + "VALUES (?, ?, Date('now'), ?, ?, ?, ?, ?)";

        conectar();
        try {
            pSt = connect.prepareStatement(query);

            pSt.setInt(1, Login.idUsuarioGuardar);
            pSt.setString(2, tipo);
            pSt.setString(3, hora);
            pSt.setString(4, departamento);
            pSt.setString(5, ciudad);
            pSt.setBytes(6, fileData);
            pSt.setString(7, informacion);

            pSt.executeUpdate();
            JOptionPane.showMessageDialog(null, "¡PUBLICADO CON ÉXITO!");

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR INFORMACION:\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static ArrayList<ArrayList<Object>> seleccionar(String query, String[] columnas) throws SQLException {
        conectar();
        ArrayList<ArrayList<Object>> registros = new ArrayList<>();
        try {
            pSt = connect.prepareStatement(query);
            result = pSt.executeQuery();

            while (result.next()) {
                ArrayList<Object> registro = new ArrayList<>();
                for (var i : columnas) {
                    registro.add(result.getObject(i));
                }
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.out.println("ERROR AL SELECCIONAR INFORMACIÓN:\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return registros;
    }

    public static void recibirEvidencia(int idIncidente) {
        conectar();
        String query = "SELECT evidencia FROM incidentes_ambientales WHERE id_incidente = ?";

        try {
            pSt = connect.prepareStatement(query);
            pSt.setInt(1, idIncidente);
            result = pSt.executeQuery();
            if (result.next()) {
                byte[] fileData = result.getBytes("evidencia");
                Files.writeFile(fileData,
                        String.format(
                                rutaEvidencia,
                                idIncidente));
            } else {
                System.out.println("No se encontró el incidente con ID: " + idIncidente);
            }
        } catch (SQLException e) {
        } finally {
            cerrarConexion();
        }

    }

    public static void actualizar(String query) throws SQLException {
        conectar();
        try {
            pSt = connect.prepareStatement(query);
            pSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR AL ACTUALIZAR INFORMACIÓN:\n" + e.getMessage());
        } finally {
            cerrarConexion();
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
            cerrarConexion();
        }
    }

    public static String solicitarNombreUsuario(int id_usuario) {
        String nombre = "";
        try {
            nombre = (String) Conexion.seleccionar(
                    String.format("SELECT * FROM usuarios WHERE id_usuario = %d ",
                            id_usuario),
                    new String[] { "nombre" }).get(0).get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return nombre;
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
