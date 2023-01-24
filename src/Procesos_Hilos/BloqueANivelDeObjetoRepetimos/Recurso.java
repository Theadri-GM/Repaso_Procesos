package Procesos_Hilos.BloqueANivelDeObjetoRepetimos;

public class Recurso {
    public void bloquear(){
        System.out.println("Soy el hilo " + Thread.currentThread().getName() + "\n");
        synchronized (this){
            System.out.println("Hilo " + Thread.currentThread().getName() + " toma el recurso.");
            for (int i=0; i<1000000; i++);
            System.out.println("Hilo " + Thread.currentThread().getName() + " deja el recurso y se va.");
        }
    }
}

class Hilo extends Thread{

    Recurso recurso;

    public Hilo (Recurso recurso){
        this.recurso = recurso;
    }

    @Override
    public void run() {
        this.recurso.bloquear();
    }
}

class MetodosDeInterbloqueos {
    public static void main(String[] args) {
        Recurso r1, r2;
        Hilo h1, h2, h3, h4;

        r1 = new Recurso();
        r2 = new Recurso();

        h1 = new Hilo(r1);
        h2 = new Hilo(r2);
        h3 = new Hilo(r1);
        h4 = new Hilo(r1);

        h3.setName("Hilo 3");
        h4.setName("Hilo 4");
        h2.setName("Hilo 2");
        h1.setName("Hilo 1");

        h1.start();
        h2.start();
        h3.start();
        h4.start();
    }
}