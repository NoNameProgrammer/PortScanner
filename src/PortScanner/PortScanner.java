package PortScanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortScanner {

    public String getDate() {

        LocalDateTime date =  LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return (date.format(formatter));
    }

    public void scan(String inputText) {

        Parser parser = new Parser();
        ArrayList<String> hostList = parser.getHost(inputText);
        ArrayList<Integer> portList = parser.getPort(inputText);

        Writer writer = new Writer();
        writer.writeFile( "---" + getDate() + '\n');
        Collections.shuffle(portList);

        ExecutorService thread = Executors.newFixedThreadPool(parser.numberOfThread(inputText));

        for (int i = 0; i < hostList.size(); i++) {
            thread.execute(new ScanningThread(hostList.get(i), portList));
        }
        System.out.println("Check result of scan.txt");
        thread.shutdown();
    }
}
