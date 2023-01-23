package Procesos_ping.ProcessBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilder_Example {

    public void ejecutarProceso(String file){
        ProcessBuilder pb;
        try{
            pb = new ProcessBuilder().command("cat", file);
            Process proceso = pb.start();
            System.out.println("Soy el proceso padre y creare el CAT...");
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null){
                System.out.println(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String fichero="/tmp/msg.txt";
        ProcessBuilder_Example p1 = new ProcessBuilder_Example();
        p1.ejecutarProceso(fichero);
        System.out.println("Finalizado");
    }
}
