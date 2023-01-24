package Procesos_Hilos.Examen24Noviembre2022Problema1;

class Herramientass{
    Object llaveInglesa = new Object();
    Object carraca = new Object();

    public synchronized void tenerLlaveInglesayPedirCarraca(){
        try{
            synchronized (llaveInglesa){
                Thread.sleep(2000);
                synchronized (carraca){
                    System.out.println("Acceso por orden a la llave Inglesa y a la carraca finalizado.\n");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void tenerCarracayPedirLlaveInglesa(){
        try{
            synchronized (carraca){
                Thread.sleep(2000);
                synchronized (llaveInglesa){
                    Thread.sleep(2000);
                    System.out.println("Acceso por orden a la carraca y a la llave inglesa finalizado.");
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}// Fin de la clase Herramientas
class Hiloo implements Runnable{
    Herramientass herramienta;
    int numHilo;
    public Hiloo(Herramientass herramienta, int numHilo) {
        this.herramienta = herramienta;
        this.numHilo = numHilo;
    }
    @Override
    public void run() {
        if (numHilo == 1)
            herramienta.tenerLlaveInglesayPedirCarraca();
        else
            herramienta.tenerCarracayPedirLlaveInglesa();
    }
}// Fin de la clase Hilo
public class Problema1SinInterbloqueo {
    public static void main(String[] args) {
        System.out.println("Aqui los dos mecanicos intentan acceder a las herramientas y hay un interbloqueo.");
        System.out.println("Con lo cual, nunca accede ninguno a ninguna otra herramienta ya que siempre están" +
                "esperando");
        Herramientass herramienta = new Herramientass();
        Thread hilo1 = new Thread(new Hiloo(herramienta, 1));
        Thread hilo2 = new Thread(new Hiloo(herramienta, 2));

        hilo1.start();
        hilo2.start();

        try{
            hilo1.join();
            hilo2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
