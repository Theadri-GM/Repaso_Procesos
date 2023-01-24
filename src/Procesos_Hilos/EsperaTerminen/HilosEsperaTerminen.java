package Procesos_Hilos.EsperaTerminen;

import Procesos_Hilos.Runnable.HilosConThread;
import java.util.Random;

public class HilosEsperaTerminen extends Thread{

    // Creamos los atributos de nuestros hilos.
    private String nombre;
    // Constructor por defecto.
    public HilosEsperaTerminen (String nombre){
        this.nombre = nombre;
    }
    // Hay que sobreescribir el método run().
    @Override
    public void run() {
        // Mostramos que hilo es.
        System.out.printf("Hola soy el proceso con nombre %s y acabo de comenzar.\n", this.nombre);
        // Nos ayudamos del Random para indicar un número aleatorio.
        Random r = new Random();
        for (int i=0; i<5; i++){
            // Iniciamos el random.
            int pausa = 10 +r.nextInt(500-10);
            // Mostramos la pausa que hace nuestro hilo.
            System.out.printf("Hilo: %s, hace una pausa de %d milisegundos %d\n", this.nombre, pausa, i+1);
            try{
                // Le hacemos que haga la pausa a nuestro hilo.
                Thread.sleep(pausa);
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
            }
        }
        // Se muestra la finalización del hilo.
        System.out.printf("Hola, vuelvo a ser el hilo %s, pero ahora he acabado.\n", this.nombre);
    }
    // Ejecución del main.
    public static void main(String[] args) {
        // Creamos hilos.
        Thread h1 = new HilosEsperaTerminen("Adrian");
        Thread h2 = new HilosEsperaTerminen("Gabriel");
        // Los lanzamos.
        h1.start();
        h2.start();
        // Hacemos que se unan antes que el principal y que cuando terminen el principal se ejecute detrás de ellos.
        try{
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido.");
        }
        System.out.println("Hilo principal terminado.");
    }
}
