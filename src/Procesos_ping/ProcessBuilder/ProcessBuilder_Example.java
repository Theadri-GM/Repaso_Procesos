package Procesos_ping.ProcessBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

                        /* ESTE EJERCICIO NOS PERMITE REALIZAR LA LECTURA DE UN ARCHIVO. */
public class ProcessBuilder_Example {

    // Creamos metodo al que le pasamos una ruta de un fichero como String.
    public void ejecutarProceso(String file){
        // Dentro de nuestro metodo, implementamos nuestro ProcessBuilder.
        ProcessBuilder pb;
        try{
            // Ahora, a nuestro ProcessBuilder, le pasamos el comando que queremos hacer y el archivo que queremos leer
            // (que se lo hemos pasado como parámetro)
            pb = new ProcessBuilder().command("cat", file);
            pb.redirectOutput(new File("/tmp/salida.outs"));
            // Y arrancamos nuestro proceso.
            Process proceso = pb.start();
            System.out.println("Soy el proceso padre y creare el CAT...");
            // Ahora, con la ayuda de nuestro BufferedReader, guardaremos la ejecución del comando en él.
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            // Y lo iremos devolviendo mediante un bucle while al que le vamos a pasar la línea que va a almacenar
            // el contenido del BufferedReader y mediante el método readLine() podremos pasarle lo que devuelve la
            // ejecución de nuestro comando a la línea.
            String linea;
            while ((linea = br.readLine()) != null){
                System.out.println(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        // Instanciamos mediante un String donde se encuentra nuestro fichero.
        String fichero="/tmp/msg.txt";
        // Ahora, creamos un Objeto del tipo de la Clase.
        ProcessBuilder_Example p1 = new ProcessBuilder_Example();
        // Y gracias a este Objeto, podremos llamar al método anteriormente creado al que le pasaremos el fichero que
        // hemos creado anteriormente.
        p1.ejecutarProceso(fichero);
        // Mostramos la finalización del programa.
        System.out.println("Finalizado");
    }
}
