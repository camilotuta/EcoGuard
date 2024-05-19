/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Signup;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

import Code.Conexion;
import Code.EnviarCorreo;
import Code.Ubicaciones;
import Screens.Login.Login;
import Screens.Principal.Principal;

/**
 *
 * @author tutaa
 */
public class Signup extends javax.swing.JFrame {
        Random random = new Random();
        private Boolean correoVerificado = false;
        private String codigo = String.valueOf(random.nextInt(100_000, 999_999));
        private Integer intentos = 3;
        Ubicaciones ubicaciones = new Ubicaciones();
        Map<String, List<String>> departamentos = ubicaciones.departamentos;

        /**
         * Creates new form Signup
         */
        public Signup() {
                initComponents();

                this.setTitle("Registrarse");
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setIconImage(Toolkit.getDefaultToolkit()
                                .getImage(getClass().getResource("/img/icon.png")));
                desactivarBotonRegistrarse();
                desactivarBotonEnviarCodigo();
                desactivarBotonVerificarCodigo();
                desactivarComboCiudad();
                desactivarCamposContraseña();
                ponerDepartamentos();
                ponerEdades();

        }

        @SuppressWarnings("deprecation")
        public void desactivarBotonRegistrarse() {
                btnRegistrarse.setEnabled((correoVerificado && (tfNombre.getText().length() >= 10)
                                && (!comboEdad.getSelectedItem().equals("Seleccionar"))
                                && (!comboDepartamento.getSelectedItem().equals("Seleccionar"))
                                && (!comboCiudad.getSelectedItem().equals("Seleccionar"))
                                && (tfCorreo.getText().contains("@")) && (pfContraseña.getText().length() >= 8)
                                && (pfConfirmarContraseña.getText().length() >= 8)
                                && (tfCorreo.getText().length() >= 13)
                                && (pfContraseña.getText().equals(pfConfirmarContraseña.getText()))));
        }

        public void desactivarBotonEnviarCodigo() {
                btnEnviarCodigo.setEnabled(
                                tfNombre.getText().length() > 8 && tfCorreo.getText().contains("@") && tfCorreo
                                                .getText().length() >= 13);
        }

        public void desactivarBotonVerificarCodigo() {
                if (tfRecibirCodigo.getText().length() == 6
                                && tfCorreo.getText().contains("@") && tfCorreo
                                                .getText().length() >= 13) {
                        btnVerificarCodigo.setEnabled(true);
                }
        }

        public void activarCamposContraseña() {
                pfContraseña.setEnabled(true);
                pfConfirmarContraseña.setEnabled(true);
        }

        public void desactivarCamposContraseña() {
                pfContraseña.setEnabled(false);
                pfConfirmarContraseña.setEnabled(false);
        }

        public void desactivarComboCiudad() {
                comboCiudad.setEnabled(!comboDepartamento.getSelectedItem().equals("Seleccionar"));
        }

        public void verificarCodigo() {

                if (tfRecibirCodigo.getText().equals(codigo) && intentos > 0) {
                        JOptionPane.showMessageDialog(null, "EL CÓDIGO ES CORRECTO.");
                        pfContraseña.setEnabled(true);
                        pfConfirmarContraseña.setEnabled(true);
                        intentos = 3;
                        correoVerificado = true;
                        activarCamposContraseña();
                        btnEnviarCodigo.setEnabled(false);
                        btnVerificarCodigo.setEnabled(false);
                } else if (intentos == 0) {
                        JOptionPane.showMessageDialog(rootPane, "NO TIENE MÁS INTENTOS.");
                        tfRecibirCodigo.setEnabled(false);
                        tfCorreo.setEnabled(false);
                        btnEnviarCodigo.setEnabled(false);
                        btnVerificarCodigo.setEnabled(false);
                } else {
                        intentos--;
                        JOptionPane.showMessageDialog(null,
                                        "EL CÓDIGO NO ES CORRECTO.\nTIENE " + intentos + " INTENTOS.");
                }

        }

