package Procesos_Procesos.ProcessBuilder;

import java.io.File;
import java.io.IOException;

public class ProcessBuilder_Example2 {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cat", "/tmp/msg.txt");

        pb.start();
        pb.redirectOutput(new File("salidaOutput.txt"));
    }
}
