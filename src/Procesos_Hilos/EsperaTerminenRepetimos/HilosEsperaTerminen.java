package Procesos_Hilos.EsperaTerminenRepetimos;

import java.util.Random;

import static java.lang.Math.random;

public class HilosEsperaTerminen extends Thread{

    private String name;
    public HilosEsperaTerminen(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.printf("Hola soy el hilo %s y me estoy ejecutando ahora mismo.\n", this.name);
        Random random = new Random();
        for(int i=0; i<5; i++){
            int pausa = 10 + random.nextInt(500-10);
            try{
                Thread.sleep(pausa);
                System.out.printf("El hilo %s se ha ido a descansar por unos %d milisegundos.\n", this.name, pausa);
            }catch(Exception ex){
                System.out.printf("El hilo %s ha sido interrumpido.", this.name);
            }
        }
        System.out.printf("El hilo %s ha terminado.\n", this.name);
    }

    public static void main(String[] args) {
        Thread h1 = new HilosEsperaTerminen("Adrian");
        Thread h2 = new HilosEsperaTerminen("Gabriel");
        h1.start();
        h2.start();
        try{
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Soy el hilo padre y acabo de terminar ya que mis crios ya han terminado.");
    }
}
