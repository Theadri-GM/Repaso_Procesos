package Procesos_Procesos.EjercicioPlataforma;

import java.io.File;
import java.io.IOException;

public class Ejercicio1_ActividadSencillaProcesos2 {
    public static void main(String[] args) throws IOException {
        String file = "/etc/init.d";
        ProcessBuilder pb = new ProcessBuilder().command("ls" , file);
        pb.redirectOutput(new File("/tmp/salida.out"));

    }
}
