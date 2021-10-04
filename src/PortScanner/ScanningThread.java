package PortScanner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ScanningThread implements Runnable{

    String host;
    ArrayList<Integer> ports;

    ScanningThread(String h, ArrayList<Integer> portList) {
        host = h;
        ports = portList;
    }

    @Override
    public void run() {

        Writer writer = new Writer();

        for (Integer port : ports) {
            try {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);

                Socket socket = new Socket();
                socket.connect(inetSocketAddress, 50);
                socket.close();

                writer.writeFile("host:" + host + " \t" + "port:"+ port + " is open ✓" + '\n');
            } catch (IOException ex) {

            }
        }
    }
}
