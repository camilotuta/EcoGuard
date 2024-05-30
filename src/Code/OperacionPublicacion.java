// cSpell:ignore operacion publicacion
package Code;

import Screens.Login.Login;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class OperacionPublicacion extends OperacionCRUD {
    
    public static String rutaEvidencia = "C:/Users/tutaa/Workspace/Java/Projects/EcoGuard/imgPublicaciones/%d.jpg";
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
    public static String solicitarNombreUsuario(int id_usuario) {
        String nombre = "";
        try {
            nombre = (String) seleccionar(
                    String.format("SELECT * FROM usuarios WHERE id_usuario = %d ",
                            id_usuario),
                    new String[] { "nombre" }).get(0).get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return nombre;
    } 
}
