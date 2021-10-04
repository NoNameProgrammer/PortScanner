import PortScanner.*;

public class Main {

    public static void main(String[] args){

        String inputText = "scan -h 81.19.82.91,81.19.82.98-120 -p 43-45,80 -t 15";
        PortScanner scanner = new PortScanner();
        scanner.scan(inputText);
    }
}
