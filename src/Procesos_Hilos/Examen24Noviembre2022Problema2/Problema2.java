package Procesos_Hilos.Examen24Noviembre2022Problema2;
/*
                                        PROBLEMA DE BLOQUEO A NIVEL DE CÓDIGO.
           Problema de bloqueo a nivel de código con objetos Object.
           Implementar un porgrama que lleve el control de dos cubetas de agua, la idea es que 5 personas tienen que
           llenar las dos cubetas. Las personas 1, 3 y 5 llenan la cubeta numero 1 y las personas 2 y 4 llenan la cubeta
           numero 2.

           Cada persona hace 10 llenados de 10 litros cada vez. La cantidad de cada cubeta es 300L la primera y de
           200L la segunda.

 */

// Creamos el objeto Cubeta.
class Cubeta{
    // Le pasamos los atributos de la cubeta.
    int cantidad1, cantidad2;
    // Unos de ellos van a ser los dos objetos que vamos a usar.
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    // Implementamos getters & setters
    public int getCantidad1() {
        return cantidad1;
    }
    public void setCantidad1(int cantidad1){
            this.cantidad1 = cantidad1;
    }
    public int getCantidad2() {
        return cantidad2;
    }
    public void setCantidad2(int cantidad2) {
        this.cantidad2 = cantidad2;
    }
    // Creamos el método llenar1, al cuál llamarán los objetos que vayan a llenar la cubeta 1.
    public void llenar1(){
        System.out.println("Soy la persona con nombre " + Thread.currentThread().getName() + " y estoy llenando la cubeta 1");
        // Hacemos el método el cual el objeto lock1, hará que los recursos se llenen.
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " Accediendo al recurso y bloqueando.");
            cantidad1 = cantidad1+10;
            System.out.println(Thread.currentThread().getName() + " Deja y desbloquea el recurso.");
        }
    }public void llenar2() {
        System.out.println("Soy la persona con nombre " + Thread.currentThread().getName() + " y estoy llenando la cubeta 2");
        // Hacemos el método el cual el objeto lock1, hará que los recursos se llenen.
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + " Accediendo al recurso y bloqueando.");
            cantidad2 = cantidad2 + 10;
            System.out.println(Thread.currentThread().getName() + " Deja y desbloquea el recurso.");
        }
    }
}// Fin de la clase Cubeta.
// Creamos la clase Persona que serán nuestros hilos.
class Persona extends Thread{
    // Le pasamos como atributos objeto de la clase Cubeta.
    private Cubeta object;
    // Y un entero que hará referencia al id de la persona.
    int idPersona;
    // Constructor normal y corriente.
    public Persona(Cubeta cubeta, int idPersona){
        this.object = cubeta;
        this.idPersona = idPersona;
    }
    // Sobreescribimos el método run de nuestro hilo.
    @Override
    public void run() {
        // Si el id de persona es el id 1 entonces...
        if(this.idPersona == 1){
            // va a llenar 10 veces...
            for(int i=0;i<10;i++){
                this.object.llenar1();
                try{
                    // y va a dormir 2 segundos.
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println("Se ha interrumpido el llenado de la persona 1");
                }
            }
        }if(this.idPersona == 2){
            for(int i=0;i<10;i++){
                this.object.llenar2();
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println("Se ha interrumpido el llenado de la persona 2");
                }
            }
        }if(this.idPersona == 3){
            for(int i=0;i<10;i++){
                this.object.llenar2();
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println("Se ha interrumpido el llenado de la persona 3");
                }
            }
        }if(this.idPersona == 4){
            for(int i=0;i<10;i++){
                this.object.llenar1();
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println("Se ha interrumpido el llenado de la persona 4");
                }
            }
        }
    }
}// Fin clase Persona.
// Creamos nuestra clase principal donde estará el main.
public class Problema2 {
    // MAIN normal y corriente.
    public static void main(String[] args) {
        // Creamos dos objetos de la clase Cubeta.
        Cubeta c1, c2;
        // Creamos 5 personas.
        Persona persona1, persona2, persona3, persona4, persona5;
        // Inicializamos nuestras cubetas.
        c1 = new Cubeta();
        c2 = new Cubeta();
        // Inicializamos nuestras personas.
        persona1 = new Persona(c1, 1);
        persona2 = new Persona(c2, 2);
        persona3 = new Persona(c2, 3);
        persona4 = new Persona(c1, 4);
        persona5 = new Persona(c1, 5);
        // Les cambiamos el nombre a las personas para saber cuales se están ejecutando
        persona1.setName("Persona 1");
        persona2.setName("Persona 2");
        persona3.setName("Persona 3");
        persona4.setName("Persona 4");
        persona5.setName("Persona 5");
        // Arrancamos nuestros hilos.
        persona1.start();
        persona2.start();
        persona3.start();
        persona4.start();
        persona5.start();

        try{
            // Y les hacemos un join().
            persona1.join();
            persona2.join();
            persona3.join();
            persona4.join();
            persona5.join();
        }catch(InterruptedException e){
            System.out.println("Se ha interrumpido la ejecución de nuestro programa de llenado de cubetas.");
        }
        // Mostramos la cantidad que contiene cada cubeta.
        System.out.printf("La cubeta tiene una capacidad de %d%n",c1.getCantidad1());
        System.out.printf("La cubeta tiene una capacidad de %d%n",c2.getCantidad2());
    }
}
