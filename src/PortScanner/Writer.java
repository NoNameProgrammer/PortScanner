package PortScanner;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public static void writeFile(String output) {

        try(FileWriter writer = new FileWriter("result of scan.txt", true))
        {
            writer.write(output);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
