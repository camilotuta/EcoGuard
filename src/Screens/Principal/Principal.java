/*
 cSpell:ignore publicacion ubicacion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal;

import Screens.Login.Login;

import Screens.Principal.ReportIncident.ReportIncident;
import Screens.Principal.ViewIncident.ViewIncident;
import Screens.Profile.PersonalProfile;
import Screens.Profile.ViewProfile;
import Code.CambiarIU;
import Code.Conexion;
import Code.Incidentes;
import Code.Ubicaciones;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import java.awt.event.*;

/**
 *
 * @author tutaa
 */
public class Principal extends javax.swing.JFrame {
        Incidentes incidentes = new Incidentes();
        Map<String, String> incidentesAmbientales = incidentes.incidentes;
        ArrayList<Integer> idsUsuarios = new ArrayList<>();

        Ubicaciones ubicacion = new Ubicaciones();
        Map<String, ArrayList<ArrayList<Integer>>> puntos = ubicacion.generarPuntos();

        /**
         * Creates new form Principal
         */
        public Principal() {
                initComponents();

                this.setTitle("Pantalla Principal");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                textosLabels();
                llenarPublicaciones();
                agregarMarcadores();
                ponerUbicacion();

        }

        public void crearLabel(int x, int y, String iconoPath, int idPublicacion) {
                javax.swing.JLabel nuevoMarcador = new javax.swing.JLabel();
                nuevoMarcador.setToolTipText(String.valueOf(idPublicacion));
                CambiarIU.setImageLabelSize(nuevoMarcador, iconoPath, 30, 30);
                panelMapa.add(nuevoMarcador, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, -1, -1));
                nuevoMarcador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                nuevoMarcador.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                ViewIncident.idPublicacion = idPublicacion;
                                irViewIncident();
                        }
                });

        }

        public void irViewIncident() {
                ViewIncident view = new ViewIncident();
                view.setVisible(true);

                this.setVisible(false);
        }

        public void ponerUbicacion() {
                try {
                        var ubi = Conexion.seleccionar(
                                        "Select * from usuarios where id_usuario =" + Login.idUsuarioGuardar,
                                        new String[] { "Ciudad", "departamento" });

                        lbPonerUbicacion.setText(ubi.get(0).get(0) + " - " + ubi.get(0).get(1));

                } catch (SQLException e) {
                        e.printStackTrace();
                }

        }

        public void llenarPublicaciones() {

                try {
                        ArrayList<ArrayList<Object>> datosPublicaciones = solicitarPublicaciones();

                        // 0
                        idsUsuarios.add((int) datosPublicaciones.get(0).get(1));
                        String nombre0 = solicitarNombreUsuario((int) datosPublicaciones.get(0).get(1));
                        String tipo0 = (String) datosPublicaciones.get(0).get(2);
                        String hora0 = (String) datosPublicaciones.get(0).get(7);
                        String fecha0 = (String) datosPublicaciones.get(0).get(3);
                        String ubicacion0 = (String) datosPublicaciones.get(0).get(5) + " - "
                                        + (String) datosPublicaciones.get(0).get(4);
                        int idIncidente0 = (int) datosPublicaciones.get(0).get(0);
                        Conexion.recibirEvidencia(idIncidente0);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente0, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente0));

                        lbPonerNombreUsuario0.setText(nombre0);
                        lbPonerTipoIncidente0.setText(tipo0);
                        lbPonerHoraIncidente0.setText(hora0);
                        lbPonerFechaIncidente0.setText(fecha0);
                        lbPonerUbicacionIncidente0.setText(ubicacion0);

                        // 1
                        idsUsuarios.add((int) datosPublicaciones.get(1).get(1));
                        String nombre1 = solicitarNombreUsuario((int) datosPublicaciones.get(1).get(1));
                        String tipo1 = (String) datosPublicaciones.get(1).get(2);
                        String hora1 = (String) datosPublicaciones.get(1).get(7);
                        String fecha1 = (String) datosPublicaciones.get(1).get(3);
                        String ubicacion1 = (String) datosPublicaciones.get(1).get(5) + " - "
                                        + (String) datosPublicaciones.get(1).get(4);
                        int idIncidente1 = (int) datosPublicaciones.get(1).get(0);
                        Conexion.recibirEvidencia(idIncidente1);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente1, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente1));

                        lbPonerNombreUsuario1.setText(nombre1);
                        lbPonerTipoIncidente1.setText(tipo1);
                        lbPonerHoraIncidente1.setText(hora1);
                        lbPonerFechaIncidente1.setText(fecha1);
                        lbPonerUbicacionIncidente1.setText(ubicacion1);

                        // 2
                        idsUsuarios.add((int) datosPublicaciones.get(2).get(1));
                        String nombre2 = solicitarNombreUsuario((int) datosPublicaciones.get(2).get(1));
                        String tipo2 = (String) datosPublicaciones.get(2).get(2);
                        String hora2 = (String) datosPublicaciones.get(2).get(7);
                        String fecha2 = (String) datosPublicaciones.get(2).get(3);
                        String ubicacion2 = (String) datosPublicaciones.get(2).get(5) + " - "
                                        + (String) datosPublicaciones.get(2).get(4);
                        int idIncidente2 = (int) datosPublicaciones.get(2).get(0);
                        Conexion.recibirEvidencia(idIncidente2);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente2, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente2));

                        lbPonerNombreUsuario2.setText(nombre2);
                        lbPonerTipoIncidente2.setText(tipo2);
                        lbPonerHoraIncidente2.setText(hora2);
                        lbPonerFechaIncidente2.setText(fecha2);
                        lbPonerUbicacionIncidente2.setText(ubicacion2);

                        // 3
                        idsUsuarios.add((int) datosPublicaciones.get(3).get(1));
                        String nombre3 = solicitarNombreUsuario((int) datosPublicaciones.get(3).get(1));
                        String tipo3 = (String) datosPublicaciones.get(3).get(2);
                        String hora3 = (String) datosPublicaciones.get(3).get(7);
                        String fecha3 = (String) datosPublicaciones.get(3).get(3);
                        String ubicacion3 = (String) datosPublicaciones.get(3).get(5) + " - "
                                        + (String) datosPublicaciones.get(3).get(4);
                        int idIncidente3 = (int) datosPublicaciones.get(3).get(0);
                        Conexion.recibirEvidencia(idIncidente3);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente3, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente3));

                        lbPonerNombreUsuario3.setText(nombre3);
                        lbPonerTipoIncidente3.setText(tipo3);
                        lbPonerHoraIncidente3.setText(hora3);
                        lbPonerFechaIncidente3.setText(fecha3);
                        lbPonerUbicacionIncidente3.setText(ubicacion3);

                        // 4
                        idsUsuarios.add((int) datosPublicaciones.get(4).get(1));
                        String nombre4 = solicitarNombreUsuario((int) datosPublicaciones.get(4).get(1));
                        String tipo4 = (String) datosPublicaciones.get(4).get(2);
                        String hora4 = (String) datosPublicaciones.get(4).get(7);
                        String fecha4 = (String) datosPublicaciones.get(4).get(3);
                        String ubicacion4 = (String) datosPublicaciones.get(4).get(5) + " - "
                                        + (String) datosPublicaciones.get(4).get(4);
                        int idIncidente4 = (int) datosPublicaciones.get(4).get(0);
                        Conexion.recibirEvidencia(idIncidente4);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente4, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente4));

                        lbPonerNombreUsuario4.setText(nombre4);
                        lbPonerTipoIncidente4.setText(tipo4);
                        lbPonerHoraIncidente4.setText(hora4);
                        lbPonerFechaIncidente4.setText(fecha4);
                        lbPonerUbicacionIncidente4.setText(ubicacion4);

                        // 5
                        idsUsuarios.add((int) datosPublicaciones.get(5).get(1));
                        String nombre5 = solicitarNombreUsuario((int) datosPublicaciones.get(5).get(1));
                        String tipo5 = (String) datosPublicaciones.get(5).get(2);
                        String hora5 = (String) datosPublicaciones.get(5).get(7);
                        String fecha5 = (String) datosPublicaciones.get(5).get(3);
                        String ubicacion5 = (String) datosPublicaciones.get(5).get(5) + " - "
                                        + (String) datosPublicaciones.get(5).get(4);
                        int idIncidente5 = (int) datosPublicaciones.get(5).get(0);
                        Conexion.recibirEvidencia(idIncidente5);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente5, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente5));

                        lbPonerNombreUsuario5.setText(nombre5);
                        lbPonerTipoIncidente5.setText(tipo5);
                        lbPonerHoraIncidente5.setText(hora5);
                        lbPonerFechaIncidente5.setText(fecha5);
                        lbPonerUbicacionIncidente5.setText(ubicacion5);

                        // 6
                        idsUsuarios.add((int) datosPublicaciones.get(6).get(1));
                        String nombre6 = solicitarNombreUsuario((int) datosPublicaciones.get(6).get(1));
                        String tipo6 = (String) datosPublicaciones.get(6).get(2);
                        String hora6 = (String) datosPublicaciones.get(6).get(7);
                        String fecha6 = (String) datosPublicaciones.get(6).get(3);
                        String ubicacion6 = (String) datosPublicaciones.get(6).get(5) + " - "
                                        + (String) datosPublicaciones.get(6).get(4);
                        int idIncidente6 = (int) datosPublicaciones.get(6).get(0);
                        Conexion.recibirEvidencia(idIncidente6);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente6, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente6));

                        lbPonerNombreUsuario6.setText(nombre6);
                        lbPonerTipoIncidente6.setText(tipo6);
                        lbPonerHoraIncidente6.setText(hora6);
                        lbPonerFechaIncidente6.setText(fecha6);
                        lbPonerUbicacionIncidente6.setText(ubicacion6);
                        // 7
                        idsUsuarios.add((int) datosPublicaciones.get(7).get(1));
                        String nombre7 = solicitarNombreUsuario((int) datosPublicaciones.get(7).get(1));
                        String tipo7 = (String) datosPublicaciones.get(7).get(2);
                        String hora7 = (String) datosPublicaciones.get(7).get(7);
                        String fecha7 = (String) datosPublicaciones.get(7).get(3);
                        String ubicacion7 = (String) datosPublicaciones.get(7).get(5) + " - "
                                        + (String) datosPublicaciones.get(7).get(4);
                        int idIncidente7 = (int) datosPublicaciones.get(7).get(0);
                        Conexion.recibirEvidencia(idIncidente7);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente7, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente7));

                        lbPonerNombreUsuario7.setText(nombre7);
                        lbPonerTipoIncidente7.setText(tipo7);
                        lbPonerHoraIncidente7.setText(hora7);
                        lbPonerFechaIncidente7.setText(fecha7);
                        lbPonerUbicacionIncidente7.setText(ubicacion7);
                        // 8
                        idsUsuarios.add((int) datosPublicaciones.get(8).get(1));
                        String nombre8 = solicitarNombreUsuario((int) datosPublicaciones.get(8).get(1));
                        String tipo8 = (String) datosPublicaciones.get(8).get(2);
                        String hora8 = (String) datosPublicaciones.get(8).get(7);
                        String fecha8 = (String) datosPublicaciones.get(8).get(3);
                        String ubicacion8 = (String) datosPublicaciones.get(8).get(5) + " - "
                                        + (String) datosPublicaciones.get(8).get(4);
                        int idIncidente8 = (int) datosPublicaciones.get(8).get(0);
                        Conexion.recibirEvidencia(idIncidente8);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente8, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente8));

                        lbPonerNombreUsuario8.setText(nombre8);
                        lbPonerTipoIncidente8.setText(tipo8);
                        lbPonerHoraIncidente8.setText(hora8);
                        lbPonerFechaIncidente8.setText(fecha8);
                        lbPonerUbicacionIncidente8.setText(ubicacion8);
                        // 9
                        idsUsuarios.add((int) datosPublicaciones.get(9).get(1));
                        String nombre9 = solicitarNombreUsuario((int) datosPublicaciones.get(9).get(1));
                        String tipo9 = (String) datosPublicaciones.get(9).get(2);
                        String hora9 = (String) datosPublicaciones.get(9).get(7);
                        String fecha9 = (String) datosPublicaciones.get(9).get(3);
                        String ubicacion9 = (String) datosPublicaciones.get(9).get(5) + " - "
                                        + (String) datosPublicaciones.get(9).get(4);
                        int idIncidente9 = (int) datosPublicaciones.get(9).get(0);
                        Conexion.recibirEvidencia(idIncidente9);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente9, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente9));

                        lbPonerNombreUsuario9.setText(nombre9);
                        lbPonerTipoIncidente9.setText(tipo9);
                        lbPonerHoraIncidente9.setText(hora9);
                        lbPonerFechaIncidente9.setText(fecha9);
                        lbPonerUbicacionIncidente9.setText(ubicacion9);
                        // 10
                        idsUsuarios.add((int) datosPublicaciones.get(10).get(1));
                        String nombre10 = solicitarNombreUsuario((int) datosPublicaciones.get(10).get(1));
                        String tipo10 = (String) datosPublicaciones.get(10).get(2);
                        String hora10 = (String) datosPublicaciones.get(10).get(7);
                        String fecha10 = (String) datosPublicaciones.get(10).get(3);
                        String ubicacion10 = (String) datosPublicaciones.get(10).get(5) + " - "
                                        + (String) datosPublicaciones.get(10).get(4);
                        int idIncidente10 = (int) datosPublicaciones.get(10).get(0);
                        Conexion.recibirEvidencia(idIncidente10);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente10, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente10));

                        lbPonerNombreUsuario10.setText(nombre10);
                        lbPonerTipoIncidente10.setText(tipo10);
                        lbPonerHoraIncidente10.setText(hora10);
                        lbPonerFechaIncidente10.setText(fecha10);
                        lbPonerUbicacionIncidente10.setText(ubicacion10);
                        // 11
                        idsUsuarios.add((int) datosPublicaciones.get(11).get(1));
                        String nombre11 = solicitarNombreUsuario((int) datosPublicaciones.get(11).get(1));
                        String tipo11 = (String) datosPublicaciones.get(11).get(2);
                        String hora11 = (String) datosPublicaciones.get(11).get(7);
                        String fecha11 = (String) datosPublicaciones.get(11).get(3);
                        String ubicacion11 = (String) datosPublicaciones.get(11).get(5) + " - "
                                        + (String) datosPublicaciones.get(11).get(4);
                        int idIncidente11 = (int) datosPublicaciones.get(11).get(0);
                        Conexion.recibirEvidencia(idIncidente11);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente11, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente11));

                        lbPonerNombreUsuario11.setText(nombre11);
                        lbPonerTipoIncidente11.setText(tipo11);
                        lbPonerHoraIncidente11.setText(hora11);
                        lbPonerFechaIncidente11.setText(fecha11);
                        lbPonerUbicacionIncidente11.setText(ubicacion11);
                        // 12
                        idsUsuarios.add((int) datosPublicaciones.get(12).get(1));
                        String nombre12 = solicitarNombreUsuario((int) datosPublicaciones.get(12).get(1));
                        String tipo12 = (String) datosPublicaciones.get(12).get(2);
                        String hora12 = (String) datosPublicaciones.get(12).get(7);
                        String fecha12 = (String) datosPublicaciones.get(12).get(3);
                        String ubicacion12 = (String) datosPublicaciones.get(12).get(5) + " - "
                                        + (String) datosPublicaciones.get(12).get(4);
                        int idIncidente12 = (int) datosPublicaciones.get(12).get(0);
                        Conexion.recibirEvidencia(idIncidente12);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente12, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente12));

                        lbPonerNombreUsuario12.setText(nombre12);
                        lbPonerTipoIncidente12.setText(tipo12);
                        lbPonerHoraIncidente12.setText(hora12);
                        lbPonerFechaIncidente12.setText(fecha12);
                        lbPonerUbicacionIncidente12.setText(ubicacion12);
                        // 13
                        idsUsuarios.add((int) datosPublicaciones.get(13).get(1));
                        String nombre13 = solicitarNombreUsuario((int) datosPublicaciones.get(13).get(1));
                        String tipo13 = (String) datosPublicaciones.get(13).get(2);
                        String hora13 = (String) datosPublicaciones.get(13).get(7);
                        String fecha13 = (String) datosPublicaciones.get(13).get(3);
                        String ubicacion13 = (String) datosPublicaciones.get(13).get(5) + " - "
                                        + (String) datosPublicaciones.get(13).get(4);
                        int idIncidente13 = (int) datosPublicaciones.get(13).get(0);
                        Conexion.recibirEvidencia(idIncidente13);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente13, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente13));

                        lbPonerNombreUsuario13.setText(nombre13);
                        lbPonerTipoIncidente13.setText(tipo13);
                        lbPonerHoraIncidente13.setText(hora13);
                        lbPonerFechaIncidente13.setText(fecha13);
                        lbPonerUbicacionIncidente13.setText(ubicacion13);

                        // 14
                        idsUsuarios.add((int) datosPublicaciones.get(14).get(1));
                        String nombre14 = solicitarNombreUsuario((int) datosPublicaciones.get(14).get(1));
                        String tipo14 = (String) datosPublicaciones.get(14).get(2);
                        String hora14 = (String) datosPublicaciones.get(14).get(7);
                        String fecha14 = (String) datosPublicaciones.get(14).get(3);
                        String ubicacion14 = (String) datosPublicaciones.get(14).get(5) + " - "
                                        + (String) datosPublicaciones.get(14).get(4);
                        int idIncidente14 = (int) datosPublicaciones.get(14).get(0);
                        Conexion.recibirEvidencia(idIncidente14);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente14, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente14));

                        lbPonerNombreUsuario14.setText(nombre14);
                        lbPonerTipoIncidente14.setText(tipo14);
                        lbPonerHoraIncidente14.setText(hora14);
                        lbPonerFechaIncidente14.setText(fecha14);
                        lbPonerUbicacionIncidente14.setText(ubicacion14);
                        // 15
                        idsUsuarios.add((int) datosPublicaciones.get(15).get(1));
                        String nombre15 = solicitarNombreUsuario((int) datosPublicaciones.get(15).get(1));
                        String tipo15 = (String) datosPublicaciones.get(15).get(2);
                        String hora15 = (String) datosPublicaciones.get(15).get(7);
                        String fecha15 = (String) datosPublicaciones.get(15).get(3);
                        String ubicacion15 = (String) datosPublicaciones.get(15).get(5) + " - "
                                        + (String) datosPublicaciones.get(15).get(4);
                        int idIncidente15 = (int) datosPublicaciones.get(15).get(0);
                        Conexion.recibirEvidencia(idIncidente15);
                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente15, String.format(
                                        "C:\\Users\\tutaa\\Workspace\\Java\\Projects\\EcoGuard\\imgPublicaciones\\%d.jpg",
                                        idIncidente15));

                        lbPonerNombreUsuario15.setText(nombre15);
                        lbPonerTipoIncidente15.setText(tipo15);
                        lbPonerHoraIncidente15.setText(hora15);
                        lbPonerFechaIncidente15.setText(fecha15);
                        lbPonerUbicacionIncidente15.setText(ubicacion15);

                } catch (Exception e) {
                }
        }

        public String solicitarNombreUsuario(int id_usuario) {
                String nombre = "";
                try {
                        nombre = (String) Conexion.seleccionar(
                                        String.format("SELECT * FROM usuarios WHERE id_usuario = %d ",
                                                        id_usuario),
                                        new String[] { "nombre" }).get(0).get(0);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return nombre;
        }

        public ArrayList<ArrayList<Object>> solicitarPublicaciones() {
                ArrayList<ArrayList<Object>> publicaciones = new ArrayList<>();
                try {
                        publicaciones = Conexion.seleccionar("SELECT * FROM incidentes_ambientales",
                                        new String[] { "id_incidente", "id_usuario", "tipo", "fecha", "departamento",
                                                        "ciudad", "informacion", "hora" });
                        return publicaciones;
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return publicaciones;
        }

        public void agregarMarcadores() {
                var marcadores = solicitarPublicaciones();

                for (int i = 0; i < marcadores.size(); i++) {
                        int idPublicacion = (int) marcadores.get(i).get(0);
                        int x = (puntos.get(marcadores.get(i).get(4))).get(0).get(0);
                        int y = (puntos.get(marcadores.get(i).get(4))).get(0).get(1);
                        String rutaIcono = incidentesAmbientales.get(marcadores.get(i).get(2));
                        crearLabel(x, y, rutaIcono, idPublicacion);
                }

        }

        public void mostrarUsuario(int idUsuario) {
                if (idUsuario != 0) {
                        ViewProfile.idUsuarioMostrar = idUsuario;
                        ViewProfile ver = new ViewProfile();
                        ver.setVisible(true);
                } else {
                        JOptionPane.showMessageDialog(null, "SELECCIONE UN USUARIO VÁLIDO");
                }
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaPrincipal = new javax.swing.JPanel();
                imgPublicar = new javax.swing.JLabel();
                imgReload = new javax.swing.JLabel();
                panelMenu = new javax.swing.JPanel();
                imgCasa = new javax.swing.JLabel();
                imgVolver = new javax.swing.JLabel();
                imgUsuario = new javax.swing.JLabel();
                imgMenuBar = new javax.swing.JLabel();
                lbPonerUbicacion = new javax.swing.JLabel();
                lbPublicaciones = new javax.swing.JLabel();
                panelMapa = new javax.swing.JPanel();
                scrollPublicaciones = new Screens.Principal.Custom.ScrollPaneWin11();
                panelPublicaciones = new javax.swing.JPanel();
                panelPublicacion15 = new javax.swing.JPanel();
                imgLike15 = new javax.swing.JLabel();
                lbPonerHoraIncidente15 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente15 = new javax.swing.JLabel();
                lbPonerFechaIncidente15 = new javax.swing.JLabel();
                lbPonerTipoIncidente15 = new javax.swing.JLabel();
                lbPonerNombreUsuario15 = new javax.swing.JLabel();
                imgIconoPublicacion15 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente15 = new javax.swing.JLabel();
                imgFondoPub15 = new javax.swing.JLabel();
                panelPublicacion14 = new javax.swing.JPanel();
                imgLike14 = new javax.swing.JLabel();
                lbPonerHoraIncidente14 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente14 = new javax.swing.JLabel();
                lbPonerFechaIncidente14 = new javax.swing.JLabel();
                lbPonerTipoIncidente14 = new javax.swing.JLabel();
                lbPonerNombreUsuario14 = new javax.swing.JLabel();
                imgIconoPublicacion14 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente14 = new javax.swing.JLabel();
                imgFondoPub14 = new javax.swing.JLabel();
                panelPublicacion13 = new javax.swing.JPanel();
                imgLike13 = new javax.swing.JLabel();
                lbPonerHoraIncidente13 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente13 = new javax.swing.JLabel();
                lbPonerFechaIncidente13 = new javax.swing.JLabel();
                lbPonerTipoIncidente13 = new javax.swing.JLabel();
                lbPonerNombreUsuario13 = new javax.swing.JLabel();
                imgIconoPublicacion13 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente13 = new javax.swing.JLabel();
                imgFondoPub13 = new javax.swing.JLabel();
                panelPublicacion12 = new javax.swing.JPanel();
                imgLike12 = new javax.swing.JLabel();
                lbPonerHoraIncidente12 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente12 = new javax.swing.JLabel();
                lbPonerFechaIncidente12 = new javax.swing.JLabel();
                lbPonerTipoIncidente12 = new javax.swing.JLabel();
                lbPonerNombreUsuario12 = new javax.swing.JLabel();
                imgIconoPublicacion12 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente12 = new javax.swing.JLabel();
                imgFondoPub12 = new javax.swing.JLabel();
                panelPublicacion11 = new javax.swing.JPanel();
                imgLike11 = new javax.swing.JLabel();
                lbPonerHoraIncidente11 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente11 = new javax.swing.JLabel();
                lbPonerFechaIncidente11 = new javax.swing.JLabel();
                lbPonerTipoIncidente11 = new javax.swing.JLabel();
                lbPonerNombreUsuario11 = new javax.swing.JLabel();
                imgIconoPublicacion11 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente11 = new javax.swing.JLabel();
                imgFondoPub11 = new javax.swing.JLabel();
                panelPublicacion10 = new javax.swing.JPanel();
                imgLike10 = new javax.swing.JLabel();
                lbPonerHoraIncidente10 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente10 = new javax.swing.JLabel();
                lbPonerFechaIncidente10 = new javax.swing.JLabel();
                lbPonerTipoIncidente10 = new javax.swing.JLabel();
                lbPonerNombreUsuario10 = new javax.swing.JLabel();
                imgIconoPublicacion10 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente10 = new javax.swing.JLabel();
                imgFondoPub10 = new javax.swing.JLabel();
                panelPublicacion9 = new javax.swing.JPanel();
                imgLike9 = new javax.swing.JLabel();
                lbPonerHoraIncidente9 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente9 = new javax.swing.JLabel();
                lbPonerFechaIncidente9 = new javax.swing.JLabel();
                lbPonerTipoIncidente9 = new javax.swing.JLabel();
                lbPonerNombreUsuario9 = new javax.swing.JLabel();
                imgIconoPublicacion9 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente9 = new javax.swing.JLabel();
                imgFondoPub9 = new javax.swing.JLabel();
                panelPublicacion8 = new javax.swing.JPanel();
                imgLike8 = new javax.swing.JLabel();
                lbPonerHoraIncidente8 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente8 = new javax.swing.JLabel();
                lbPonerFechaIncidente8 = new javax.swing.JLabel();
                lbPonerTipoIncidente8 = new javax.swing.JLabel();
                lbPonerNombreUsuario8 = new javax.swing.JLabel();
                imgIconoPublicacion8 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente8 = new javax.swing.JLabel();
                imgFondoPub8 = new javax.swing.JLabel();
                panelPublicacion7 = new javax.swing.JPanel();
                imgLike7 = new javax.swing.JLabel();
                lbPonerHoraIncidente7 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente7 = new javax.swing.JLabel();
                lbPonerFechaIncidente7 = new javax.swing.JLabel();
                lbPonerTipoIncidente7 = new javax.swing.JLabel();
                lbPonerNombreUsuario7 = new javax.swing.JLabel();
                imgIconoPublicacion7 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente7 = new javax.swing.JLabel();
                imgFondoPub7 = new javax.swing.JLabel();
                panelPublicacion6 = new javax.swing.JPanel();
                imgLike6 = new javax.swing.JLabel();
                lbPonerHoraIncidente6 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente6 = new javax.swing.JLabel();
                lbPonerFechaIncidente6 = new javax.swing.JLabel();
                lbPonerTipoIncidente6 = new javax.swing.JLabel();
                lbPonerNombreUsuario6 = new javax.swing.JLabel();
                imgIconoPublicacion6 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente6 = new javax.swing.JLabel();
                imgFondoPub6 = new javax.swing.JLabel();
                panelPublicacion5 = new javax.swing.JPanel();
                imgLike5 = new javax.swing.JLabel();
                lbPonerHoraIncidente5 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente5 = new javax.swing.JLabel();
                lbPonerFechaIncidente5 = new javax.swing.JLabel();
                lbPonerTipoIncidente5 = new javax.swing.JLabel();
                lbPonerNombreUsuario5 = new javax.swing.JLabel();
                imgIconoPublicacion5 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente5 = new javax.swing.JLabel();
                imgFondoPub5 = new javax.swing.JLabel();
                panelPublicacion4 = new javax.swing.JPanel();
                imgLike4 = new javax.swing.JLabel();
                lbPonerHoraIncidente4 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente4 = new javax.swing.JLabel();
                lbPonerFechaIncidente4 = new javax.swing.JLabel();
                lbPonerTipoIncidente4 = new javax.swing.JLabel();
                lbPonerNombreUsuario4 = new javax.swing.JLabel();
                imgIconoPublicacion4 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente4 = new javax.swing.JLabel();
                imgFondoPub4 = new javax.swing.JLabel();
                panelPublicacion3 = new javax.swing.JPanel();
                imgLike3 = new javax.swing.JLabel();
                lbPonerHoraIncidente3 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente3 = new javax.swing.JLabel();
                lbPonerFechaIncidente3 = new javax.swing.JLabel();
                lbPonerTipoIncidente3 = new javax.swing.JLabel();
                lbPonerNombreUsuario3 = new javax.swing.JLabel();
                imgIconoPublicacion3 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente3 = new javax.swing.JLabel();
                imgFondoPub3 = new javax.swing.JLabel();
                panelPublicacion2 = new javax.swing.JPanel();
                imgLike2 = new javax.swing.JLabel();
                lbPonerHoraIncidente2 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente2 = new javax.swing.JLabel();
                lbPonerFechaIncidente2 = new javax.swing.JLabel();
                lbPonerTipoIncidente2 = new javax.swing.JLabel();
                lbPonerNombreUsuario2 = new javax.swing.JLabel();
                imgIconoPublicacion2 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente2 = new javax.swing.JLabel();
                imgFondoPub2 = new javax.swing.JLabel();
                panelPublicacion1 = new javax.swing.JPanel();
                imgLike1 = new javax.swing.JLabel();
                lbPonerHoraIncidente1 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente1 = new javax.swing.JLabel();
                lbPonerFechaIncidente1 = new javax.swing.JLabel();
                lbPonerTipoIncidente1 = new javax.swing.JLabel();
                lbPonerNombreUsuario1 = new javax.swing.JLabel();
                imgIconoPublicacion1 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente1 = new javax.swing.JLabel();
                imgFondoPub1 = new javax.swing.JLabel();
                panelPublicacion0 = new javax.swing.JPanel();
                imgLike0 = new javax.swing.JLabel();
                lbPonerHoraIncidente0 = new javax.swing.JLabel();
                lbPonerUbicacionIncidente0 = new javax.swing.JLabel();
                lbPonerFechaIncidente0 = new javax.swing.JLabel();
                lbPonerTipoIncidente0 = new javax.swing.JLabel();
                lbPonerNombreUsuario0 = new javax.swing.JLabel();
                imgIconoPublicacion0 = new javax.swing.JLabel();
                imgPonerEvidenciaIncidente0 = new javax.swing.JLabel();
                imgFondoPub0 = new javax.swing.JLabel();
                imgMapa = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaPrincipal.setBackground(new java.awt.Color(22, 22, 26));
                ventanaPrincipal.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgPublicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnPublicar.png"))); // NOI18N
                imgPublicar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgPublicar.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgPublicarMouseClicked(evt);
                        }
                });
                ventanaPrincipal.add(imgPublicar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, -1, -1));

                imgReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reloadPequeño.png"))); // NOI18N
                imgReload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgReload.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgReloadMouseClicked(evt);
                        }
                });
                ventanaPrincipal.add(imgReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 110, -1, -1));

                panelMenu.setBackground(new java.awt.Color(22, 22, 26));
                panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casaHover2.png"))); // NOI18N
                imgCasa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                panelMenu.add(imgCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

                imgVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/volver.png"))); // NOI18N
                imgVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgVolver.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgVolverMouseClicked(evt);
                        }

                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                imgVolverMouseEntered(evt);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                imgVolverMouseExited(evt);
                        }
                });
                panelMenu.add(imgVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

                imgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
                imgUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                imgUsuarioMouseClicked(evt);
                        }

                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                imgUsuarioMouseEntered(evt);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                imgUsuarioMouseExited(evt);
                        }
                });
                panelMenu.add(imgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

                imgMenuBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                imgMenuBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lineaVertical.png"))); // NOI18N
                imgMenuBar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
                imgMenuBar.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                imgMenuBarMouseEntered(evt);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                imgMenuBarMouseExited(evt);
                        }
                });
                panelMenu.add(imgMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, -1));

                ventanaPrincipal.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, 60, 220));

                lbPonerUbicacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerUbicacion.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                lbPonerUbicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/marcador.png"))); // NOI18N
                lbPonerUbicacion.setText("-");
                ventanaPrincipal.add(lbPonerUbicacion,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, -1));

                lbPublicaciones.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbPublicaciones.setForeground(new java.awt.Color(255, 255, 254));
                lbPublicaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPublicaciones.setText("Publicaciones");
                ventanaPrincipal.add(lbPublicaciones,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, -1));

                panelMapa.setBackground(new java.awt.Color(22, 22, 26));
                panelMapa.setOpaque(false);
                panelMapa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                ventanaPrincipal.add(panelMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 420, 550));

                panelPublicaciones.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicaciones.setMinimumSize(new java.awt.Dimension(350, 100000));
                panelPublicaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                panelPublicacion15.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion15.setEnabled(false);
                panelPublicacion15.setFocusable(false);
                panelPublicacion15.setOpaque(false);
                panelPublicacion15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike15.setDisabledIcon(null);
                panelPublicacion15.add(imgLike15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente15.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente15.setText("-");
                panelPublicacion15.add(lbPonerHoraIncidente15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente15.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente15.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente15.setText("-");
                panelPublicacion15.add(lbPonerUbicacionIncidente15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente15.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente15.setText("-");
                panelPublicacion15.add(lbPonerFechaIncidente15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente15.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente15.setText("-");
                panelPublicacion15.add(lbPonerTipoIncidente15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario15.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario15.setText("-");
                lbPonerNombreUsuario15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario15.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario15MouseClicked(evt);
                        }
                });
                panelPublicacion15.add(lbPonerNombreUsuario15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion15.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion15.add(imgIconoPublicacion15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente15.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion15.add(imgPonerEvidenciaIncidente15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub15.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion15.add(imgFondoPub15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion15,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2870, 310, 200));

                panelPublicacion14.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion14.setEnabled(false);
                panelPublicacion14.setFocusable(false);
                panelPublicacion14.setOpaque(false);
                panelPublicacion14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike14.setDisabledIcon(null);
                panelPublicacion14.add(imgLike14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente14.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente14.setText("-");
                panelPublicacion14.add(lbPonerHoraIncidente14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente14.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente14.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente14.setText("-");
                panelPublicacion14.add(lbPonerUbicacionIncidente14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente14.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente14.setText("-");
                panelPublicacion14.add(lbPonerFechaIncidente14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente14.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente14.setText("-");
                panelPublicacion14.add(lbPonerTipoIncidente14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario14.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario14.setText("-");
                lbPonerNombreUsuario14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario14.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario14MouseClicked(evt);
                        }
                });
                panelPublicacion14.add(lbPonerNombreUsuario14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion14.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion14.add(imgIconoPublicacion14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente14.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion14.add(imgPonerEvidenciaIncidente14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub14.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion14.add(imgFondoPub14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion14,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2680, 310, 200));

                panelPublicacion13.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion13.setEnabled(false);
                panelPublicacion13.setFocusable(false);
                panelPublicacion13.setOpaque(false);
                panelPublicacion13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike13.setDisabledIcon(null);
                panelPublicacion13.add(imgLike13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente13.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente13.setText("-");
                panelPublicacion13.add(lbPonerHoraIncidente13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente13.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente13.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente13.setText("-");
                panelPublicacion13.add(lbPonerUbicacionIncidente13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente13.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente13.setText("-");
                panelPublicacion13.add(lbPonerFechaIncidente13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente13.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente13.setText("-");
                panelPublicacion13.add(lbPonerTipoIncidente13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario13.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario13.setText("-");
                lbPonerNombreUsuario13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario13.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario13MouseClicked(evt);
                        }
                });
                panelPublicacion13.add(lbPonerNombreUsuario13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion13.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion13.add(imgIconoPublicacion13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente13.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion13.add(imgPonerEvidenciaIncidente13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub13.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion13.add(imgFondoPub13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion13,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2490, 310, 200));

                panelPublicacion12.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion12.setEnabled(false);
                panelPublicacion12.setFocusable(false);
                panelPublicacion12.setOpaque(false);
                panelPublicacion12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike12.setDisabledIcon(null);
                panelPublicacion12.add(imgLike12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente12.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente12.setText("-");
                panelPublicacion12.add(lbPonerHoraIncidente12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente12.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente12.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente12.setText("-");
                panelPublicacion12.add(lbPonerUbicacionIncidente12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente12.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente12.setText("-");
                panelPublicacion12.add(lbPonerFechaIncidente12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente12.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente12.setText("-");
                panelPublicacion12.add(lbPonerTipoIncidente12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario12.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario12.setText("-");
                lbPonerNombreUsuario12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario12.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario12MouseClicked(evt);
                        }
                });
                panelPublicacion12.add(lbPonerNombreUsuario12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion12.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion12.add(imgIconoPublicacion12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente12.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion12.add(imgPonerEvidenciaIncidente12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub12.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion12.add(imgFondoPub12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion12,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2300, 310, 200));

                panelPublicacion11.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion11.setEnabled(false);
                panelPublicacion11.setFocusable(false);
                panelPublicacion11.setOpaque(false);
                panelPublicacion11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike11.setDisabledIcon(null);
                panelPublicacion11.add(imgLike11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente11.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente11.setText("-");
                panelPublicacion11.add(lbPonerHoraIncidente11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente11.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente11.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente11.setText("-");
                panelPublicacion11.add(lbPonerUbicacionIncidente11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente11.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente11.setText("-");
                panelPublicacion11.add(lbPonerFechaIncidente11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente11.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente11.setText("-");
                panelPublicacion11.add(lbPonerTipoIncidente11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario11.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario11.setText("-");
                lbPonerNombreUsuario11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario11.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario11MouseClicked(evt);
                        }
                });
                panelPublicacion11.add(lbPonerNombreUsuario11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion11.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion11.add(imgIconoPublicacion11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente11.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion11.add(imgPonerEvidenciaIncidente11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub11.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion11.add(imgFondoPub11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion11,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2110, 310, 200));

                panelPublicacion10.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion10.setEnabled(false);
                panelPublicacion10.setFocusable(false);
                panelPublicacion10.setOpaque(false);
                panelPublicacion10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike10.setDisabledIcon(null);
                panelPublicacion10.add(imgLike10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente10.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente10.setText("-");
                panelPublicacion10.add(lbPonerHoraIncidente10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente10.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente10.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente10.setText("-");
                panelPublicacion10.add(lbPonerUbicacionIncidente10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente10.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente10.setText("-");
                panelPublicacion10.add(lbPonerFechaIncidente10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente10.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente10.setText("-");
                panelPublicacion10.add(lbPonerTipoIncidente10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario10.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario10.setText("-");
                lbPonerNombreUsuario10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario10.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario10MouseClicked(evt);
                        }
                });
                panelPublicacion10.add(lbPonerNombreUsuario10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion10.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion10.add(imgIconoPublicacion10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente10.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion10.add(imgPonerEvidenciaIncidente10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub10.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion10.add(imgFondoPub10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion10,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1920, 310, 200));

                panelPublicacion9.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion9.setEnabled(false);
                panelPublicacion9.setFocusable(false);
                panelPublicacion9.setOpaque(false);
                panelPublicacion9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike9.setDisabledIcon(null);
                panelPublicacion9.add(imgLike9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente9.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente9.setText("-");
                panelPublicacion9.add(lbPonerHoraIncidente9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente9.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente9.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente9.setText("-");
                panelPublicacion9.add(lbPonerUbicacionIncidente9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente9.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente9.setText("-");
                panelPublicacion9.add(lbPonerFechaIncidente9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente9.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente9.setText("-");
                panelPublicacion9.add(lbPonerTipoIncidente9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario9.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario9.setText("-");
                lbPonerNombreUsuario9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario9.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario9MouseClicked(evt);
                        }
                });
                panelPublicacion9.add(lbPonerNombreUsuario9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion9.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion9.add(imgIconoPublicacion9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente9.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion9.add(imgPonerEvidenciaIncidente9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub9.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion9.add(imgFondoPub9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion9,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1730, 310, 200));

                panelPublicacion8.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion8.setEnabled(false);
                panelPublicacion8.setFocusable(false);
                panelPublicacion8.setOpaque(false);
                panelPublicacion8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike8.setDisabledIcon(null);
                panelPublicacion8.add(imgLike8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente8.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente8.setText("-");
                panelPublicacion8.add(lbPonerHoraIncidente8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente8.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente8.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente8.setText("-");
                panelPublicacion8.add(lbPonerUbicacionIncidente8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente8.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente8.setText("-");
                panelPublicacion8.add(lbPonerFechaIncidente8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente8.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente8.setText("-");
                panelPublicacion8.add(lbPonerTipoIncidente8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario8.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario8.setText("-");
                lbPonerNombreUsuario8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario8.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario8MouseClicked(evt);
                        }
                });
                panelPublicacion8.add(lbPonerNombreUsuario8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion8.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion8.add(imgIconoPublicacion8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente8.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion8.add(imgPonerEvidenciaIncidente8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub8.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion8.add(imgFondoPub8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion8,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1540, 310, 200));

                panelPublicacion7.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion7.setEnabled(false);
                panelPublicacion7.setFocusable(false);
                panelPublicacion7.setOpaque(false);
                panelPublicacion7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike7.setDisabledIcon(null);
                panelPublicacion7.add(imgLike7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente7.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente7.setText("-");
                panelPublicacion7.add(lbPonerHoraIncidente7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente7.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente7.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente7.setText("-");
                panelPublicacion7.add(lbPonerUbicacionIncidente7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente7.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente7.setText("-");
                panelPublicacion7.add(lbPonerFechaIncidente7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente7.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente7.setText("-");
                panelPublicacion7.add(lbPonerTipoIncidente7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario7.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario7.setText("-");
                lbPonerNombreUsuario7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario7.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario7MouseClicked(evt);
                        }
                });
                panelPublicacion7.add(lbPonerNombreUsuario7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion7.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion7.add(imgIconoPublicacion7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente7.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion7.add(imgPonerEvidenciaIncidente7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub7.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion7.add(imgFondoPub7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion7,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1350, 310, 200));

                panelPublicacion6.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion6.setEnabled(false);
                panelPublicacion6.setFocusable(false);
                panelPublicacion6.setOpaque(false);
                panelPublicacion6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike6.setDisabledIcon(null);
                panelPublicacion6.add(imgLike6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente6.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente6.setText("-");
                panelPublicacion6.add(lbPonerHoraIncidente6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente6.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente6.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente6.setText("-");
                panelPublicacion6.add(lbPonerUbicacionIncidente6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente6.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente6.setText("-");
                panelPublicacion6.add(lbPonerFechaIncidente6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente6.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente6.setText("-");
                panelPublicacion6.add(lbPonerTipoIncidente6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario6.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario6.setText("-");
                lbPonerNombreUsuario6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario6.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario6MouseClicked(evt);
                        }
                });
                panelPublicacion6.add(lbPonerNombreUsuario6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion6.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion6.add(imgIconoPublicacion6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente6.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion6.add(imgPonerEvidenciaIncidente6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub6.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion6.add(imgFondoPub6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion6,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1160, 310, 200));

                panelPublicacion5.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion5.setEnabled(false);
                panelPublicacion5.setFocusable(false);
                panelPublicacion5.setOpaque(false);
                panelPublicacion5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike5.setDisabledIcon(null);
                panelPublicacion5.add(imgLike5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente5.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente5.setText("-");
                panelPublicacion5.add(lbPonerHoraIncidente5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente5.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente5.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente5.setText("-");
                panelPublicacion5.add(lbPonerUbicacionIncidente5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente5.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente5.setText("-");
                panelPublicacion5.add(lbPonerFechaIncidente5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente5.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente5.setText("-");
                panelPublicacion5.add(lbPonerTipoIncidente5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario5.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario5.setText("-");
                lbPonerNombreUsuario5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario5.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario5MouseClicked(evt);
                        }
                });
                panelPublicacion5.add(lbPonerNombreUsuario5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion5.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion5.add(imgIconoPublicacion5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente5.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion5.add(imgPonerEvidenciaIncidente5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub5.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion5.add(imgFondoPub5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion5,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 970, 310, 200));

                panelPublicacion4.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion4.setEnabled(false);
                panelPublicacion4.setFocusable(false);
                panelPublicacion4.setOpaque(false);
                panelPublicacion4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike4.setDisabledIcon(null);
                panelPublicacion4.add(imgLike4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente4.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente4.setText("-");
                panelPublicacion4.add(lbPonerHoraIncidente4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente4.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente4.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente4.setText("-");
                panelPublicacion4.add(lbPonerUbicacionIncidente4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente4.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente4.setText("-");
                panelPublicacion4.add(lbPonerFechaIncidente4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente4.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente4.setText("-");
                panelPublicacion4.add(lbPonerTipoIncidente4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario4.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario4.setText("-");
                lbPonerNombreUsuario4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario4.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario4MouseClicked(evt);
                        }
                });
                panelPublicacion4.add(lbPonerNombreUsuario4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion4.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion4.add(imgIconoPublicacion4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente4.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion4.add(imgPonerEvidenciaIncidente4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub4.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion4.add(imgFondoPub4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion4,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 780, 310, 200));

                panelPublicacion3.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion3.setEnabled(false);
                panelPublicacion3.setFocusable(false);
                panelPublicacion3.setOpaque(false);
                panelPublicacion3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike3.setDisabledIcon(null);
                panelPublicacion3.add(imgLike3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente3.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente3.setText("-");
                panelPublicacion3.add(lbPonerHoraIncidente3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente3.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente3.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente3.setText("-");
                panelPublicacion3.add(lbPonerUbicacionIncidente3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente3.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente3.setText("-");
                panelPublicacion3.add(lbPonerFechaIncidente3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente3.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente3.setText("-");
                panelPublicacion3.add(lbPonerTipoIncidente3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario3.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario3.setText("-");
                lbPonerNombreUsuario3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario3.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario3MouseClicked(evt);
                        }
                });
                panelPublicacion3.add(lbPonerNombreUsuario3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion3.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion3.add(imgIconoPublicacion3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente3.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion3.add(imgPonerEvidenciaIncidente3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub3.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion3.add(imgFondoPub3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion3,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 310, 200));

                panelPublicacion2.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion2.setEnabled(false);
                panelPublicacion2.setFocusable(false);
                panelPublicacion2.setOpaque(false);
                panelPublicacion2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike2.setDisabledIcon(null);
                panelPublicacion2.add(imgLike2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente2.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente2.setText("-");
                panelPublicacion2.add(lbPonerHoraIncidente2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente2.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente2.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente2.setText("-");
                panelPublicacion2.add(lbPonerUbicacionIncidente2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente2.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente2.setText("-");
                panelPublicacion2.add(lbPonerFechaIncidente2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente2.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente2.setText("-");
                panelPublicacion2.add(lbPonerTipoIncidente2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario2.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario2.setText("-");
                lbPonerNombreUsuario2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario2MouseClicked(evt);
                        }
                });
                panelPublicacion2.add(lbPonerNombreUsuario2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion2.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion2.add(imgIconoPublicacion2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente2.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion2.add(imgPonerEvidenciaIncidente2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub2.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion2.add(imgFondoPub2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion2,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 310, 200));

                panelPublicacion1.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion1.setEnabled(false);
                panelPublicacion1.setFocusable(false);
                panelPublicacion1.setOpaque(false);
                panelPublicacion1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike1.setDisabledIcon(null);
                panelPublicacion1.add(imgLike1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente1.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente1.setText("-");
                panelPublicacion1.add(lbPonerHoraIncidente1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente1.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente1.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente1.setText("-");
                panelPublicacion1.add(lbPonerUbicacionIncidente1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente1.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente1.setText("-");
                panelPublicacion1.add(lbPonerFechaIncidente1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente1.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente1.setText("-");
                panelPublicacion1.add(lbPonerTipoIncidente1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario1.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario1.setText("-");
                lbPonerNombreUsuario1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario1MouseClicked(evt);
                        }
                });
                panelPublicacion1.add(lbPonerNombreUsuario1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion1.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion1.add(imgIconoPublicacion1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente1.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion1.add(imgPonerEvidenciaIncidente1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub1.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion1.add(imgFondoPub1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion1,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 310, 200));

                panelPublicacion0.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion0.setEnabled(false);
                panelPublicacion0.setFocusable(false);
                panelPublicacion0.setOpaque(false);
                panelPublicacion0.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgLike0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeOff.png"))); // NOI18N
                imgLike0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                imgLike0.setDisabledIcon(null);
                panelPublicacion0.add(imgLike0, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

                lbPonerHoraIncidente0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerHoraIncidente0.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente0.setText("-");
                panelPublicacion0.add(lbPonerHoraIncidente0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbPonerUbicacionIncidente0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerUbicacionIncidente0.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbPonerUbicacionIncidente0.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbPonerUbicacionIncidente0.setText("-");
                panelPublicacion0.add(lbPonerUbicacionIncidente0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbPonerFechaIncidente0.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente0.setText("-");
                panelPublicacion0.add(lbPonerFechaIncidente0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbPonerTipoIncidente0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbPonerTipoIncidente0.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente0.setText("-");
                panelPublicacion0.add(lbPonerTipoIncidente0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbPonerNombreUsuario0.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbPonerNombreUsuario0.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario0.setText("-");
                lbPonerNombreUsuario0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lbPonerNombreUsuario0.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbPonerNombreUsuario0MouseClicked(evt);
                        }
                });
                panelPublicacion0.add(lbPonerNombreUsuario0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion0.setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion0.add(imgIconoPublicacion0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgPonerEvidenciaIncidente0.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion0.add(imgPonerEvidenciaIncidente0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub0.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion0.add(imgFondoPub0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion0,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 310, 200));

                scrollPublicaciones.setViewportView(panelPublicaciones);

                ventanaPrincipal.add(scrollPublicaciones,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 390, 530));

                imgMapa.setBackground(new java.awt.Color(22, 22, 26));
                imgMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mapaColombiaNuevo.png"))); // NOI18N
                ventanaPrincipal.add(imgMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 420, 550));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void imgReloadMouseClicked(java.awt.event.MouseEvent evt) {
                llenarPublicaciones();
        }

        private void lbPonerNombreUsuario0MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(0));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario1MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(1));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario2MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(2));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario3MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(3));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario4MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(4));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario5MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(5));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario6MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(6));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario7MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(7));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario8MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(8));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario9MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(9));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario10MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(10));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario11MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(11));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario12MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(12));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario13MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(13));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario14MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(14));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void lbPonerNombreUsuario15MouseClicked(java.awt.event.MouseEvent evt) {
                try {

                        mostrarUsuario(idsUsuarios.get(14));
                } catch (Exception e) {
                        mostrarUsuario(0);

                }
        }

        private void imgPublicarMouseClicked(java.awt.event.MouseEvent evt) {
                ReportIncident reportar = new ReportIncident();
                reportar.setVisible(true);
                this.setVisible(false);
        }

        private void textosLabels() {
                imgPublicar.setToolTipText("Publicar");
                imgReload.setToolTipText("Recargar Pestaña");
                imgVolver.setToolTipText("Salir");
                imgUsuario.setToolTipText("Usuario");

        }

        private void imgMenuBarMouseExited(java.awt.event.MouseEvent evt) {
        }

        private void imgUsuarioMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgUsuario, "src/img/perfilHover2.png");

        }

        private void imgUsuarioMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgUsuario, "src/img/usuario.png");

        }

        private void imgVolverMouseEntered(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volverHover2.png");

        }

        private void imgVolverMouseExited(java.awt.event.MouseEvent evt) {
                CambiarIU.setImageLabel(imgVolver, "src/img/volver.png");
        }

        private void imgMenuBarMouseEntered(java.awt.event.MouseEvent evt) {
        }

        private void imgVolverMouseClicked(java.awt.event.MouseEvent evt) {

                Login login = new Login();
                login.setVisible(true);
                this.setVisible(false);
        }

        private void imgUsuarioMouseClicked(java.awt.event.MouseEvent evt) {
                PersonalProfile perfil = new PersonalProfile();
                perfil.setVisible(true);
                this.setVisible(false);
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
                // (optional) ">
                /*
                 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
                 * look and feel.
                 * For details see
                 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(Principal.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(Principal.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(Principal.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(Principal.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                }
                // </editor-fold>
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(() -> {
                        new Principal().setVisible(true);
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel imgCasa;
        private javax.swing.JLabel imgFondoPub0;
        private javax.swing.JLabel imgFondoPub1;
        private javax.swing.JLabel imgFondoPub10;
        private javax.swing.JLabel imgFondoPub11;
        private javax.swing.JLabel imgFondoPub12;
        private javax.swing.JLabel imgFondoPub13;
        private javax.swing.JLabel imgFondoPub14;
        private javax.swing.JLabel imgFondoPub15;
        private javax.swing.JLabel imgFondoPub2;
        private javax.swing.JLabel imgFondoPub3;
        private javax.swing.JLabel imgFondoPub4;
        private javax.swing.JLabel imgFondoPub5;
        private javax.swing.JLabel imgFondoPub6;
        private javax.swing.JLabel imgFondoPub7;
        private javax.swing.JLabel imgFondoPub8;
        private javax.swing.JLabel imgFondoPub9;
        private javax.swing.JLabel imgIconoPublicacion0;
        private javax.swing.JLabel imgIconoPublicacion1;
        private javax.swing.JLabel imgIconoPublicacion10;
        private javax.swing.JLabel imgIconoPublicacion11;
        private javax.swing.JLabel imgIconoPublicacion12;
        private javax.swing.JLabel imgIconoPublicacion13;
        private javax.swing.JLabel imgIconoPublicacion14;
        private javax.swing.JLabel imgIconoPublicacion15;
        private javax.swing.JLabel imgIconoPublicacion2;
        private javax.swing.JLabel imgIconoPublicacion3;
        private javax.swing.JLabel imgIconoPublicacion4;
        private javax.swing.JLabel imgIconoPublicacion5;
        private javax.swing.JLabel imgIconoPublicacion6;
        private javax.swing.JLabel imgIconoPublicacion7;
        private javax.swing.JLabel imgIconoPublicacion8;
        private javax.swing.JLabel imgIconoPublicacion9;
        private javax.swing.JLabel imgLike0;
        private javax.swing.JLabel imgLike1;
        private javax.swing.JLabel imgLike10;
        private javax.swing.JLabel imgLike11;
        private javax.swing.JLabel imgLike12;
        private javax.swing.JLabel imgLike13;
        private javax.swing.JLabel imgLike14;
        private javax.swing.JLabel imgLike15;
        private javax.swing.JLabel imgLike2;
        private javax.swing.JLabel imgLike3;
        private javax.swing.JLabel imgLike4;
        private javax.swing.JLabel imgLike5;
        private javax.swing.JLabel imgLike6;
        private javax.swing.JLabel imgLike7;
        private javax.swing.JLabel imgLike8;
        private javax.swing.JLabel imgLike9;
        private javax.swing.JLabel imgMapa;
        private javax.swing.JLabel imgMenuBar;
        private javax.swing.JLabel imgPonerEvidenciaIncidente0;
        private javax.swing.JLabel imgPonerEvidenciaIncidente1;
        private javax.swing.JLabel imgPonerEvidenciaIncidente10;
        private javax.swing.JLabel imgPonerEvidenciaIncidente11;
        private javax.swing.JLabel imgPonerEvidenciaIncidente12;
        private javax.swing.JLabel imgPonerEvidenciaIncidente13;
        private javax.swing.JLabel imgPonerEvidenciaIncidente14;
        private javax.swing.JLabel imgPonerEvidenciaIncidente15;
        private javax.swing.JLabel imgPonerEvidenciaIncidente2;
        private javax.swing.JLabel imgPonerEvidenciaIncidente3;
        private javax.swing.JLabel imgPonerEvidenciaIncidente4;
        private javax.swing.JLabel imgPonerEvidenciaIncidente5;
        private javax.swing.JLabel imgPonerEvidenciaIncidente6;
        private javax.swing.JLabel imgPonerEvidenciaIncidente7;
        private javax.swing.JLabel imgPonerEvidenciaIncidente8;
        private javax.swing.JLabel imgPonerEvidenciaIncidente9;
        private javax.swing.JLabel imgPublicar;
        private javax.swing.JLabel imgReload;
        private javax.swing.JLabel imgUsuario;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JLabel lbPonerFechaIncidente0;
        private javax.swing.JLabel lbPonerFechaIncidente1;
        private javax.swing.JLabel lbPonerFechaIncidente10;
        private javax.swing.JLabel lbPonerFechaIncidente11;
        private javax.swing.JLabel lbPonerFechaIncidente12;
        private javax.swing.JLabel lbPonerFechaIncidente13;
        private javax.swing.JLabel lbPonerFechaIncidente14;
        private javax.swing.JLabel lbPonerFechaIncidente15;
        private javax.swing.JLabel lbPonerFechaIncidente2;
        private javax.swing.JLabel lbPonerFechaIncidente3;
        private javax.swing.JLabel lbPonerFechaIncidente4;
        private javax.swing.JLabel lbPonerFechaIncidente5;
        private javax.swing.JLabel lbPonerFechaIncidente6;
        private javax.swing.JLabel lbPonerFechaIncidente7;
        private javax.swing.JLabel lbPonerFechaIncidente8;
        private javax.swing.JLabel lbPonerFechaIncidente9;
        private javax.swing.JLabel lbPonerHoraIncidente0;
        private javax.swing.JLabel lbPonerHoraIncidente1;
        private javax.swing.JLabel lbPonerHoraIncidente10;
        private javax.swing.JLabel lbPonerHoraIncidente11;
        private javax.swing.JLabel lbPonerHoraIncidente12;
        private javax.swing.JLabel lbPonerHoraIncidente13;
        private javax.swing.JLabel lbPonerHoraIncidente14;
        private javax.swing.JLabel lbPonerHoraIncidente15;
        private javax.swing.JLabel lbPonerHoraIncidente2;
        private javax.swing.JLabel lbPonerHoraIncidente3;
        private javax.swing.JLabel lbPonerHoraIncidente4;
        private javax.swing.JLabel lbPonerHoraIncidente5;
        private javax.swing.JLabel lbPonerHoraIncidente6;
        private javax.swing.JLabel lbPonerHoraIncidente7;
        private javax.swing.JLabel lbPonerHoraIncidente8;
        private javax.swing.JLabel lbPonerHoraIncidente9;
        private javax.swing.JLabel lbPonerNombreUsuario0;
        private javax.swing.JLabel lbPonerNombreUsuario1;
        private javax.swing.JLabel lbPonerNombreUsuario10;
        private javax.swing.JLabel lbPonerNombreUsuario11;
        private javax.swing.JLabel lbPonerNombreUsuario12;
        private javax.swing.JLabel lbPonerNombreUsuario13;
        private javax.swing.JLabel lbPonerNombreUsuario14;
        private javax.swing.JLabel lbPonerNombreUsuario15;
        private javax.swing.JLabel lbPonerNombreUsuario2;
        private javax.swing.JLabel lbPonerNombreUsuario3;
        private javax.swing.JLabel lbPonerNombreUsuario4;
        private javax.swing.JLabel lbPonerNombreUsuario5;
        private javax.swing.JLabel lbPonerNombreUsuario6;
        private javax.swing.JLabel lbPonerNombreUsuario7;
        private javax.swing.JLabel lbPonerNombreUsuario8;
        private javax.swing.JLabel lbPonerNombreUsuario9;
        private javax.swing.JLabel lbPonerTipoIncidente0;
        private javax.swing.JLabel lbPonerTipoIncidente1;
        private javax.swing.JLabel lbPonerTipoIncidente10;
        private javax.swing.JLabel lbPonerTipoIncidente11;
        private javax.swing.JLabel lbPonerTipoIncidente12;
        private javax.swing.JLabel lbPonerTipoIncidente13;
        private javax.swing.JLabel lbPonerTipoIncidente14;
        private javax.swing.JLabel lbPonerTipoIncidente15;
        private javax.swing.JLabel lbPonerTipoIncidente2;
        private javax.swing.JLabel lbPonerTipoIncidente3;
        private javax.swing.JLabel lbPonerTipoIncidente4;
        private javax.swing.JLabel lbPonerTipoIncidente5;
        private javax.swing.JLabel lbPonerTipoIncidente6;
        private javax.swing.JLabel lbPonerTipoIncidente7;
        private javax.swing.JLabel lbPonerTipoIncidente8;
        private javax.swing.JLabel lbPonerTipoIncidente9;
        private javax.swing.JLabel lbPonerUbicacion;
        private javax.swing.JLabel lbPonerUbicacionIncidente0;
        private javax.swing.JLabel lbPonerUbicacionIncidente1;
        private javax.swing.JLabel lbPonerUbicacionIncidente10;
        private javax.swing.JLabel lbPonerUbicacionIncidente11;
        private javax.swing.JLabel lbPonerUbicacionIncidente12;
        private javax.swing.JLabel lbPonerUbicacionIncidente13;
        private javax.swing.JLabel lbPonerUbicacionIncidente14;
        private javax.swing.JLabel lbPonerUbicacionIncidente15;
        private javax.swing.JLabel lbPonerUbicacionIncidente2;
        private javax.swing.JLabel lbPonerUbicacionIncidente3;
        private javax.swing.JLabel lbPonerUbicacionIncidente4;
        private javax.swing.JLabel lbPonerUbicacionIncidente5;
        private javax.swing.JLabel lbPonerUbicacionIncidente6;
        private javax.swing.JLabel lbPonerUbicacionIncidente7;
        private javax.swing.JLabel lbPonerUbicacionIncidente8;
        private javax.swing.JLabel lbPonerUbicacionIncidente9;
        private javax.swing.JLabel lbPublicaciones;
        private javax.swing.JPanel panelMapa;
        private javax.swing.JPanel panelMenu;
        private javax.swing.JPanel panelPublicacion0;
        private javax.swing.JPanel panelPublicacion1;
        private javax.swing.JPanel panelPublicacion10;
        private javax.swing.JPanel panelPublicacion11;
        private javax.swing.JPanel panelPublicacion12;
        private javax.swing.JPanel panelPublicacion13;
        private javax.swing.JPanel panelPublicacion14;
        private javax.swing.JPanel panelPublicacion15;
        private javax.swing.JPanel panelPublicacion2;
        private javax.swing.JPanel panelPublicacion3;
        private javax.swing.JPanel panelPublicacion4;
        private javax.swing.JPanel panelPublicacion5;
        private javax.swing.JPanel panelPublicacion6;
        private javax.swing.JPanel panelPublicacion7;
        private javax.swing.JPanel panelPublicacion8;
        private javax.swing.JPanel panelPublicacion9;
        private javax.swing.JPanel panelPublicaciones;
        private javax.swing.JScrollPane scrollPublicaciones;
        private javax.swing.JPanel ventanaPrincipal;
        // End of variables declaration//GEN-END:variables
}
