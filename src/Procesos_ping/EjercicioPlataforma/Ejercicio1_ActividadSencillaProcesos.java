package Procesos_ping.EjercicioPlataforma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1_ActividadSencillaProcesos {
    public static void main(String[] args) throws IOException {
        String file = "/etc/init.d";
        Runtime runtime = Runtime.getRuntime();
        Process p = null;
        try{
            p = runtime.exec("ls " + file);
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = bf.readLine()) != null){
                System.out.println(linea);
            }
        }catch(Exception ex){
            System.out.println("No hemos podido realizar la operacion");
        }

    }
}
