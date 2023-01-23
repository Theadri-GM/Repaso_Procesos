package Procesos_ping.ProcessBuilderRepetimos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

                            /* RECREACIÓN DEL EJERCICIO PROCESSBUILDER_EXAMPLE */
public class ProcessBuilder_Example {

    public void ejecutarProceso(String file){
        ProcessBuilder pb;
        try{
            pb = new ProcessBuilder().command("cat", file);
            BufferedReader bf = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));
            String linea;
            while ((linea = bf.readLine()) != null){
                System.out.println(linea);
            }
        }catch(Exception ex){
            System.out.println("No se ha podido realizar el comando que ha sido indicado por un error");
        }
    }

    public static void main(String[] args) {
        String file = "/etc/msg.txt";
        ProcessBuilder_Example p1 = new ProcessBuilder_Example();
        p1.ejecutarProceso(file);
        System.out.println("Termina ejecución programa.");
    }

}
