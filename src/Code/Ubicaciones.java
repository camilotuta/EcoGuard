// cSpell:ignore Antioquia Arauca Boyacá Caquetá Casanare Guainía Guaviare Huila Nariño Quindío Risaralda Tolima Vaupés Vichada antioquia arauca Arauquita atlantico Cravo Fortul boyaca Itagüí Duitama Magangué Chiquinquirá Malambo Medellín Chinchiná Mompox caqueta Florencia Caguán casanare Rionegro Riosucio cauca Popayán Quilichao Guapi Sabanalarga Samacá Aguachica Bosconia Curumaní Chiriguaná Quibdó Istmina Saravena Sahagún Cereté Sogamoso cundinamarca Soacha Tadó Girardot guainia Inírida guaviare huila Neiva Pitalito Garzón Riohacha Maicao Tambo Manaure Aracataca Tauramena Acacías narino Tierralta Ipiales Tumaco Cúcuta Ocaña putumayo Mocoa Orito Sibundoy quindio Calarcá Tebaida Quimbaya risaralda Pereira Dosquebradas santander Floridablanca Girón Piedecuesta Barrancabermeja Sincelejo Corozal Sampués Coveñas tolima Ibagué Tuluá Cartago Tunja Turbaco Mitú Túquerres Carurú Uribia Valledupar vaupes Carreño Rosalía Cumaribo vichada Villamaría Villavicencio Yopal Zipaquirá
package Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ubicaciones {

    public Map<String, List<String>> departamentos = new HashMap<>();

    public Ubicaciones() {

        // Departamento Amazonas
        List<String> amazonas = new ArrayList<>();
        amazonas.add("Leticia");
        amazonas.add("Puerto Nariño");
        // Agregar la lista de municipios al departamento
        departamentos.put("Amazonas", amazonas);

        // Departamento Antioquia
        List<String> antioquia = new ArrayList<>();
        antioquia.add("Medellín");
        antioquia.add("Bello");
        antioquia.add("Itagüí");
        antioquia.add("Envigado");
        antioquia.add("Rionegro");
        // Agregar la lista de municipios al departamento
        departamentos.put("Antioquia", antioquia);

        // Departamento Arauca
        List<String> arauca = new ArrayList<>();
        arauca.add("Arauca");
        arauca.add("Arauquita");
        arauca.add("Cravo Norte");
        arauca.add("Fortul");
        arauca.add("Puerto Rondón");
        arauca.add("Saravena");
        arauca.add("Tame");
        // Agregar la lista de municipios al departamento
        departamentos.put("Arauca", arauca);

        // Departamento Atlántico
        List<String> atlantico = new ArrayList<>();
        atlantico.add("Barranquilla");
        atlantico.add("Soledad");
        atlantico.add("Malambo");
        atlantico.add("Puerto Colombia");
        atlantico.add("Sabanalarga");
        // Agregar la lista de municipios al departamento
        departamentos.put("Atlántico", atlantico);

        // Departamento Bolívar
        List<String> bolivar = new ArrayList<>();
        bolivar.add("Cartagena");
        bolivar.add("Magangué");
        bolivar.add("Turbaco");
        bolivar.add("El Carmen de Bolívar");
        bolivar.add("Mompox");
        // Agregar la lista de municipios al departamento
        departamentos.put("Bolívar", bolivar);

        // Departamento Boyacá
        List<String> boyaca = new ArrayList<>();
        boyaca.add("Tunja");
        boyaca.add("Duitama");
        boyaca.add("Sogamoso");
        boyaca.add("Chiquinquirá");
        boyaca.add("Samacá");
        // Agregar la lista de municipios al departamento
        departamentos.put("Boyacá", boyaca);

        // Departamento Caldas
        List<String> caldas = new ArrayList<>();
        caldas.add("Manizales");
        caldas.add("Villamaría");
        caldas.add("Chinchiná");
        caldas.add("Riosucio");
        caldas.add("La Dorada");
        // Agregar la lista de municipios al departamento
        departamentos.put("Caldas", caldas);

        // Departamento Caquetá
        List<String> caqueta = new ArrayList<>();
        caqueta.add("Florencia");
        caqueta.add("Milán");
        caqueta.add("San Vicente del Caguán");
        caqueta.add("Solano");
        caqueta.add("Puerto Rico");
        // Agregar la lista de municipios al departamento
        departamentos.put("Caquetá", caqueta);

        // Departamento Casanare
        List<String> casanare = new ArrayList<>();
        casanare.add("Yopal");
        casanare.add("Aguazul");
        casanare.add("Villanueva");
        casanare.add("Monterrey");
        casanare.add("Tauramena");
        // Agregar la lista de municipios al departamento
        departamentos.put("Casanare", casanare);

        // Departamento Cauca
        List<String> cauca = new ArrayList<>();
        cauca.add("Popayán");
        cauca.add("Santander de Quilichao");
        cauca.add("Puerto Tejada");
        cauca.add("Guapi");
        cauca.add("El Tambo");
        // Agregar la lista de municipios al departamento
        departamentos.put("Cauca", cauca);

        // Departamento Cesar
        List<String> cesar = new ArrayList<>();
        cesar.add("Valledupar");
        cesar.add("Aguachica");
        cesar.add("Bosconia");
        cesar.add("Curumaní");
        cesar.add("Chiriguaná");
        // Agregar la lista de municipios al departamento
        departamentos.put("Cesar", cesar);

        // Departamento Chocó
        List<String> choco = new ArrayList<>();
        choco.add("Quibdó");
        choco.add("Istmina");
        choco.add("Tadó");
        choco.add("Riosucio");
        choco.add("Bahía Solano");
        // Agregar la lista de municipios al departamento
        departamentos.put("Chocó", choco);

        // Departamento Córdoba
        List<String> cordoba = new ArrayList<>();
        cordoba.add("Montería");
        cordoba.add("Lorica");
        cordoba.add("Sahagún");
        cordoba.add("Cereté");
        cordoba.add("Tierralta");
        // Agregar la lista de municipios al departamento
        departamentos.put("Córdoba", cordoba);

        // Departamento Cundinamarca
        List<String> cundinamarca = new ArrayList<>();
        cundinamarca.add("Bogotá");
        cundinamarca.add("Soacha");
        cundinamarca.add("Chía");
        cundinamarca.add("Zipaquirá");
        cundinamarca.add("Girardot");
        // Agregar la lista de municipios al departamento
        departamentos.put("Cundinamarca", cundinamarca);

        // Departamento Guainía
        List<String> guainia = new ArrayList<>();
        guainia.add("Inírida");
        // Agregar la lista de municipios al departamento
        departamentos.put("Guainía", guainia);

        // Departamento Guaviare
        List<String> guaviare = new ArrayList<>();
        guaviare.add("San José del Guaviare");
        guaviare.add("Calamar");
        guaviare.add("El Retorno");
        guaviare.add("Miraflores");
        // Agregar la lista de municipios al departamento
        departamentos.put("Guaviare", guaviare);

        // Departamento Huila
        List<String> huila = new ArrayList<>();
        huila.add("Neiva");
        huila.add("Pitalito");
        huila.add("Garzón");
        huila.add("La Plata");
        huila.add("San Agustín");
        // Agregar la lista de municipios al departamento
        departamentos.put("Huila", huila);

        // Departamento La Guajira
        List<String> laGuajira = new ArrayList<>();
        laGuajira.add("Riohacha");
        laGuajira.add("Maicao");
        laGuajira.add("Uribia");
        laGuajira.add("Manaure");
        laGuajira.add("San Juan del Cesar");
        // Agregar la lista de municipios al departamento
        departamentos.put("La Guajira", laGuajira);

        // Departamento Magdalena
        List<String> magdalena = new ArrayList<>();
        magdalena.add("Santa Marta");
        magdalena.add("Ciénaga");
        magdalena.add("Fundación");
        magdalena.add("Aracataca");
        magdalena.add("El Banco");
        // Agregar la lista de municipios al departamento
        departamentos.put("Magdalena", magdalena);

        // Departamento Meta
        List<String> meta = new ArrayList<>();
        meta.add("Villavicencio");
        meta.add("Acacías");
        meta.add("Granada");
        meta.add("Puerto López");
        meta.add("San Martín");
        // Agregar la lista de municipios al departamento
        departamentos.put("Meta", meta);

        // Departamento Nariño
        List<String> narino = new ArrayList<>();
        narino.add("Pasto");
        narino.add("Tumaco");
        narino.add("Ipiales");
        narino.add("Túquerres");
        narino.add("La Unión");
        // Agregar la lista de municipios al departamento
        departamentos.put("Nariño", narino);

        // Departamento Norte de Santander
        List<String> norteDeSantander = new ArrayList<>();
        norteDeSantander.add("Cúcuta");
        norteDeSantander.add("Ocaña");
        norteDeSantander.add("Pamplona");
        norteDeSantander.add("Villa del Rosario");
        norteDeSantander.add("Los Patios");
        // Agregar la lista de municipios al departamento
        departamentos.put("Norte de Santander", norteDeSantander);

        // Departamento Putumayo
        List<String> putumayo = new ArrayList<>();
        putumayo.add("Mocoa");
        putumayo.add("Puerto Asís");
        putumayo.add("Orito");
        putumayo.add("Sibundoy");
        putumayo.add("San Francisco");
        // Agregar la lista de municipios al departamento
        departamentos.put("Putumayo", putumayo);

        // Departamento Quindío
        List<String> quindio = new ArrayList<>();
        quindio.add("Armenia");
        quindio.add("Calarcá");
        quindio.add("La Tebaida");
        quindio.add("Montenegro");
        quindio.add("Quimbaya");
        // Agregar la lista de municipios al departamento
        departamentos.put("Quindío", quindio);

        // Departamento Risaralda
        List<String> risaralda = new ArrayList<>();
        risaralda.add("Pereira");
        risaralda.add("Dosquebradas");
        risaralda.add("Santa Rosa de Cabal");
        risaralda.add("La Virginia");
        risaralda.add("Belén de Umbría");
        // Agregar la lista de municipios al departamento
        departamentos.put("Risaralda", risaralda);

        // Departamento San Andrés y Providencia
        List<String> sanAndresYProvidencia = new ArrayList<>();
        sanAndresYProvidencia.add("San Andrés");
        sanAndresYProvidencia.add("Providencia");
        // Agregar la lista de municipios al departamento
        departamentos.put("San Andrés y Providencia", sanAndresYProvidencia);

        // Departamento Santander
        List<String> santander = new ArrayList<>();
        santander.add("Bucaramanga");
        santander.add("Floridablanca");
        santander.add("Girón");
        santander.add("Piedecuesta");
        santander.add("Barrancabermeja");
        // Agregar la lista de municipios al departamento
        departamentos.put("Santander", santander);

        // Departamento Sucre
        List<String> sucre = new ArrayList<>();
        sucre.add("Sincelejo");
        sucre.add("Corozal");
        sucre.add("Sampués");
        sucre.add("San Marcos");
        sucre.add("Coveñas");
        // Agregar la lista de municipios al departamento
        departamentos.put("Sucre", sucre);

        // Departamento Tolima
        List<String> tolima = new ArrayList<>();
        tolima.add("Ibagué");
        tolima.add("Espinal");
        tolima.add("Melgar");
        tolima.add("Honda");
        tolima.add("Chaparral");
        // Agregar la lista de municipios al departamento
        departamentos.put("Tolima", tolima);

        // Departamento Valle del Cauca
        List<String> valleDelCauca = new ArrayList<>();
        valleDelCauca.add("Cali");
        valleDelCauca.add("Buenaventura");
        valleDelCauca.add("Palmira");
        valleDelCauca.add("Tuluá");
        valleDelCauca.add("Cartago");
        // Agregar la lista de municipios al departamento
        departamentos.put("Valle del Cauca", valleDelCauca);

        // Departamento Vaupés
        List<String> vaupes = new ArrayList<>();
        vaupes.add("Mitú");
        vaupes.add("Carurú");
        // Agregar la lista de municipios al departamento
        departamentos.put("Vaupés", vaupes);

        // Departamento Vichada
        List<String> vichada = new ArrayList<>();
        vichada.add("Puerto Carreño");
        vichada.add("La Primavera");
        vichada.add("Santa Rosalía");
        vichada.add("Cumaribo");
        // Agregar la lista de municipios al departamento
        departamentos.put("Vichada", vichada);
    }
}