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

class Cubeta{

    int cantidad1, cantidad2, cantidad3, cantidad4;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public int getCantidad1() {
        return cantidad1;
    }
    public void setCantidad1(int cantidad1) {
        this.cantidad1 = cantidad1;
    }public int getCantidad2() {
        return cantidad2;
    }
    public void setCantidad2(int cantidad2) {
        this.cantidad2 = cantidad2;
    }

    public void llenar1(){
        System.out.println("Soy la persona con nombre " + Thread.currentThread().getName() + " y estoy llenando la cubeta 1");

        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " Accediendo al recurso y bloqueando.");
            cantidad1 = cantidad1+10;
            System.out.println(Thread.currentThread().getName() + " Deja y desbloquea el recurso.");
        }
    }public void llenar2() {
        System.out.println("Soy la persona con nombre " + Thread.currentThread().getName() + " y estoy llenando la cubeta 2");

        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " Accediendo al recurso y bloqueando.");
            cantidad2 = cantidad2 + 10;
            System.out.println(Thread.currentThread().getName() + " Deja y desbloquea el recurso.");
        }
    }
}// Fin de la clase Cubeta.
class Persona extends Thread{

    private Cubeta object;
    int idPersona;

    public Persona(Cubeta cubeta, int idPersona){
        this.object = cubeta;
        this.idPersona = idPersona;
    }

    @Override
    public void run() {
        if(this.idPersona == 1){
            for(int i=0;i<10;i++){
                this.object.llenar1();
                try{
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
public class Problema2 {
    public static void main(String[] args) {

        Cubeta c1, c2;
        Persona persona1, persona2, persona3, persona4, persona5;

        c1 = new Cubeta();
        c2 = new Cubeta();

        persona1 = new Persona(c1, 1);
        persona2 = new Persona(c2, 2);
        persona3 = new Persona(c2, 3);
        persona4 = new Persona(c1, 4);
        persona5 = new Persona(c1, 1);

        persona1.setName("Persona 1");
        persona2.setName("Persona 2");
        persona3.setName("Persona 3");
        persona4.setName("Persona 4");
        persona5.setName("Persona 5");

        persona1.start();
        persona2.start();
        persona3.start();
        persona4.start();
        persona5.start();

        try{
            persona1.join();
            persona2.join();
            persona3.join();
            persona4.join();
            persona5.join();
        }catch(InterruptedException e){
            System.out.println("Se ha interrumpido la ejecución de nuestro programa de llenado de cubetas.");
        }

        System.out.printf("La cubeta tiene una capacidad de %d%n",c1.getCantidad1());
        System.out.printf("La cubeta tiene una capacidad de %d%n",c2.getCantidad2());
    }
}
