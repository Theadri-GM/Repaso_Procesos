package Procesos_ping.ProcessAndRuntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaPing {
    public static void main(String[] args) {
        // Instanciamos Runtime y nuestro Porcess.
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        // Instanciamos dentro de un Array de Strings el comando que queramos ejecutar y lo demas comandos necesarios.
        String command[] = {"ping ", "-c 3 " ,"8.8.8.8" };
        // Ahora, dentro de un bloque try{}catch{} ponemos lo que queremos que nuestro programa haga.
        try{
            // Ahora, en nuestro proceso, guardaremos la ejecución de nuestro runtime pasandole el comando y mediante
            // la función exec en runtime.
            process = runtime.exec(command[0] + command[1] + command[2]);
            // Con el BufferReader, guardamos dentro dentro de él el resultado de nuestro comando.
            BufferedReader in = new BufferedReader((new InputStreamReader(process.getInputStream())));
            // Ahora, para mostrar lo que se ha guardado dentro de nuestro BufferReader, mediante un bucle for, sacamos
            // por pantalla la ejecución de nuestro comando mediante la función readLine() sobre nuestro BufferedReader.
            for (int i=0; i<=3; i++){
                System.out.println("Saludo desde ping " + i + " " + in.readLine() + ".");
            }
        } catch (IOException e) {
            System.out.println("No se ha hecho el ping.");
            System.exit(-1);
        }
        // Ahora, comprobamos que la ejecución del runtime no haya sido null. Y si no ha sido null, lo destruimos.
        if (process!=null){
            process.destroy();
        }
        // Ahora, esperamos a que nuestro proceso termine. Y si no espera, se mostrará el mensaje de error que hay
        // almacenado en nuestro catch y se cambiará el código de ejecución como si hubiese habido un error.
        try{
            process.waitFor();
        } catch (InterruptedException e) {
            System.out.println("No se ha hecho la espera. Ya habia terminado la ejecución.");
            System.exit(-1);
        }
        // Para finalizar, mostramos por pantalla el estado de término de nuestro programa para saber si ha habido algún
        // error.
        System.out.println("Estado de término: " + process.exitValue());
        System.exit(0);
    }
}