        public Boolean usuarioEstaRegistrado(String correo) throws SQLException {
                var datosUsuarioRegistrado = Conexion.seleccionar(
                                String.format("SELECT * FROM usuarios WHERE correo = '%s'",
                                                correo),
                                new String[] { "correo" });

                if (datosUsuarioRegistrado.size() == 1) {
                        return true;
                }
                return false;
        }

        public void enviarCodigo() throws HeadlessException, SQLException {
                String correo = tfCorreo.getText().toLowerCase();

                String nombre = tfNombre.getText();

                if (!usuarioEstaRegistrado(correo)) {

                        Random rand = new Random();
                        codigo = String.valueOf(rand.nextInt(100_000, 999_999));

                        String listaCodigo[] = codigo.split(""), text = "";

                        for (int i = 0; i < listaCodigo.length; i++) {
                                if (i != listaCodigo.length - 1) {
                                        text += listaCodigo[i] + "-";
                                } else {
                                        text += listaCodigo[i];
                                }
                        }

                        String asunto = "Verificación de correo electrónico en EcoGuard";
                        String mensaje = "&#x1F44B; Hola, " + nombre + ".<br><br>"
                                        + "¡Bienvenido/a a EcoGuard! Antes de que puedas comenzar a utilizar tu cuenta, necesitamos verificar que tu correo electrónico sea válido. "
                                        + "Para ello, utiliza el siguiente código de verificación:<br><br>"
                                        + "&#128273; <strong style=\"font-size: 24px;\">" + text + "</strong><br><br>"
                                        + "Por favor, ingresa este código en la página de verificación de correo electrónico y sigue las instrucciones para verificar tu cuenta.<br><br>"
                                        + "Si no has creado una cuenta en EcoGuard, por favor ignora este correo electrónico y asegúrate de proteger tu cuenta.<br><br>"
                                        + "Si tienes alguna pregunta o necesitas ayuda, no dudes en contactar a nuestro equipo de soporte. &#128516;<br><br>"
                                        + "¡Que tengas un excelente día! &#128077;<br><br>"
                                        + "Atentamente,<br>"
                                        + "El equipo de EcoGuard. &#128170;";

                        new EnviarCorreo(correo, asunto, mensaje);
                } else {
                        JOptionPane.showMessageDialog(null, "YA EXISTE UNA CUENTA CON ESTE CORREO.");
                }
        }

        public void ponerDepartamentos() {
                for (String departamento : departamentos.keySet()) {
                        comboDepartamento.addItem(departamento);
                }
        }

        public void ponerEdades() {
                for (int i = 13; i < 101; i++) {
                        comboEdad.addItem(String.valueOf(i));
                }
        }

        public void ponerCiudades() {
                comboCiudad.removeAllItems();
                comboCiudad.addItem("Seleccionar");
                List<String> ciudades = departamentos.get(comboDepartamento.getSelectedItem());
                for (String i : ciudades) {
                        comboCiudad.addItem(i);
                }
        }

