/*
 cSpell:ignore Beleño Sahagún Dulcehelado
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Login;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Code.Conexion;
import Screens.Principal.Principal;
import Screens.Signup.Signup;

/**
 *
 * @author tutaa
 */
public class Login extends javax.swing.JFrame {
        public static int idUsuarioGuardar = 31;
        public static String correoGuardar = "cauntertut2004@gmail.com";

        /**
         * Creates new form Login
         */
        public Login() {
                initComponents();
                this.setTitle("Ingresar");
                this.setResizable(false);
                this.setLocationRelativeTo(null);
                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                ponerTextoEtiqueta(txtMostrarCopy, "©" + obtenerAño() + " EcoGuard . Todos los derechos reservados.");

                tfCorreo.setText(correoGuardar);
                desactivarBotonIngresar();
        }

        public void desactivarBotonIngresar() {
                char[] contraseñaEncriptada = pfContraseña.getPassword();
                String contraseña = new String(contraseñaEncriptada);
                btnIngresar.setEnabled((contraseña.length() > 8) && tfCorreo.getText().contains("@")
                                && tfCorreo.getText().length() > 13);

        }

        public Boolean usuarioEstaRegistrado(String correo, String contraseña) throws SQLException {
                var datosUsuarioRegistrado = Conexion.seleccionar(
                                String.format("SELECT * FROM usuarios WHERE correo = '%s' AND contraseña = '%s'",
                                                correo, contraseña),
                                new String[] { "id_usuario", "correo", "contraseña" });

                if (datosUsuarioRegistrado.size() > 0 && datosUsuarioRegistrado.size() < 4) {
                        idUsuarioGuardar = (Integer) datosUsuarioRegistrado.get(0);
                        return true;
                }
                return false;
        }

