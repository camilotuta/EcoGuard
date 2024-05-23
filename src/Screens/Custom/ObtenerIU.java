// cSpell:ignore rawtypes
package Screens.Custom;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class ObtenerIU {

    public static char[] obtenerContraseña(JPasswordField pfContraseña) {
        return pfContraseña.getPassword();
    }

    public static String obtenerTextoEtiqueta(JLabel label) {
        return (String) label.getText();
    }

    public static String obtenerTextoPanel(JTextPane panel) {
        return (String) panel.getText();
    }

    public static String obtenerTextoArea(JTextArea area) {
        return (String) area.getText();
    }

    public static String obtenerTextoCampo(JTextField area) {
        return (String) area.getText();
    }

    @SuppressWarnings("rawtypes")
    public static String obtenerSeleccionCombo(JComboBox comboBox) {
        return (String) comboBox.getSelectedItem();
    }

}
