package Procesos_Procesos.ProcessAndRuntimeRepetimos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaPing {
    public static void main(String[] args) {
        String commands[] = {"ping ", "8.8.8.8"};
        Runtime runtime = Runtime.getRuntime();
        Process pb = null;

        try{
            pb = runtime.exec(commands[0] + commands[1]);
            BufferedReader bf = new BufferedReader(new InputStreamReader(pb.getInputStream()));
            for (int i=0; i<=3; i++){
                System.out.println("Hola desde la ejecución " + i + " " + bf.readLine());
            }
        } catch (IOException e) {
            System.out.println("No hemos podido ejecutar el comando correctamente.");
            System.exit(-1);
        }

        if (pb!=null){
            pb.destroy();
        }

        try{
            pb.waitFor();
        } catch (InterruptedException e) {
            System.out.println("No he podido esperar porque el proceso ya habia terminado.");
            System.exit(-1);
        }

        System.out.println("Proceso finalizado con salida: " + pb.exitValue());
        System.exit(0);

    }
}