        @SuppressWarnings("deprecation")
        public void registrarUsuario() throws SQLException {
                String correo = tfCorreo.getText().toLowerCase();
                String contraseña = pfContraseña.getText();
                String nombre = tfNombre.getText();
                String departamento = (String) comboDepartamento.getSelectedItem();
                String ciudad = (String) comboCiudad.getSelectedItem();
                int edad = Integer.parseInt((String) comboEdad.getSelectedItem());
                Conexion.registrar(
                                String.format("INSERT INTO usuarios (correo,contraseña,nombre,departamento,ciudad,edad,"
                                                + "fecha_registro,biografia ) VALUES ('%s','%s','%s','%s','%s',%d,DATE ('now'),' ');",
                                                correo, contraseña, nombre, departamento, ciudad, edad));
                JOptionPane.showMessageDialog(this, "¡REGISTRO EXITOSO!", "¡AVISO!",
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
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

                ventanaSignup = new javax.swing.JPanel();
                lbRegistro = new javax.swing.JLabel();
                lbNombre = new javax.swing.JLabel();
                tfNombre = new javax.swing.JTextField();
                lbEdad = new javax.swing.JLabel();
                comboEdad = new javax.swing.JComboBox<>();
                lbDepartamento = new javax.swing.JLabel();
                comboDepartamento = new javax.swing.JComboBox<>();
                lbCiudad = new javax.swing.JLabel();
                comboCiudad = new javax.swing.JComboBox<>();
                lbCorreo = new javax.swing.JLabel();
                tfCorreo = new javax.swing.JTextField();
                btnEnviarCodigo = new javax.swing.JButton();
                tfRecibirCodigo = new javax.swing.JTextField();
                btnVerificarCodigo = new javax.swing.JButton();
                lbConfContraseña = new javax.swing.JLabel();
                pfConfirmarContraseña = new javax.swing.JPasswordField();
                lbContraseña = new javax.swing.JLabel();
                pfContraseña = new javax.swing.JPasswordField();
                btnRegistrarse = new javax.swing.JButton();
                btnRegresar = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaSignup.setBackground(new java.awt.Color(22, 22, 26));
                ventanaSignup.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaSignup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbRegistro.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbRegistro.setForeground(new java.awt.Color(255, 255, 254));
                lbRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbRegistro.setText("Registro");
                ventanaSignup.add(lbRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, -1));

                lbNombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbNombre.setForeground(new java.awt.Color(255, 255, 254));
                lbNombre.setText("NOMBRE COMPLETO: ");
                ventanaSignup.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

                tfNombre.setBackground(new java.awt.Color(36, 38, 41));
                tfNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfNombre.setForeground(new java.awt.Color(148, 161, 178));
                tfNombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                tfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                tfNombreKeyPressed(evt);
                        }

                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfNombreKeyReleased(evt);
                        }
                });
                ventanaSignup.add(tfNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 310, 30));

                lbEdad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbEdad.setForeground(new java.awt.Color(255, 255, 254));
                lbEdad.setText("EDAD:");
                ventanaSignup.add(lbEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

                comboEdad.setBackground(new java.awt.Color(36, 38, 41));
                comboEdad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                comboEdad.setForeground(new java.awt.Color(255, 255, 254));
                comboEdad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
                comboEdad.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                comboEdadActionPerformed(evt);
                        }
                });
                ventanaSignup.add(comboEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, -1, 30));

                lbDepartamento.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbDepartamento.setForeground(new java.awt.Color(255, 255, 254));
                lbDepartamento.setText("DEPARTAMENTO:");
                ventanaSignup.add(lbDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

                comboDepartamento.setBackground(new java.awt.Color(36, 38, 41));
                comboDepartamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                comboDepartamento.setForeground(new java.awt.Color(255, 255, 254));
                comboDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
                comboDepartamento.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                comboDepartamentoActionPerformed(evt);
                        }
                });
                ventanaSignup.add(comboDepartamento,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 300, 30));

                lbCiudad.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbCiudad.setForeground(new java.awt.Color(255, 255, 254));
                lbCiudad.setText("CIUDAD:");
                ventanaSignup.add(lbCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

                comboCiudad.setBackground(new java.awt.Color(36, 38, 41));
                comboCiudad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                comboCiudad.setForeground(new java.awt.Color(255, 255, 254));
                comboCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
                comboCiudad.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                comboCiudadActionPerformed(evt);
                        }
                });
                ventanaSignup.add(comboCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 300, 30));

                lbCorreo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbCorreo.setForeground(new java.awt.Color(255, 255, 254));
                lbCorreo.setText("CORREO ELECTRÓNICO: ");
                ventanaSignup.add(lbCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, -1, -1));

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
                ventanaSignup.add(tfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 310, 30));

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
                ventanaSignup.add(btnEnviarCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, -1, -1));

                tfRecibirCodigo.setBackground(new java.awt.Color(36, 38, 41));
                tfRecibirCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                tfRecibirCodigo.setForeground(new java.awt.Color(148, 161, 178));
                tfRecibirCodigo.setBorder(
                                new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                tfRecibirCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                tfRecibirCodigoKeyPressed(evt);
                        }

                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfRecibirCodigoKeyReleased(evt);
                        }
                });
                ventanaSignup.add(tfRecibirCodigo,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 180, 30));

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
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 450, -1, -1));

                lbConfContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbConfContraseña.setForeground(new java.awt.Color(255, 255, 254));
                lbConfContraseña.setText("CONFIRMAR CONTRASEÑA: ");
                ventanaSignup.add(lbConfContraseña,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, -1, -1));

                pfConfirmarContraseña.setBackground(new java.awt.Color(36, 38, 41));
                pfConfirmarContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                pfConfirmarContraseña.setForeground(new java.awt.Color(148, 161, 178));
                pfConfirmarContraseña.setBorder(
                                new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                pfConfirmarContraseña.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                pfConfirmarContraseñaActionPerformed(evt);
                        }
                });
                pfConfirmarContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                pfConfirmarContraseñaKeyReleased(evt);
                        }
                });
                ventanaSignup.add(pfConfirmarContraseña,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 570, 310, 30));

                lbContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbContraseña.setForeground(new java.awt.Color(255, 255, 254));
                lbContraseña.setText("CONTRASEÑA: ");
                ventanaSignup.add(lbContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, -1, -1));

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
                ventanaSignup.add(pfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, 310, 30));

                btnRegistrarse.setBackground(new java.awt.Color(127, 90, 240));
                btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                btnRegistrarse.setForeground(new java.awt.Color(255, 255, 254));
                btnRegistrarse.setText("Registrarse");
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
                ventanaSignup.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 630, -1, -1));

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
                ventanaSignup.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 630, -1, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaSignup, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ventanaSignup, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void comboDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {
                desactivarBotonRegistrarse();
                desactivarComboCiudad();
                ponerCiudades();

        }

        private void comboCiudadActionPerformed(java.awt.event.ActionEvent evt) {
                desactivarBotonRegistrarse();
        }

        private void pfContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
        }

        private void pfConfirmarContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
        }

        private void pfConfirmarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
                registrarUsuario();

                Login.correoGuardar = tfCorreo.getText();

                Login login = new Login();
                login.setVisible(true);
                this.setVisible(false);
        }

        private void btnVerificarCodigoActionPerformed(java.awt.event.ActionEvent evt) {
                verificarCodigo();
        }

        private void tfRecibirCodigoKeyPressed(java.awt.event.KeyEvent evt) {

        }

        private void tfRecibirCodigoKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonVerificarCodigo();
        }

        private void tfNombreKeyPressed(java.awt.event.KeyEvent evt) {

        }

        private void tfNombreKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
                desactivarBotonEnviarCodigo();
        }

        private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {
                Login login = new Login();
                login.setVisible(true);
                this.setVisible(false);

        }

        private void tfCorreoKeyPressed(java.awt.event.KeyEvent evt) {

        }

        private void tfCorreoKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
                desactivarBotonEnviarCodigo();
                desactivarBotonVerificarCodigo();
        }

        private void btnEnviarCodigoActionPerformed(java.awt.event.ActionEvent evt)
                        throws HeadlessException, SQLException {
                enviarCodigo();
        }

        private void pfContraseñaActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void comboEdadActionPerformed(java.awt.event.ActionEvent evt) {

                desactivarBotonRegistrarse();
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
                        java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE,
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

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(() -> {
                        new Signup().setVisible(true);
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnEnviarCodigo;
        private javax.swing.JButton btnRegistrarse;
        private javax.swing.JButton btnRegresar;
        private javax.swing.JButton btnVerificarCodigo;
        private javax.swing.JComboBox<String> comboCiudad;
        private javax.swing.JComboBox<String> comboDepartamento;
        private javax.swing.JComboBox<String> comboEdad;
        private javax.swing.JLabel lbCiudad;
        private javax.swing.JLabel lbConfContraseña;
        private javax.swing.JLabel lbContraseña;
        private javax.swing.JLabel lbCorreo;
        private javax.swing.JLabel lbDepartamento;
        private javax.swing.JLabel lbEdad;
        private javax.swing.JLabel lbNombre;
        private javax.swing.JLabel lbRegistro;
        private javax.swing.JPasswordField pfConfirmarContraseña;
        private javax.swing.JPasswordField pfContraseña;
        private javax.swing.JTextField tfCorreo;
        private javax.swing.JTextField tfNombre;
        private javax.swing.JTextField tfRecibirCodigo;
        private javax.swing.JPanel ventanaSignup;
        // End of variables declaration//GEN-END:variables
}
