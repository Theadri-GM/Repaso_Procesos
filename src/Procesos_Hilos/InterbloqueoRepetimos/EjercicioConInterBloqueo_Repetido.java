package Procesos_Hilos.InterbloqueoRepetimos;

import java.io.IOException;

class Interbloqueo{
    Object objeto1 = new Object();
    Object objeto2 = new Object();

    public void acceso1Y2() {
        try {
            synchronized (objeto1) {
                Thread.sleep(2000);
                synchronized (objeto2){
                    System.out.println("Acceso al recurso por orden 1 y 2\n");
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void acceso2y1(){
        try{
            synchronized (objeto2) {
                Thread.sleep(2000);
                synchronized (objeto1) {
                    Thread.sleep(2000);
                    System.out.println("Acceso al recuso por orden 2 y 1\n");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}// Fin de la clase Interbloqueo.

class Hilo implements Runnable{

    private Interbloqueo bloqueo;
    private int numHilo;

    public Hilo(Interbloqueo bloqueo, int numHilo) {
        this.bloqueo = bloqueo;
        this.numHilo = numHilo;
    }

    @Override
    public void run() {
        if (this.numHilo == 1)
            bloqueo.acceso1Y2();
        else
            bloqueo.acceso2y1();
    }
} // Fin de la clase Hilo.

public class EjercicioConInterBloqueo_Repetido {
    public static void main(String[] args) {
        System.out.println("Voy a bloquear dos hilos al acceso a un recurso.");
        Interbloqueo bloqueo = new Interbloqueo();
        Thread hilo1 = new Thread(new Hilo(bloqueo, 1));
        Thread hilo2 = new Thread(new Hilo(bloqueo, 2));

        hilo1.start();
        hilo2.start();
        try{
            hilo1.join();
            hilo2.join();
            System.out.println("Este mensaje no se imprimirá nunca.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
