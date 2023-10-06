package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

public class UtilidadesFichero {
    public static List<String> leerTransacciones(String archivo) throws IOException {
        List<String> transacciones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                transacciones.add(linea);
            }
        }

        return transacciones;
    }

    /**
     * Suma las transacciones y devuelve el total.
     */
    public static long sumarTransacciones(List<String> transacciones) {
        long suma = 0;

        for (String transaccion : transacciones) {
            try {
                suma += Long.parseLong(transaccion);
            } catch (NumberFormatException e) {
                System.err.println("Error en formato de número: " + transaccion);
            }
        }

        return suma;
    }

    /**
     * Guarda la suma en un archivo.
     */
    public static void guardarResultado(String archivo, long suma) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println(suma);
        }
    }
}

