package org.example;

import java.io.IOException;
import java.util.List;

public class ProcesadoresContabilidad extends Thread {
    private String archivo;

    public ProcesadoresContabilidad(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }

    @Override
    public void run() {
        try {
            List<String> transacciones = UtilidadesFichero.leerTransacciones(archivo);
            long suma = UtilidadesFichero.sumarTransacciones(transacciones);
            UtilidadesFichero.guardarResultado(archivo + ".res", suma);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
