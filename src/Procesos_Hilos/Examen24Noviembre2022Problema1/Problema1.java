package Procesos_Hilos.Examen24Noviembre2022Problema1;

/*

                                        PROBLEMA DE INTERBLOQUEO.
         Se trata de dos mecanicos que estan trabajando en el taller con dos herramientas. (Clase Herramientas).

         LLega un momento que mecanico1 coge la llave inglesa y necesita carraca que la tiene en ese momento mecanico 2
         que a su vez necesita la llave inglesa.

         Programar una clase para que se produzca interbloqueo y documentarlo con pantallazo de la ejecución y
         programar otra clase solucionando el problema del interbloqueo.

*/

class Herramientas{
    Object llaveInglesa = new Object();
    Object carraca = new Object();

    public void tenerLlaveInglesayPedirCarraca(){
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
    public void tenerCarracayPedirLlaveInglesa(){
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
class Hilo implements Runnable{
    Herramientas herramienta;
    int numHilo;
    public Hilo(Herramientas herramienta, int numHilo) {
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
public class Problema1 {
    public static void main(String[] args) {
        System.out.println("Aqui los dos mecanicos intentan acceder a las herramientas y hay un interbloqueo.");
        System.out.println("Con lo cual, nunca accede ninguno a ninguna otra herramienta ya que siempre están" +
                "esperando");
        Herramientas herramienta = new Herramientas();
        Thread hilo1 = new Thread(new Hilo(herramienta, 1));
        Thread hilo2 = new Thread(new Hilo(herramienta, 2));

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
