package Procesos_Hilos.CooperacionEntreHilos;

import java.sql.SQLOutput;

public class Hilo implements Runnable{
    int numeroHilo;
    int cuantoIncremento;
    int numeroDeIncrementos=0;
    private EjemploContador contador;

    public Hilo (int nHilo, int cIncremento,EjemploContador conta){
        this.numeroHilo = nHilo;
        this.cuantoIncremento = cIncremento;
        this.contador = conta;
    }

    public int getnumeroDeIncrementos(){
        return this.numeroDeIncrementos;
    }

    @Override
    public void run() {
        for (int i=0; i<this.cuantoIncremento; i++){
            contador.incrementaContador();
            this.numeroDeIncrementos++;
        }
        System.out.printf("Hilo con id %s, ha hecho %d incrementos\n", this.numeroHilo, this.getnumeroDeIncrementos());
    }
}
class HilosIncrementanVariable{
    private static final int NUM_HILOS=10;
    private static final int CUENTA_MAXIMA=1000;

    public static void main(String[] args) {
        EjemploContador c = new EjemploContador();
        Thread hilos[] = new Thread[NUM_HILOS];
        for(int i=0; i<NUM_HILOS; i++){
            hilos[i] = new Thread(new Hilo(i, CUENTA_MAXIMA/NUM_HILOS, c));
            hilos[i].start();
        }

        for(int i=0; i<NUM_HILOS; i++){
            try{
                hilos[i].join();
            } catch (InterruptedException e) {
                System.out.println("Se ha producido una interrupción no deseada\n");
            }
        }
        System.out.printf("Dede el hilo principal, se han porducido un total de %d\n", c.getContador());
    }
}
