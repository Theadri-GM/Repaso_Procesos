package Procesos_Hilos.Interbloqueo;


// Creamos la clase Interbloqueo
class Interbloqueo{
    // Esta contendrá dos objetos de la clase Object.
    private Object objeto1 = new Object();
    private Object objeto2 = new Object();
    // Crearemos un metodo ell cual le dara acceso a un objeto primero y bloqueara el objeto y despues pedirá al otro
    // objeto.
    public void accesoOrdenObjeto1Y2() {
        try {
            synchronized (objeto1) {
                Thread.sleep(2000);
                synchronized (objeto2) {
                    System.out.println("Acceso a recurso por orden 1 y 2, finalizado\n");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Despues, crearemos un metodo parecido al de antes, salvo que a este, le haremos un sleep tambien al objeto 1.
    // produciendo así un Interbloqueo.
        public void accesoOrdenObjeto2Y1(){
            try{
                synchronized (objeto2){
                    Thread.sleep(2000);
                    synchronized (objeto1){
                        Thread.sleep(2000);
                        System.out.println("Acceso a recurso por orden 2 y 1, finalizado\n");
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    } // Fin clase Interbloqueo.
// Ahora, creamos nuestra clase Hilo que siempre implementará de Runnable o extenderá de Thread.
class Hilo implements Runnable {
    // Y le pasamos como atributos un objeto de la clase Interbloqueo y un idHilo.
    private Interbloqueo bloqueo;
    private int numHilo;
    // Constructor simple.
    public Hilo(Interbloqueo bloqueo, int numHilo) {
        this.bloqueo = bloqueo;
        this.numHilo = numHilo;
    }
    // Ahora, sobreescribimos el método run y le decimos...
    @Override
    public void run() {
        // Si es hilo1
        if (this.numHilo==1)
            bloqueo.accesoOrdenObjeto1Y2(); // Que bloquee 1 y 2.
        // Que no es hilo1
        else
            bloqueo.accesoOrdenObjeto2Y1(); // Que bloquee 2 y 1.
    }
    // En este método hacemos nuestro interbloqueo. Porque el hilo 1 va a bloquear el acceso al objeto1 y después al 2.
    // Pero es que el el hilo 2 va a bloquear el acceso al objeto 2 y despues al 1. Así, cuando ambos terminen,
    // se quedarán esperando a que el otro termine pero nunca van a terminan porque estarán en un interbloqueo
    // constante.
} // Fin clase Hilo
// Clase donde ejecutaremos nuestro main.
public class EjercicioConInterBloqueo {
    // Main
    public static void main(String[] args) {
        System.out.println("Voy a bloquear a dos hilos al acceso a un recurso\n");
        // Creamos el objeto bloqueo.
        Interbloqueo bloqueo = new Interbloqueo();
        // Creamos nuestros hilos.
        Thread hilo1 = new Thread(new Hilo(bloqueo, 1));
        Thread hilo2 = new Thread(new Hilo(bloqueo, 2));
        // Los inicializamos.
        hilo1.start();
        hilo2.start();
        try{
            // Y les hacemos un Join.
            hilo1.join();
            hilo2.join();
            System.out.println("Este mensaje no saldrá nunca.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
