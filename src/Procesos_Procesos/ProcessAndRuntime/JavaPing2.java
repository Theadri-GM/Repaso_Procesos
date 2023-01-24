package Procesos_Procesos.ProcessAndRuntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaPing2 {
    public static void main(String[] args) {
        // Instanciamos los comandos necesarios en un array de Strings.
        String command[] = {"ping ", "-c 3 " ,"8.8.8.8" };
        // Y también instanciamos nuestro Runtime y nuestro Proceso. (Necesarios ambos).
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        // Ahora, dentro de un bloque try{}catch{}, encapsularemos lo que queremos ejecutar en nuestra aplicación.
        try{
            // Dentro de nuestro proceso, realizamos la ejecución de nuestro runtime con la ayuda de la función exec().
            process = runtime.exec(command[0] + command[1] + command[2]);
            // Dentro de nuestro BufferedRedader, guardamos lo que nos devuelve el resultado del comando.
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // Con la ayuda de un bucle for, devolvemos lo que hay dentro de nuestro BufferedReader
            // (que contiene lo que ha devuelto la ejecución de nuestro comando que le hemos pasado)
            // y lo mostramos por pantalla con la ayuda de la función readLine();
            for(int i=0; i<=3; i++){
                System.out.println("Saludo desde ping " + i + " " + in.readLine());
            }
        } catch (IOException e) {
            System.out.println("No se ha podido correr ping");
            System.exit(-1);
        }
        // Ahora, comprobamos que la ejecución del Runtime no ha sido null y si no lo ha sido, destruimos el proceso.
        if (process != null){
            process.destroy();
            System.out.println("Mato al proceso ping. Pero el proceso sigue.");
        }
        // Ahora, esperaremos a que nuestro proceso termine. Si ya ha terminado, mostrará que nuestro proceso ya había
        // terminado y no ha podido realizar la espera. Y por si no fuera poco, también le cambiamos el exit a -1
        // para mostrar que ha habido un error en la ejecución de nuestro programa.
        try{
            System.out.println("Ahora, espero a que mi proceso termine...");
            process.waitFor();
            System.out.println("Mi proceso ha terminado.");
        } catch (InterruptedException e) {
            System.out.println("No he podido esperar a mi proceso. Ya había terminado :( ...");
            System.exit(-1);
        }
        // Y por último, mostramos por pantalla el exitValue() que nos devolverá el código de ejecución de nuestra app
        // y este nos dirá si ha habido o no un error en nuestra app.
        System.out.println("Estado de término: " + process.exitValue());
        System.exit(0);
    }
}
