package Procesos_Hilos.BloqueoANivelDeObjeto;

public class Recurso {

    public void bloquear(){
        System.out.println("Soy el hilo cuyo nombre es" + Thread.currentThread().getName() + "\n");
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "Accediendo al recurso y bloqueando\n");
            for(int i=0; i<10000000; i++);
                System.out.println(Thread.currentThread().getName() + "Deja y desbloquea el recurso.\n");

        }
    }
}
class Hilo extends Thread{

    private Recurso objeto;

    Hilo (Recurso objeto){
        this.objeto = objeto;
    }

    public void run(){
        this.objeto.bloquear();
    }
}

class MetodosDeInterbloqueos{
    public static void main(String[] args) {

        Recurso r1, r2, r3, r4;
        Hilo h1, h2, h3, h4;

        r1 = new Recurso();
        r2 = new Recurso();

        h1 = new Hilo(r1);
        h2 = new Hilo(r1);
        h3 = new Hilo(r2);
        h4 = new Hilo(r1);

        h1.setName("Hilo 1");
        h2.setName("Hilo 2");
        h3.setName("Hilo 3");
        h4.setName("Hilo 4");

        h1.start();
        h2.start();
        h3.start();
        h4.start();
    }
}
