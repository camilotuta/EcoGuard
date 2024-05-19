/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal.ReportIncident;

import java.awt.Toolkit;
import java.util.Calendar;

/**
 *
 * @author tutaa
 */
public class ReportIncident extends javax.swing.JFrame {

        /**
         * Creates new form ReportIncident
         */
        public ReportIncident() {
                initComponents();

                this.setTitle("Ingresar");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));

        }

        public static String obtenerAño() {
                return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaReportarIncidente = new javax.swing.JPanel();
                lbReportar = new javax.swing.JLabel();
                lbTipo = new javax.swing.JLabel();
                comboTipoIncidente = new javax.swing.JComboBox<>();
                lbDepartamento = new javax.swing.JLabel();
                comboDepartamento = new javax.swing.JComboBox<>();
                lbCiudad = new javax.swing.JLabel();
                comboCiudad = new javax.swing.JComboBox<>();
                lbInformacion = new javax.swing.JLabel();
                scrollInformacion = new Screens.Principal.Custom.ScrollPaneWin11();
                tfInformacion = new javax.swing.JTextPane();
                lbEvidencia = new javax.swing.JLabel();
                tfRutaImagen = new javax.swing.JTextField();
                btnSubirImagen = new javax.swing.JButton();
                mostrarImgEvidencia = new javax.swing.JLabel();
                lbPreview = new javax.swing.JLabel();
                btnPublicar = new javax.swing.JButton();
                btnRegresar = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaReportarIncidente.setBackground(new java.awt.Color(22, 22, 26));
                ventanaReportarIncidente.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaReportarIncidente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbReportar.setFont(new java.awt.Font("Crabs", 1, 90)); // NOI18N
                lbReportar.setForeground(new java.awt.Color(255, 255, 254));
                lbReportar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbReportar.setText("Reportar Incidente");
                ventanaReportarIncidente.add(lbReportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 860, -1));

                lbTipo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbTipo.setForeground(new java.awt.Color(255, 255, 254));
                lbTipo.setText("TIPO:");
                ventanaReportarIncidente.add(lbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

                comboTipoIncidente.setBackground(new java.awt.Color(36, 38, 41));
                comboTipoIncidente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                comboTipoIncidente.setForeground(new java.awt.Color(255, 255, 254));
                comboTipoIncidente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                comboTipoIncidente.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                comboTipoIncidenteActionPerformed(evt);
                        }
                });
                ventanaReportarIncidente.add(comboTipoIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 300, 30));

                lbDepartamento.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbDepartamento.setForeground(new java.awt.Color(255, 255, 254));
                lbDepartamento.setText("DEPARTAMENTO:");
                ventanaReportarIncidente.add(lbDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

                comboDepartamento.setBackground(new java.awt.Color(36, 38, 41));
                comboDepartamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                comboDepartamento.setForeground(new java.awt.Color(255, 255, 254));
                comboDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                ventanaReportarIncidente.add(comboDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 300, 30));

                lbCiudad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbCiudad.setForeground(new java.awt.Color(255, 255, 254));
                lbCiudad.setText("CIUDAD:");
                ventanaReportarIncidente.add(lbCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, -1, -1));

                comboCiudad.setBackground(new java.awt.Color(36, 38, 41));
                comboCiudad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                comboCiudad.setForeground(new java.awt.Color(255, 255, 254));
                comboCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                ventanaReportarIncidente.add(comboCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 300, 30));

                lbInformacion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbInformacion.setForeground(new java.awt.Color(255, 255, 254));
                lbInformacion.setText("INFORMACIÓN:");
                ventanaReportarIncidente.add(lbInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, -1, -1));

                tfInformacion.setBackground(new java.awt.Color(22, 22, 26));
                tfInformacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfInformacion.setForeground(new java.awt.Color(148, 161, 178));
                tfInformacion.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfInformacionKeyReleased(evt);
                        }
                });
                scrollInformacion.setViewportView(tfInformacion);

                ventanaReportarIncidente.add(scrollInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 420, 80));

                lbEvidencia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbEvidencia.setForeground(new java.awt.Color(255, 255, 254));
                lbEvidencia.setText("EVIDENCIA:");
                ventanaReportarIncidente.add(lbEvidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, -1, -1));

                tfRutaImagen.setBackground(new java.awt.Color(36, 38, 41));
                tfRutaImagen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfRutaImagen.setForeground(new java.awt.Color(148, 161, 178));
                tfRutaImagen.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                tfRutaImagen.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                tfRutaImagenKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfRutaImagenKeyReleased(evt);
                        }
                });
                ventanaReportarIncidente.add(tfRutaImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 250, 30));

                btnSubirImagen.setBackground(new java.awt.Color(127, 90, 240));
                btnSubirImagen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnSubirImagen.setForeground(new java.awt.Color(255, 255, 254));
                btnSubirImagen.setText("Subir Imagen");
                btnSubirImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnSubirImagen.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSubirImagenActionPerformed(evt);
                        }
                });
                ventanaReportarIncidente.add(btnSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, -1, -1));

                mostrarImgEvidencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 85, 227)));
                ventanaReportarIncidente.add(mostrarImgEvidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 510, 120, 100));

                lbPreview.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbPreview.setForeground(new java.awt.Color(255, 255, 254));
                lbPreview.setText("Preview");
                ventanaReportarIncidente.add(lbPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 540, -1, -1));

                btnPublicar.setBackground(new java.awt.Color(127, 90, 240));
                btnPublicar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnPublicar.setForeground(new java.awt.Color(255, 255, 254));
                btnPublicar.setText("Publicar");
                btnPublicar.setActionCommand("Ingresar");
                btnPublicar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnPublicar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnPublicarActionPerformed(evt);
                        }
                });
                ventanaReportarIncidente.add(btnPublicar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 650, -1, -1));

                btnRegresar.setBackground(new java.awt.Color(127, 90, 240));
                btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnRegresar.setForeground(new java.awt.Color(255, 255, 254));
                btnRegresar.setText("Regresar");
                btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnRegresar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRegresarActionPerformed(evt);
                        }
                });
                ventanaReportarIncidente.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 650, -1, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ventanaReportarIncidente, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ventanaReportarIncidente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnSubirImagenActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void tfRutaImagenKeyPressed(java.awt.event.KeyEvent evt) {

        }

        private void tfRutaImagenKeyReleased(java.awt.event.KeyEvent evt) {

        }

        private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void comboTipoIncidenteActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void btnPublicarActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void tfInformacionKeyReleased(java.awt.event.KeyEvent evt) {

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
                        java.util.logging.Logger.getLogger(ReportIncident.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(ReportIncident.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(ReportIncident.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(ReportIncident.class.getName())
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
                        new ReportIncident().setVisible(true);
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnPublicar;
        private javax.swing.JButton btnRegresar;
        private javax.swing.JButton btnSubirImagen;
        private javax.swing.JComboBox<String> comboCiudad;
        private javax.swing.JComboBox<String> comboDepartamento;
        private javax.swing.JComboBox<String> comboTipoIncidente;
        private javax.swing.JLabel lbCiudad;
        private javax.swing.JLabel lbDepartamento;
        private javax.swing.JLabel lbEvidencia;
        private javax.swing.JLabel lbInformacion;
        private javax.swing.JLabel lbPreview;
        private javax.swing.JLabel lbReportar;
        private javax.swing.JLabel lbTipo;
        private javax.swing.JLabel mostrarImgEvidencia;
        private javax.swing.JScrollPane scrollInformacion;
        private javax.swing.JTextPane tfInformacion;
        private javax.swing.JTextField tfRutaImagen;
        private javax.swing.JPanel ventanaReportarIncidente;
        // End of variables declaration//GEN-END:variables
}
