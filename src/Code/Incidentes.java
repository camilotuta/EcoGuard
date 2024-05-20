// cSpell:ignore contaminacion deforestacion mineria plasticos
package Code;

import java.util.HashMap;
import java.util.Map;

public class Incidentes {

    public Map<String, String> incidentes = new HashMap<>();

    public Incidentes() {
        incidentes.put("Derrames de petróleo", "src/img/iconosIncidentes/derramePetroleo.png");
        incidentes.put("Contaminación del agua", "src/img/iconosIncidentes/contaminacionAgua.png");
        incidentes.put("Contaminación del aire", "src/img/iconosIncidentes/contaminacionAire.png");
        incidentes.put("Deforestación", "src/img/iconosIncidentes/deforestacion.png");
        incidentes.put("Incendios forestales", "src/img/iconosIncidentes/incendio.png");
        incidentes.put("Especies invasoras", "src/img/iconosIncidentes/plagas.png");
        incidentes.put("Minería ilegal", "src/img/iconosIncidentes/mineriaIlegal.png");
        incidentes.put("Uso excesivo de plásticos", "src/img/iconosIncidentes/plasticosResiduales.png");
    }
}
