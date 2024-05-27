/*
 cSpell:ignore Ubicacion publicacion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal.ViewIncident;

import Code.Conexion;
import Screens.Custom.CambiarIU;
import Screens.Custom.ScrollPaneWin11;
import Screens.Principal.Principal;

import java.awt.Toolkit;
import java.sql.SQLException;

/**
 *
 * @author migue
 */
public class ViewIncident extends javax.swing.JFrame {
        public static int idPublicacion;

        /**
         * Creates new form ViewIncident
         */
        public ViewIncident() {
                initComponents();

                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                ponerDatos();
        }

        private void ponerDatos() {

                Conexion.recibirEvidencia(idPublicacion);

                try {

                        var publicacion = Conexion.seleccionar(
                                        String.format("SELECT * FROM incidentes_ambientales where id_incidente = %d",
                                                        idPublicacion),
                                        new String[] { "id_usuario", "tipo", "fecha", "hora", "departamento", "ciudad",
                                                        "evidencia", "informacion" });
                        int idUsuarioPublicacion = (int) publicacion.get(0).get(0);

                        var datos = Conexion.seleccionar(
                                        String.format("SELECT * FROM usuarios where id_usuario = %d",
                                                        idUsuarioPublicacion),
                                        new String[] { "nombre" });

                        String tipo = (String) publicacion.get(0).get(1);
                        this.setTitle(tipo.substring(0, 1).toUpperCase() + tipo.substring(1).toLowerCase());

                        CambiarIU.ponerTextoEtiqueta(lbPonerNombreUsuario, (String) datos.get(0).get(0));
                        CambiarIU.ponerTextoEtiqueta(lbPonerTipoIncidente, tipo);
                        CambiarIU.ponerTextoEtiqueta(lbPonerFechaIncidente, (String) publicacion.get(0).get(2));
                        CambiarIU.ponerTextoEtiqueta(lbPonerHoraIncidente, (String) publicacion.get(0).get(3));
                        CambiarIU.ponerTextoEtiqueta(lbPonerUbicacionIncidente, ((String) publicacion.get(0).get(5))
                                        + " - " + ((String) publicacion.get(0).get(4)));

                        CambiarIU.ponerTextoArea(txtMostrarInfoIncidente, (String) publicacion.get(0).get(7));

                        CambiarIU.setImageLabel(imgPonerEvidenciaIncidente,
                                        String.format(Conexion.rutaEvidencia, idPublicacion));

                } catch (SQLException e) {
                        System.out.println(e);
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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaMostrarIncidente = new javax.swing.JPanel();
                lbPonerNombreUsuario = new javax.swing.JLabel();
                lbPonerLikes = new javax.swing.JLabel();
                lbPonerTipoIncidente = new javax.swing.JLabel();
                scrollInfoIncidente = new ScrollPaneWin11();
                txtMostrarInfoIncidente = new javax.swing.JTextArea();
                imgPonerEvidenciaIncidente = new javax.swing.JLabel();
                lbPonerFechaIncidente = new javax.swing.JLabel();
                lbPonerHoraIncidente = new javax.swing.JLabel();
                lbPonerUbicacionIncidente = new javax.swing.JLabel();
                btnSalir = new javax.swing.JButton();
                imgFondo = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaMostrarIncidente.setBackground(new java.awt.Color(22, 22, 26));
                ventanaMostrarIncidente.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaMostrarIncidente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbPonerNombreUsuario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
                lbPonerNombreUsuario.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerNombreUsuario.setText("-");
                lbPonerNombreUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                ventanaMostrarIncidente.add(lbPonerNombreUsuario,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 570, -1));

                lbPonerLikes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerLikes.setForeground(new java.awt.Color(255, 255, 255));
                lbPonerLikes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerLikes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/likeVerdeGrande.png"))); // NOI18N
                lbPonerLikes.setText("10");
                ventanaMostrarIncidente.add(lbPonerLikes,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 570, -1));

                lbPonerTipoIncidente.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
                lbPonerTipoIncidente.setForeground(new java.awt.Color(127, 90, 240));
                lbPonerTipoIncidente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerTipoIncidente.setText("-");
                ventanaMostrarIncidente.add(lbPonerTipoIncidente,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 570, -1));

                scrollInfoIncidente.setBackground(new java.awt.Color(0, 2, 2));

                txtMostrarInfoIncidente.setEditable(false);
                txtMostrarInfoIncidente.setBackground(new java.awt.Color(0, 2, 2));
                txtMostrarInfoIncidente.setColumns(1);
                txtMostrarInfoIncidente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                txtMostrarInfoIncidente.setForeground(new java.awt.Color(148, 161, 178));
                txtMostrarInfoIncidente.setLineWrap(true);
                txtMostrarInfoIncidente.setRows(3);
                txtMostrarInfoIncidente.setWrapStyleWord(true);
                txtMostrarInfoIncidente.setBorder(null);
                txtMostrarInfoIncidente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtMostrarInfoIncidente.setFocusable(false);
                scrollInfoIncidente.setViewportView(txtMostrarInfoIncidente);

                ventanaMostrarIncidente.add(scrollInfoIncidente,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 200, 140));

                imgPonerEvidenciaIncidente.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                ventanaMostrarIncidente.add(imgPonerEvidenciaIncidente,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 150, 140));

                lbPonerFechaIncidente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerFechaIncidente.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerFechaIncidente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerFechaIncidente.setText("-");
                ventanaMostrarIncidente.add(lbPonerFechaIncidente,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 200, -1));

                lbPonerHoraIncidente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerHoraIncidente.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerHoraIncidente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerHoraIncidente.setText("-");
                ventanaMostrarIncidente.add(lbPonerHoraIncidente,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 120, -1));

                lbPonerUbicacionIncidente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerUbicacionIncidente.setForeground(new java.awt.Color(255, 255, 254));
                lbPonerUbicacionIncidente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPonerUbicacionIncidente
                                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/marcadorGrande.png"))); // NOI18N
                lbPonerUbicacionIncidente.setText("-");
                ventanaMostrarIncidente.add(lbPonerUbicacionIncidente,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 570, -1));

                btnSalir.setBackground(new java.awt.Color(127, 90, 240));
                btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnSalir.setForeground(new java.awt.Color(255, 255, 254));
                btnSalir.setText("Salir");
                btnSalir.setActionCommand("Ingresar");
                btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnSalir.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSalirActionPerformed(evt);
                        }
                });
                ventanaMostrarIncidente.add(btnSalir,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, -1, -1));

                imgFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
                imgFondo.setText("jLabel1");
                ventanaMostrarIncidente.add(imgFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaMostrarIncidente,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 568,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ventanaMostrarIncidente,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                477,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
                Principal principal = new Principal();
                principal.setVisible(true);
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
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                                | javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(ViewIncident.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                }
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>

                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(() -> {
                        new ViewIncident().setVisible(true);
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnSalir;
        private javax.swing.JLabel imgFondo;
        private javax.swing.JLabel imgPonerEvidenciaIncidente;
        private javax.swing.JLabel lbPonerFechaIncidente;
        private javax.swing.JLabel lbPonerHoraIncidente;
        private javax.swing.JLabel lbPonerLikes;
        private javax.swing.JLabel lbPonerNombreUsuario;
        private javax.swing.JLabel lbPonerTipoIncidente;
        private javax.swing.JLabel lbPonerUbicacionIncidente;
        private javax.swing.JScrollPane scrollInfoIncidente;
        private javax.swing.JTextArea txtMostrarInfoIncidente;
        private javax.swing.JPanel ventanaMostrarIncidente;
        // End of variables declaration//GEN-END:variables
}
