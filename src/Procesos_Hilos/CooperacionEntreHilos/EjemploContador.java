package Procesos_Hilos.CooperacionEntreHilos;

public class EjemploContador {
    private int conta = 0;
    synchronized public void incrementaContador(){
        this.conta++;
    }
    synchronized public int getContador(){
        return this.conta;
    }
}


