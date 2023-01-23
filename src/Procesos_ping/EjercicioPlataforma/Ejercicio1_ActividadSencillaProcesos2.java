package Procesos_ping.EjercicioPlataforma;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1_ActividadSencillaProcesos2 {
    public static void main(String[] args) throws IOException {
        String file = "/etc/init.d";
        ProcessBuilder pb = new ProcessBuilder().command("ls" , file);
        pb.redirectOutput(new File("/tmp/salida.out"));

    }
}
