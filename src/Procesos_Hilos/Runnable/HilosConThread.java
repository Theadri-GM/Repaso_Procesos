package Procesos_Hilos.Runnable;

public class HilosConThread extends Thread{
    private String word;
    public HilosConThread(String s){
        word = s;
    }

    @Override
    public void run() {
        System.out.println(word);
        System.out.flush();
    }

    public static void main(String[] args) {
        Thread tP = new HilosConThread("P");
        Thread tp = new HilosConThread("p");
        tP.start();
        tp.start();

    }
}
