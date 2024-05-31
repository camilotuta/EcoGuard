// cspell:ignore rawtypes
package Screens.Custom;

import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import Code.Incidentes;
import Code.Ubicaciones;

public class ComboBox {

    static Ubicaciones ubicaciones = new Ubicaciones();
    static Map<String, List<String>> departamentos = ubicaciones.departamentos;

    static Incidentes incidentes = new Incidentes();
    public static Map<String, String> incidentesAmbientales = incidentes.incidentes;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void vaciarCombo(JComboBox comboBox) {
        comboBox.removeAllItems();
        comboBox.addItem("Seleccionar");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void ponerDepartamentos(JComboBox comboDepartamento) {
        vaciarCombo(comboDepartamento);
        for (String departamento : departamentos.keySet()) {
            comboDepartamento.addItem(departamento);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void ponerCiudades(JComboBox comboCiudad, JComboBox comboDepartamento) {
        vaciarCombo(comboCiudad);
        String seleccionDepartamento = ObtenerIU.obtenerSeleccionCombo(comboDepartamento);
        if (seleccionDepartamento != null && !seleccionDepartamento.equals("Seleccionar")) {
            List<String> ciudades = departamentos.get(seleccionDepartamento);
            for (String i : ciudades) {
                comboCiudad.addItem(i);
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    static public void ponerTipoIncidente(JComboBox comboTipoIncidente) {
        for (String i : incidentesAmbientales.keySet()) {
            comboTipoIncidente.addItem(i);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    static public void ponerEdades(JComboBox comboEdad) {
        for (int i = 13; i < 101; i++) {
            comboEdad.addItem(String.valueOf(i));
        }
    }

}
