/*
	cSpell:ignore desencriptar
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Signup;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import Code.Conexion;
import Code.Desencriptar;
import Code.EnviarCorreo;
import Screens.Custom.CambiarIU;
import Screens.Custom.ComboBox;
import Screens.Custom.ObtenerIU;
import Screens.Login.Login;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DateEvent;
import raven.datetime.component.date.DatePicker;
import raven.datetime.component.date.DateSelectionListener;

import javax.swing.JFormattedTextField;

/**
 *
 * @author tutaa
 */
public class Signup extends javax.swing.JFrame {
        // TODO: CORREOGIR METODO REGISTRAR, CAMBIAR EN LA BASE DE DATOS EDAD POR FECHA
        // Y HACER LA DEBIDA RESTA PARA PONERLA EN PERSONAL PROFILE Y EN VIEW PROFILE

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Random random = new Random();
        private Boolean correoVerificado = false;
        private String codigo = String.valueOf(random.nextInt(100_000, 999_999));
        private Integer intentos = 3;

        private boolean fechaValida = false;

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
                ComboBox.ponerDepartamentos(comboDepartamento);
                mostrarErrores();
        }

        private void desactivarBotonRegistrarse() {
                btnRegistrarse.setEnabled((fechaValida && correoVerificado
                                && (ObtenerIU.obtenerTextoCampo(tfNombre).length() >= 10)
                                && (ObtenerIU.obtenerIndiceSeleccionCombo(comboDepartamento) != 0)
                                && (ObtenerIU.obtenerIndiceSeleccionCombo(comboCiudad) != 0)
                                && (ObtenerIU.obtenerTextoCampo(tfCorreo).contains("@"))
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
                                ObtenerIU.obtenerTextoCampo(tfNombre).length() > 8
                                                && ObtenerIU.obtenerTextoCampo(tfCorreo).contains("@")
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

        private void desactivarComboCiudad() {
                comboCiudad.setEnabled(ObtenerIU.obtenerIndiceSeleccionCombo(comboDepartamento) != 0);
        }

        private void verificarCodigo() {

                if (ObtenerIU.obtenerTextoCampo(tfRecibirCodigo).equals(codigo) && intentos > 0) {
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

        private Boolean correoEstaRegistrado(String correo) throws SQLException {
                var datosUsuarioRegistrado = Conexion.seleccionar(
                                String.format("SELECT * FROM usuarios WHERE correo = '%s'",
                                                correo),
                                new String[] { "correo" });

                return datosUsuarioRegistrado.size() == 1;
        }

        private void enviarCodigo() throws HeadlessException, SQLException {
                String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();
                String nombre = ObtenerIU.obtenerTextoCampo(tfNombre);

                if (!correoEstaRegistrado(correo)) {

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

        private void registrarUsuario() throws SQLException {
                String correo = ObtenerIU.obtenerTextoCampo(tfCorreo).toLowerCase();
                String contraseña = Desencriptar.desencriptarContra(ObtenerIU.obtenerContraseña(pfContraseña));
                String nombre = ObtenerIU.obtenerTextoCampo(tfNombre);
                String departamento = (String) ObtenerIU.obtenerSeleccionCombo(comboDepartamento);
                String ciudad = (String) ObtenerIU.obtenerSeleccionCombo(comboCiudad);
                int edad = 0;
                Conexion.registrar(
                                String.format("INSERT INTO usuarios (correo,contraseña,nombre,departamento,ciudad,edad,"
                                                + "fecha_registro,biografia ) VALUES ('%s','%s','%s','%s','%s',%d,DATE ('now'),' ');",
                                                correo, contraseña, nombre, departamento, ciudad, edad));
                JOptionPane.showMessageDialog(this, "¡REGISTRO EXITOSO!", "¡AVISO!",
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

        private String obtenerFechaHoy() {
                LocalDate fechaActual = LocalDate.now();
                return fechaActual.format(formatter);
        }

        private double restarFechas(String fInicio, String fFinal) {

                LocalDate fechaInicio = LocalDate.parse(fInicio, formatter);
                LocalDate fechaFin = LocalDate.parse(fFinal, formatter);
                System.out.println(fechaInicio);
                System.out.println(fechaFin);

                Period periodo = Period.between(fechaInicio, fechaFin);

                System.out.println(periodo.getYears() * 365);
                return periodo.getYears() * 365;
        }

        private String obtenerFechaSeleccionada(JFormattedTextField ftFechaNacimiento) {
                var date = datePicker.getSelectedDate();

                return formatter.format(date);
        }

        private void mostrarErrores() {
                // tfNombre
                if (ObtenerIU.obtenerTextoCampo(tfNombre).length() < 8) {
                        CambiarIU.setImageLabel(lbErrorNombre,
                                        "src/img/error.png");
                        lbErrorNombre.setToolTipText("El nombre debe tener mínimo 8 caracteres.");
                } else {
                        CambiarIU.setImageLabel(lbErrorNombre,
                                        "src/img/check.png");
                        lbErrorNombre.setToolTipText("El nombre tiene mínimo 8 caracteres.");
                }
                // fechaNacimiento

                if (ftFechaNacimiento.getText().equals("--/--/----")) {
                        CambiarIU.setImageLabel(lbErrorFechaNacimiento,
                                        "src/img/error.png");
                        lbErrorFechaNacimiento.setToolTipText("Debe seleccionar una fecha válida.");
                } else {
                        if (restarFechas(obtenerFechaSeleccionada(ftFechaNacimiento), obtenerFechaHoy()) > 6570) {
                                CambiarIU.setImageLabel(lbErrorFechaNacimiento,
                                                "src/img/check.png");
                                lbErrorFechaNacimiento.setToolTipText("Ha seleccionado una fecha.");
                                fechaValida = true;
                        } else {

                                CambiarIU.setImageLabel(lbErrorFechaNacimiento,
                                                "src/img/error.png");
                                lbErrorFechaNacimiento.setToolTipText("Debe ser mayor de edad.");
                                fechaValida = false;
                        }
                }

                // comboDepartamento
                if (ObtenerIU.obtenerIndiceSeleccionCombo(comboDepartamento) == 0) {
                        CambiarIU.setImageLabel(lbErrorDepartamento,
                                        "src/img/error.png");
                        lbErrorDepartamento.setToolTipText("Debe seleccionar un departamento.");
                } else {
                        CambiarIU.setImageLabel(lbErrorDepartamento,
                                        "src/img/check.png");
                        lbErrorDepartamento.setToolTipText("Ha seleccionado un departamento.");
                }

                // comboCiudad
                if (ObtenerIU.obtenerIndiceSeleccionCombo(comboCiudad) == 0) {
                        CambiarIU.setImageLabel(lbErrorCiudad,
                                        "src/img/error.png");
                        lbErrorCiudad.setToolTipText("Debe seleccionar una ciudad.");
                } else {
                        CambiarIU.setImageLabel(lbErrorCiudad,
                                        "src/img/check.png");
                        lbErrorCiudad.setToolTipText("Ha seleccionado una ciudad.");
                }

                // tfCorreo
                if (!ObtenerIU.obtenerTextoCampo(tfCorreo).contains("@")) {
                        CambiarIU.setImageLabel(lbErrorCorreo,
                                        "src/img/error.png");
                        lbErrorCorreo.setToolTipText("El correo no es válido.");
                } else {
                        CambiarIU.setImageLabel(lbErrorCorreo,
                                        "src/img/check.png");
                        lbErrorCorreo.setToolTipText("El correo debe ser válido.");
                }

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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                datePicker = new DatePicker();
                ventanaSignup = new javax.swing.JPanel();
                lbErrorNombre = new javax.swing.JLabel();
                lbErrorFechaNacimiento = new javax.swing.JLabel();
                lbErrorDepartamento = new javax.swing.JLabel();
                lbErrorCiudad = new javax.swing.JLabel();
                lbErrorCorreo = new javax.swing.JLabel();
                lbErrorContraseña = new javax.swing.JLabel();
                lbErrorConfContraseña = new javax.swing.JLabel();
                lbRegistro = new javax.swing.JLabel();
                lbNombre = new javax.swing.JLabel();
                tfNombre = new javax.swing.JTextField();
                lbFechaNacimiento = new javax.swing.JLabel();
                ftFechaNacimiento = new JFormattedTextField();
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
                imgFondo = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaSignup.setBackground(new java.awt.Color(22, 22, 26));
                ventanaSignup.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaSignup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                ventanaSignup.add(lbErrorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 25, 25));
                ventanaSignup.add(lbErrorFechaNacimiento,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 25, 25));
                ventanaSignup.add(lbErrorDepartamento,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, 25, 25));
                ventanaSignup.add(lbErrorCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 350, 25, 25));
                ventanaSignup.add(lbErrorCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 400, 25, 25));
                ventanaSignup.add(lbErrorContraseña,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 510, 25, 25));
                ventanaSignup.add(lbErrorConfContraseña,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 570, 25, 25));

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
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                tfNombreKeyReleased(evt);
                        }
                });
                ventanaSignup.add(tfNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 310, 30));

                lbFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                lbFechaNacimiento.setForeground(new java.awt.Color(255, 255, 254));
                lbFechaNacimiento.setText("FECHA DE NACIMIENTO:");
                ventanaSignup.add(lbFechaNacimiento,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

                datePicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);

                datePicker.setEditor(ftFechaNacimiento);
                setLayout(new MigLayout());

                ftFechaNacimiento.setBackground(new java.awt.Color(36, 38, 41));
                ftFechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                ftFechaNacimiento.setForeground(new java.awt.Color(255, 255, 254));
                ventanaSignup.add(ftFechaNacimiento,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 170, 30));

                datePicker.setDateSelectionAble(localDate -> !localDate.isAfter(LocalDate.now()));
                datePicker.addDateSelectionListener(new DateSelectionListener() {
                        @Override
                        public void dateSelected(DateEvent dateEvent) {
                                mostrarErrores();
                        }
                });

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
                ventanaSignup.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 630, -1, -1));

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
                ventanaSignup.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 630, -1, -1));

                imgFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
                imgFondo.setText("jLabel1");
                ventanaSignup.add(imgFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

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
                ComboBox.ponerCiudades(comboCiudad, comboDepartamento);
                desactivarBotonRegistrarse();
                desactivarComboCiudad();
                mostrarErrores();
        }

        private void comboCiudadActionPerformed(java.awt.event.ActionEvent evt) {
                desactivarBotonRegistrarse();
                mostrarErrores();
        }

        private void pfContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
                mostrarErrores();
        }

        private void pfConfirmarContraseñaKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
                mostrarErrores();
        }

        private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
                registrarUsuario();

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

        private void tfNombreKeyReleased(java.awt.event.KeyEvent evt) {
                desactivarBotonRegistrarse();
                desactivarBotonEnviarCodigo();
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

        private void comboEdadActionPerformed(java.awt.event.ActionEvent evt) {
                desactivarBotonRegistrarse();
                mostrarErrores();
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
        private DatePicker datePicker;
        private javax.swing.JButton btnRegresar;
        private javax.swing.JButton btnVerificarCodigo;
        private javax.swing.JComboBox<String> comboCiudad;
        private javax.swing.JComboBox<String> comboDepartamento;
        private JFormattedTextField ftFechaNacimiento;
        private javax.swing.JLabel imgFondo;
        private javax.swing.JLabel lbCiudad;
        private javax.swing.JLabel lbConfContraseña;
        private javax.swing.JLabel lbContraseña;
        private javax.swing.JLabel lbCorreo;
        private javax.swing.JLabel lbDepartamento;
        private javax.swing.JLabel lbFechaNacimiento;
        private javax.swing.JLabel lbErrorCiudad;
        private javax.swing.JLabel lbErrorConfContraseña;
        private javax.swing.JLabel lbErrorContraseña;
        private javax.swing.JLabel lbErrorCorreo;
        private javax.swing.JLabel lbErrorDepartamento;
        private javax.swing.JLabel lbErrorFechaNacimiento;
        private javax.swing.JLabel lbErrorNombre;
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
