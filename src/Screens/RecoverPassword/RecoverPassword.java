/*
	cSpell:ignore desencriptar operacion verificacion
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.RecoverPassword;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Code.OperacionCRUD;
import Code.Desencriptar;
import Code.EnviarCodigoVerificacion;
import Screens.Custom.CambiarIU;
import Screens.Custom.ObtenerIU;
import Screens.Login.Login;

/**
 *
 * @author tutaa
 */
public class RecoverPassword extends javax.swing.JFrame {
        private boolean correoVerificado = false;
        private EnviarCodigoVerificacion enviarCodigo;
        /**
         * Creates new form RecoverPassword
         */
        public RecoverPassword() {
                initComponents();

                this.setTitle("Recuperar Contraseña");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                desactivarBotonRegistrarse();
                desactivarBotonEnviarCodigo();
                desactivarBotonVerificarCodigo();
                desactivarCamposContraseña();
                mostrarErrores();
        }

        private void desactivarBotonRegistrarse() {
                btnRegistrarse.setEnabled((correoVerificado && (ObtenerIU.obtenerTextoCampo(tfCorreo).contains("@"))
                                && (Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña))
                                                .length() >= 8)
                                && (Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfConfirmarContraseña))
                                                .length() >= 8)
                                && (ObtenerIU.obtenerTextoCampo(tfCorreo).length() >= 13)
                                && (Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña))
                                                .equals(Desencriptar.desencriptarContra(
                                                                ObtenerIU.obtenerContraseña(pfConfirmarContraseña))))));
        }

        private void desactivarBotonEnviarCodigo() {
                btnEnviarCodigo.setEnabled(
                                ObtenerIU.obtenerTextoCampo(tfCorreo).contains("@")
                                                && ObtenerIU.obtenerTextoCampo(tfCorreo).length() >= 13);
        }

        private void desactivarBotonVerificarCodigo() {
                btnVerificarCodigo.setEnabled(ObtenerIU.obtenerTextoCampo(tfRecibirCodigo).length() == 6
                                && ObtenerIU.obtenerTextoCampo(tfCorreo).contains("@")
                                && ObtenerIU.obtenerTextoCampo(tfCorreo).length() >= 13);
        }

        private void activarCamposContraseña() {
                pfContraseña.setEnabled(true);
                pfConfirmarContraseña.setEnabled(true);
        }

        private void desactivarCamposContraseña() {
                pfContraseña.setEnabled(false);
                pfConfirmarContraseña.setEnabled(false);
        }

        private void verificarCodigo() {

                if (ObtenerIU.obtenerTextoCampo(tfRecibirCodigo).equals(enviarCodigo.getCodigo()) && enviarCodigo.getIntentos() > 0) {
                        JOptionPane.showMessageDialog(null, "EL CÓDIGO ES CORRECTO.");
                        pfContraseña.setEnabled(true);
                        pfConfirmarContraseña.setEnabled(true);
                        enviarCodigo.setIntentos(3);
                        correoVerificado = true;
                        activarCamposContraseña();
                        btnEnviarCodigo.setEnabled(false);
                        btnVerificarCodigo.setEnabled(false);
                } else if (enviarCodigo.getIntentos() == 0) {
                        JOptionPane.showMessageDialog(rootPane, "NO TIENE MÁS INTENTOS.");
                        tfRecibirCodigo.setEnabled(false);
                        tfCorreo.setEnabled(false);
                        btnEnviarCodigo.setEnabled(false);
                        btnVerificarCodigo.setEnabled(false);
                } else {
                        enviarCodigo.setIntentos(enviarCodigo.getIntentos()-1);
                        JOptionPane.showMessageDialog(null,
                                        "EL CÓDIGO NO ES CORRECTO.\nTIENE " + enviarCodigo.getIntentos() + " INTENTOS.");
                }
        }

        private boolean correoEstaRegistrado(String correo) throws SQLException {
                ArrayList<ArrayList<Object>> datosUsuarioRegistrado = OperacionCRUD.seleccionar(
                                String.format("SELECT * FROM usuarios WHERE correo = '%s'",
                                                correo),
                                new String[] { "correo" });

                return datosUsuarioRegistrado.size() == 1;
        }

        private void enviarCodigo() throws HeadlessException, SQLException {
                String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();

                if (correoEstaRegistrado(correo)) {
                        String listaCodigo[] = enviarCodigo.getCodigo().split(""), text = "";

                        for (int i = 0; i < listaCodigo.length; i++) {
                                if (i != listaCodigo.length - 1) {
                                        text += listaCodigo[i] + "-";
                                } else {
                                        text += listaCodigo[i];
                                }
                        }

                        String asunto = "Restablecer tu contraseña en EcoGuard.";
                        String mensaje = "&#x1F44B; Hola, " + obtenerNombre(ObtenerIU.obtenerTextoCampo(tfCorreo))
                                        + ".<br><br>"
                                        + "Has recibido este correo electrónico porque has solicitado restablecer tu contraseña en EcoGuard. Para continuar, utiliza el siguiente código de verificación:<br><br>"
                                        + "&#128273; <strong style=\"font-size: 24px;\">" + text + "</strong><br><br>"
                                        + "Por favor, ingresa este código en la página de restablecimiento de contraseña y sigue las instrucciones para crear una nueva contraseña segura.<br><br>"
                                        + "Si no has solicitado el restablecimiento de tu contraseña, por favor ignora este correo electrónico y asegúrate de proteger tu cuenta.<br><br>"
                                        + "Si tienes alguna pregunta o necesitas ayuda, no dudes en contactar a nuestro equipo de soporte. &#128516;<br><br>"
                                        + "¡Que tengas un excelente día! &#128077;<br><br>"
                                        + "Atentamente,<br>"
                                        + "El equipo de EcoGuard. &#128170;";

                        enviarCodigo = new EnviarCodigoVerificacion(correo, asunto, mensaje);
                } else {
                        JOptionPane.showMessageDialog(null, "NO EXISTE UNA CUENTA CON ESTE CORREO.");
                }
        }

        private String obtenerNombre(String correo) throws SQLException {

                return (String) (OperacionCRUD.seleccionar(
                                String.format("SELECT nombre FROM usuarios WHERE correo = '%s'", correo),
                                new String[] { "nombre" })).get(0).get(0);

        }

        private void actualizarContraseña() throws SQLException {
                String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();
                String contraseña = Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfConfirmarContraseña));
                OperacionCRUD.registrar(
                                String.format("UPDATE usuarios SET contraseña = '%s' WHERE correo = '%s'",
                                                contraseña, correo));
                JOptionPane.showMessageDialog(this, "¡CONTRASEÑA ACTUALIZADA!", "¡AVISO!",
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

        private void mostrarErrores() {

                // pfContraseña
                if (Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña)).length() < 8) {
                        CambiarIU.setImageLabel(lbErrorContraseña,
                                        "src/img/error.png");
                        lbErrorContraseña.setToolTipText("La contraseña debe tener mínimo 8 caracteres.");
                } else {
                        CambiarIU.setImageLabel(lbErrorContraseña,
                                        "src/img/check.png");
                        lbErrorContraseña.setToolTipText("La contraseña tiene mínimo 8 caracteres.");
                }

                // pfConfContraseña
                if ((!Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfConfirmarContraseña))
                                .equals(Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña))))
                                || Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfConfirmarContraseña))
                                                .equals("")) {
                        CambiarIU.setImageLabel(lbErrorConfContraseña,
                                        "src/img/error.png");
                        lbErrorConfContraseña.setToolTipText("Las contraseñas deben ser iguales.");
                } else {
                        CambiarIU.setImageLabel(lbErrorConfContraseña,
                                        "src/img/check.png");
                        lbErrorConfContraseña.setToolTipText("Las contraseñas son iguales.");
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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaSignup = new javax.swing.JPanel();
                lbErrorContraseña = new javax.swing.JLabel();
                lbErrorConfContraseña = new javax.swing.JLabel();
                lbRegistro = new javax.swing.JLabel();
                lbCorreo = new javax.swing.JLabel();
                tfCorreo = new javax.swing.JTextField();
                btnEnviarCodigo = new javax.swing.JButton();
                tfRecibirCodigo = new javax.swing.JTextField();
                btnVerificarCodigo = new javax.swing.JButton();
                lbConfContraseña = new javax.swing.JLabel();
                pfConfirmarContraseña = new javax.swing.JPasswordField();
                lbNuevaContraseña = new javax.swing.JLabel();
                pfContraseña = new javax.swing.JPasswordField();
                btnRegistrarse = new javax.swing.JButton();
                btnRegresar = new javax.swing.JButton();
                imgFondo = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaSignup.setBackground(new java.awt.Color(22, 22, 26));
                ventanaSignup.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaSignup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                ventanaSignup.add(lbErrorContraseña,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, 25, 25));
                ventanaSignup.add(lbErrorConfContraseña,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, 25, 25));

                lbRegistro.setFont(new java.awt.Font("Crabs", 1, 70)); // NOI18N
                lbRegistro.setForeground(new java.awt.Color(255, 255, 254));
                lbRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbRegistro.setText("Recuperar Contraseña");
                ventanaSignup.add(lbRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 760, -1));

                lbCorreo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbCorreo.setForeground(new java.awt.Color(255, 255, 254));
                lbCorreo.setText("CORREO ELECTRÓNICO: ");
                ventanaSignup.add(lbCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

                tfCorreo.setBackground(new java.awt.Color(36, 38, 41));
                tfCorreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfCorreo.setForeground(new java.awt.Color(148, 161, 178));
                tfCorreo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                tfCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfCorreoKeyReleased(evt);
                        }
                });
                ventanaSignup.add(tfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 310, 30));

                btnEnviarCodigo.setBackground(new java.awt.Color(127, 90, 240));
                btnEnviarCodigo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnEnviarCodigo.setForeground(new java.awt.Color(255, 255, 254));
                btnEnviarCodigo.setText("Enviar Código");
                btnEnviarCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnEnviarCodigo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        btnEnviarCodigoActionPerformed(evt);
                                } catch (HeadlessException | SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                });
                ventanaSignup.add(btnEnviarCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

                tfRecibirCodigo.setBackground(new java.awt.Color(36, 38, 41));
                tfRecibirCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfRecibirCodigo.setForeground(new java.awt.Color(148, 161, 178));
                tfRecibirCodigo.setBorder(
                                new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                tfRecibirCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfRecibirCodigoKeyReleased(evt);
                        }
                });
                ventanaSignup.add(tfRecibirCodigo,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 180, 30));

                btnVerificarCodigo.setBackground(new java.awt.Color(127, 90, 240));
                btnVerificarCodigo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnVerificarCodigo.setForeground(new java.awt.Color(255, 255, 254));
                btnVerificarCodigo.setText("Verificar");
                btnVerificarCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnVerificarCodigo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnVerificarCodigoActionPerformed(evt);
                        }
                });
                ventanaSignup.add(btnVerificarCodigo,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, -1, -1));

                lbConfContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbConfContraseña.setForeground(new java.awt.Color(255, 255, 254));
                lbConfContraseña.setText("CONFIRMAR CONTRASEÑA: ");
                ventanaSignup.add(lbConfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

                pfConfirmarContraseña.setBackground(new java.awt.Color(36, 38, 41));
                pfConfirmarContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                pfConfirmarContraseña.setForeground(new java.awt.Color(148, 161, 178));
                pfConfirmarContraseña.setBorder(
                                new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                pfConfirmarContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                pfConfirmarContraseñaKeyReleased(evt);
                        }
                });
                ventanaSignup.add(pfConfirmarContraseña,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 310, 30));

                lbNuevaContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbNuevaContraseña.setForeground(new java.awt.Color(255, 255, 254));
                lbNuevaContraseña.setText("NUEVA CONTRASEÑA: ");
                ventanaSignup.add(lbNuevaContraseña,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

                pfContraseña.setBackground(new java.awt.Color(36, 38, 41));
                pfContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                pfContraseña.setForeground(new java.awt.Color(148, 161, 178));
                pfContraseña.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                pfContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                pfContraseñaKeyReleased(evt);
                        }
                });
                ventanaSignup.add(pfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 310, 30));

                btnRegistrarse.setBackground(new java.awt.Color(127, 90, 240));
                btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnRegistrarse.setForeground(new java.awt.Color(255, 255, 254));
                btnRegistrarse.setText("Confirmar");
                btnRegistrarse.setActionCommand("Ingresar");
                btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        btnRegistrarseActionPerformed(evt);
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                });
                ventanaSignup.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 550, -1, -1));

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
                ventanaSignup.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 550, -1, -1));

                imgFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
                imgFondo.setText("jLabel1");
                ventanaSignup.add(imgFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 630));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaSignup, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                758, javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaSignup, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                625, javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void pfContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
                mostrarErrores();
        }

        private void pfConfirmarContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
                mostrarErrores();
        }

        private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
                actualizarContraseña();

                Login.correoGuardar = ObtenerIU.obtenerTextoCampo(tfCorreo);
                Login login = new Login();
                login.setVisible(true);
                this.setVisible(false);
        }

        private void btnVerificarCodigoActionPerformed(java.awt.event.ActionEvent evt) {
                verificarCodigo();
        }

        private void tfRecibirCodigoKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonVerificarCodigo();
                mostrarErrores();
        }

        private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {
                Login login = new Login();
                login.setVisible(true);
                this.setVisible(false);

        }

        private void tfCorreoKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
                desactivarBotonEnviarCodigo();
                desactivarBotonVerificarCodigo();
                mostrarErrores();
        }

        private void btnEnviarCodigoActionPerformed(java.awt.event.ActionEvent evt)
                        throws HeadlessException, SQLException {
                enviarCodigo();
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
                        java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                }
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
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
                        new RecoverPassword().setVisible(true);
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnEnviarCodigo;
        private javax.swing.JButton btnRegistrarse;
        private javax.swing.JButton btnRegresar;
        private javax.swing.JButton btnVerificarCodigo;
        private javax.swing.JLabel imgFondo;
        private javax.swing.JLabel lbConfContraseña;
        private javax.swing.JLabel lbCorreo;
        private javax.swing.JLabel lbErrorConfContraseña;
        private javax.swing.JLabel lbErrorContraseña;
        private javax.swing.JLabel lbNuevaContraseña;
        private javax.swing.JLabel lbRegistro;
        private javax.swing.JPasswordField pfConfirmarContraseña;
        private javax.swing.JPasswordField pfContraseña;
        private javax.swing.JTextField tfCorreo;
        private javax.swing.JTextField tfRecibirCodigo;
        private javax.swing.JPanel ventanaSignup;
        // End of variables declaration//GEN-END:variables
}
