
// cSpell:ignore desencriptar 

package Code;

import javax.swing.JPasswordField;

public class Desencriptar {

    public static String desencriptarContra(JPasswordField pfContraseña) {
        char[] contraseñaEncriptada = pfContraseña.getPassword();
        return new String(contraseñaEncriptada);
    }
}