        public void ingresarUsuario() throws SQLException {
                String correo = tfCorreo.getText().toLowerCase();
                char[] contraseñaEncriptada = pfContraseña.getPassword();
                String contraseña = new String(contraseñaEncriptada);

                if (usuarioEstaRegistrado(correo, contraseña)) {
                        correoGuardar = correo;
                        Principal pantallaPrincipal = new Principal();
                        pantallaPrincipal.setVisible(true);
                        this.setVisible(false);
                } else {
                        JOptionPane.showMessageDialog(this, "CORREO O CONTRASEÑA NO VALIDOS \n", "AVISO!",
                                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        pfContraseña.setText("");
                        pfContraseña.requestFocus();
                }
        }

        public static String obtenerAño() {
                return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }

        public static void ponerTextoEtiqueta(JLabel label, String texto) {
                label.setText(texto);
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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaLogIn = new javax.swing.JPanel();
                lbTitulo = new javax.swing.JLabel();
                btnErroresComunes = new javax.swing.JButton();
                lbCorreo = new javax.swing.JLabel();
                tfCorreo = new javax.swing.JTextField();
                lbContraseña = new javax.swing.JLabel();
                pfContraseña = new javax.swing.JPasswordField();
                btnRegistrarse = new javax.swing.JButton();
                btnIngresar = new javax.swing.JButton();
                txtMostrarCopy = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaLogIn.setBackground(new java.awt.Color(22, 22, 26));
                ventanaLogIn.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaLogIn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbTitulo.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbTitulo.setForeground(new java.awt.Color(255, 255, 254));
                lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPequeño.png"))); // NOI18N
                lbTitulo.setText("EcoGuard");
                ventanaLogIn.add(lbTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1080, -1));

                btnErroresComunes.setBackground(new java.awt.Color(127, 90, 240));
                btnErroresComunes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                btnErroresComunes.setForeground(new java.awt.Color(255, 255, 254));
                btnErroresComunes.setText("?");
                btnErroresComunes.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnErroresComunesActionPerformed(evt);
                        }
                });
                ventanaLogIn.add(btnErroresComunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

                lbCorreo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbCorreo.setForeground(new java.awt.Color(255, 255, 254));
                lbCorreo.setText("CORREO: ");
                ventanaLogIn.add(lbCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, -1, -1));

                tfCorreo.setBackground(new java.awt.Color(36, 38, 41));
                tfCorreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfCorreo.setForeground(new java.awt.Color(148, 161, 178));
                tfCorreo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                tfCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                tfCorreoKeyPressed(evt);
                        }

                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfCorreoKeyReleased(evt);
                        }
                });
                ventanaLogIn.add(tfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, 310, 30));

                lbContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbContraseña.setForeground(new java.awt.Color(255, 255, 254));
                lbContraseña.setText("CONTRASEÑA: ");
                ventanaLogIn.add(lbContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, -1, -1));

                pfContraseña.setBackground(new java.awt.Color(36, 38, 41));
                pfContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                pfContraseña.setForeground(new java.awt.Color(148, 161, 178));
                pfContraseña.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                pfContraseña.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                pfContraseñaActionPerformed(evt);
                        }
                });
                pfContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                pfContraseñaKeyReleased(evt);
                        }
                });
                ventanaLogIn.add(pfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, 310, 30));

                btnRegistrarse.setBackground(new java.awt.Color(127, 90, 240));
                btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnRegistrarse.setForeground(new java.awt.Color(255, 255, 254));
                btnRegistrarse.setText("Registrarse");
                btnRegistrarse.setActionCommand("Ingresar");
                btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRegistrarseActionPerformed(evt);
                        }
                });
                ventanaLogIn.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, -1, -1));

                btnIngresar.setBackground(new java.awt.Color(127, 90, 240));
                btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnIngresar.setForeground(new java.awt.Color(255, 255, 254));
                btnIngresar.setText("Ingresar");
                btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnIngresar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        btnIngresarActionPerformed(evt);
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                });
                ventanaLogIn.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 520, -1, -1));

                txtMostrarCopy.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                txtMostrarCopy.setForeground(new java.awt.Color(148, 161, 178));
                txtMostrarCopy.setText("©");
                ventanaLogIn.add(txtMostrarCopy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, -1, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaLogIn, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaLogIn, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void pfContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonIngresar();
        }

        private void pfContraseñaActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {
                Signup pantallaRegistro = new Signup();
                pantallaRegistro.setVisible(true);
                this.setVisible(false);
        }

        private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
                ingresarUsuario();

        }

        private void tfCorreoKeyPressed(java.awt.event.KeyEvent evt) {

        }

        private void tfCorreoKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonIngresar();
        }

        private void btnErroresComunesActionPerformed(java.awt.event.ActionEvent evt) {
                var texto = "¡Hola! Aquí te dejo un apartado de ayuda con algunos errores comunes y"
                                + " sus\nposibles soluciones:\n\nNo puedes ingresar:\nVerifica tus "
                                + "credenciales de inicio de sesión.\nRestablece tu contraseña si "
                                + "la has olvidado.\nVerifica tu conexión a internet.\nSi el "
                                + "problema persiste, contacta al soporte técnico de la plataforma."
                                + "\n\nNo puedes registrarte:\nVerifica que completaste todos los "
                                + "campos requeridos y que usaste una\ndirección de correo "
                                + "institucional válida.\nSi el problema persiste, intenta utilizar"
                                + " una dirección de correo electrónico\ndiferente o contacta al "
                                + "soporte técnico de la plataforma.\n\nError al actualizar "
                                + "biografía:\nAsegúrate de seguir los requisitos de longitud y "
                                + "formato para la biografía.\nSi el problema persiste, intenta "
                                + "actualizar tu biografía desde otro\ndispositivo o navegador o "
                                + "contacta al soporte técnico de la plataforma.\n\nError al"
                                + " agendar tutoría:\nVerifica que seleccionaste la fecha y hora "
                                + "correctas.\nVerifica que tienes los permisos necesarios para"
                                + " agendar una tutoría.\nSi el problema persiste, intenta utilizar"
                                + " otro dispositivo o navegador o\ncontacta al soporte técnico de"
                                + " la plataforma.\n\nError al actualizar lista de tareas:"
                                + "\nAsegúrate de seguir los requisitos de longitud y formato para"
                                + " cada tarea en\nla lista.\nVerifica que tienes los permisos "
                                + "necesarios para actualizar la lista de\ntareas en la plataforma."
                                + "\n\nSi necesitas ayuda adicional, por favor envía un correo "
                                + "especificando tu problema a alguno\nde los siguientes correos de"
                                + " contacto:\n\nJeffry Steve Sahagún Beleño:"
                                + " jefrysahagun.sb@academia.umb.edu.co\nMiguel Ángel Bejarano Muñoz:"
                                + " miguelbejarano.am@academia.umb.edu.co";

                JOptionPane.showMessageDialog(null, texto, "AYUDA", JOptionPane.INFORMATION_MESSAGE);
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
                        java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                }
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(() -> {
                        new Login().setVisible(true);
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnErroresComunes;
        private javax.swing.JButton btnIngresar;
        private javax.swing.JButton btnRegistrarse;
        private javax.swing.JLabel lbContraseña;
        private javax.swing.JLabel lbCorreo;
        private javax.swing.JLabel lbTitulo;
        private javax.swing.JPasswordField pfContraseña;
        private javax.swing.JTextField tfCorreo;
        private javax.swing.JLabel txtMostrarCopy;
        private javax.swing.JPanel ventanaLogIn;
        // End of variables declaration//GEN-END:variables
}
