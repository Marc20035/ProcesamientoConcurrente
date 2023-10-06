package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {
    public static void mostrarMain() {
        String[] archivos = {"informatica.txt", "gerencia.txt", "contabilidad.txt", "comercio.txt", "recursos_humanos.txt"};

        List<ProcesadoresContabilidad> procesadores = new ArrayList<>();
        for (String archivo : archivos) {
            ProcesadoresContabilidad pc = new ProcesadoresContabilidad(archivo);
            pc.start();
            procesadores.add(pc);
        }

        long sumaGlobal = 0;
        for (ProcesadoresContabilidad pc : procesadores) {
            try {
                pc.join();
                List<String> resultado = UtilidadesFichero.leerTransacciones(pc.getArchivo() + ".res");
                sumaGlobal += UtilidadesFichero.sumarTransacciones(resultado);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }

        try {
            UtilidadesFichero.guardarResultado("Resultado_global.txt", sumaGlobal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
