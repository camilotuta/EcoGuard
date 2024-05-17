/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens.Principal;

import java.awt.Component;
import java.awt.Toolkit;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.*;

/**
 *
 * @author tutaa
 */
public class Principal extends javax.swing.JFrame {

	/**
	 * Creates new form Principal
	 */
	public Principal() {
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

	public static void ponerTextoEtiqueta(JLabel label, String texto) {
		label.setText(texto);
	}

	public void labeeeel() {
		JLabel label = new JLabel("Hola mundo");

		label.setName("miJLabel");

		ventanaPrincipal.add(label);

		// Agregar el JPanel al JFrame
		this.add(ventanaPrincipal);

		// Hacer visible el JFrame
		this.setVisible(true);
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                ventanaPrincipal = new javax.swing.JPanel();
                lbPublicaciones = new javax.swing.JLabel();
                panelMenu = new javax.swing.JPanel();
                imgCasa = new javax.swing.JLabel();
                imgVolver = new javax.swing.JLabel();
                imgUsuario = new javax.swing.JLabel();
                imgMenuBar = new javax.swing.JLabel();
                panelMapa = new javax.swing.JPanel();
                imgMarcador = new javax.swing.JLabel();
                imgMapa = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                panelPublicaciones = new javax.swing.JPanel();
                panelPublicacion = new javax.swing.JPanel();
                lbHoraIncidente = new javax.swing.JLabel();
                lbUbicacionIncidente = new javax.swing.JLabel();
                lbFechaIncidente = new javax.swing.JLabel();
                lbTipoIncidente = new javax.swing.JLabel();
                lbNombreUsuario = new javax.swing.JLabel();
                imgIconoPublicacion = new javax.swing.JLabel();
                imgEvidenciaIncidente = new javax.swing.JLabel();
                imgFondoPub = new javax.swing.JLabel();
                lbPonerUbicacion = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                ventanaPrincipal.setBackground(new java.awt.Color(22, 22, 26));
                ventanaPrincipal.setPreferredSize(new java.awt.Dimension(1080, 720));
                ventanaPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbPublicaciones.setFont(new java.awt.Font("Crabs", 1, 100)); // NOI18N
                lbPublicaciones.setForeground(new java.awt.Color(255, 255, 254));
                lbPublicaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbPublicaciones.setText("Publicaciones");
                ventanaPrincipal.add(lbPublicaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, -1));

                panelMenu.setBackground(new java.awt.Color(22, 22, 26));
                panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casaHover2.png"))); // NOI18N
                imgCasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                panelMenu.add(imgCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

                imgVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/volver.png"))); // NOI18N
                imgVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                panelMenu.add(imgVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

                imgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
                imgUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                panelMenu.add(imgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

                imgMenuBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                imgMenuBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lineaVertical.png"))); // NOI18N
                imgMenuBar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
                panelMenu.add(imgMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, -1));

                ventanaPrincipal.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, 60, 220));

                panelMapa.setBackground(new java.awt.Color(22, 22, 26));
                panelMapa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                imgMarcador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/marcadorMapa.png"))); // NOI18N
                imgMarcador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                panelMapa.add(imgMarcador, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, -1, -1));

                imgMapa.setBackground(new java.awt.Color(22, 22, 26));
                imgMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mapaColombia.png"))); // NOI18N
                panelMapa.add(imgMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 550));

                ventanaPrincipal.add(panelMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 420, 550));

                panelPublicaciones.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                panelPublicacion.setBackground(new java.awt.Color(36, 38, 41));
                panelPublicacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                lbHoraIncidente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbHoraIncidente.setForeground(new java.awt.Color(255, 255, 254));
                lbHoraIncidente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbHoraIncidente.setText("12:30 pm");
                panelPublicacion.add(lbHoraIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));

                lbUbicacionIncidente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbUbicacionIncidente.setForeground(new java.awt.Color(255, 255, 254));
                lbUbicacionIncidente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lbUbicacionIncidente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/marcadorPublicacion.png"))); // NOI18N
                lbUbicacionIncidente.setText("Leticia - Amazonas");
                panelPublicacion.add(lbUbicacionIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, -1));

                lbFechaIncidente.setForeground(new java.awt.Color(255, 255, 254));
                lbFechaIncidente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbFechaIncidente.setText("11 - 05 -2024");
                panelPublicacion.add(lbFechaIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

                lbTipoIncidente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                lbTipoIncidente.setForeground(new java.awt.Color(127, 90, 240));
                lbTipoIncidente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbTipoIncidente.setText("INCENDIO FORESTAL");
                panelPublicacion.add(lbTipoIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

                lbNombreUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lbNombreUsuario.setForeground(new java.awt.Color(255, 255, 254));
                lbNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lbNombreUsuario.setText("Miguel Angel Bejarano Muñoz");
                panelPublicacion.add(lbNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, -1));

                imgIconoPublicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoPublicacion.png"))); // NOI18N
                panelPublicacion.add(imgIconoPublicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

                imgEvidenciaIncidente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ejemploIncendio.jpg"))); // NOI18N
                imgEvidenciaIncidente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 90, 240)));
                panelPublicacion.add(imgEvidenciaIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 80));

                imgFondoPub.setBackground(new java.awt.Color(22, 22, 26));
                imgFondoPub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPublicacion.png"))); // NOI18N
                panelPublicacion.add(imgFondoPub, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

                panelPublicaciones.add(panelPublicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 310, 200));

                jScrollPane1.setViewportView(panelPublicaciones);

                ventanaPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 390, 520));

                lbPonerUbicacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                lbPonerUbicacion.setForeground(new java.awt.Color(148, 161, 178));
                lbPonerUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                lbPonerUbicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/marcador.png"))); // NOI18N
                lbPonerUbicacion.setText("Chía - Cundinamarca");
                ventanaPrincipal.add(lbPonerUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ventanaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ventanaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new Principal().setVisible(true);
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel imgCasa;
        private javax.swing.JLabel imgEvidenciaIncidente;
        private javax.swing.JLabel imgFondoPub;
        private javax.swing.JLabel imgIconoPublicacion;
        private javax.swing.JLabel imgMapa;
        private javax.swing.JLabel imgMarcador;
        private javax.swing.JLabel imgMenuBar;
        private javax.swing.JLabel imgUsuario;
        private javax.swing.JLabel imgVolver;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JLabel lbFechaIncidente;
        private javax.swing.JLabel lbHoraIncidente;
        private javax.swing.JLabel lbNombreUsuario;
        private javax.swing.JLabel lbPonerUbicacion;
        private javax.swing.JLabel lbPublicaciones;
        private javax.swing.JLabel lbTipoIncidente;
        private javax.swing.JLabel lbUbicacionIncidente;
        private javax.swing.JPanel panelMapa;
        private javax.swing.JPanel panelMenu;
        private javax.swing.JPanel panelPublicacion;
        private javax.swing.JPanel panelPublicaciones;
        private javax.swing.JPanel ventanaPrincipal;
        // End of variables declaration//GEN-END:variables
}
