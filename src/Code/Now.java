package Code;

import java.util.Calendar;

public class Now {

    public static String obtenerAño() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public static String obtenerMes() {
        return String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
    }

    public static String obtenerDia() {
        return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
}
