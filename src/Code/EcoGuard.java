/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Code;

import java.sql.SQLException;

/**
 *
 * @author tutaa
 */
public class EcoGuard {

    /**
     * @param args the command line arguments
     * @throws SQLException
     */

    public static void main(String[] args) throws SQLException {

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
