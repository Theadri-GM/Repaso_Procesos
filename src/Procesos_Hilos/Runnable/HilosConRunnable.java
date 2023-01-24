package Procesos_Hilos.Runnable;

public class HilosConRunnable implements Runnable{
    private final String nombre;

    HilosConRunnable(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Hola soy el hilo con "+ this.nombre + ".");
        System.out.println("Hilo " + this.nombre + " terminado.");
    }

    public static class LanzaHilos{
        public static void main(String[] args) {
            Thread h1 = new Thread(new HilosConRunnable("H1 de Adrian"));
            Thread h2 = new Thread(new HilosConRunnable("H2 de Gabriel"));
            h1.start();
            h2.start();
            System.out.println("Hilo pirncipal terminado. Mis crios han terminado.");
        }
    }
}
