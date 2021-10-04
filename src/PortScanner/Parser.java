package PortScanner;

import java.util.ArrayList;

public class Parser {

    public ArrayList<String> getHost(String inputText){

        int indexH = inputText.indexOf("-h");
        int indexP = inputText.indexOf("-p");
        String input = inputText.substring(indexH + 3, indexP - 1);      //getting host(s) from input string
        ArrayList<String> hostList = new ArrayList<>();

        String[] hosts = input.split(",");
        for (int i = 0; i < hosts.length; i++) {
            if (hosts[i].contains("-")) {                         //check a hosts range
                String[] octet = hosts[i].split("\\.");     //splitting ip address into octets [255].[255].[255].[255]
                String[] range = octet[3].split("-");       //.[1]-[255]
                int first = Integer.parseInt(range[0]);
                int last = Integer.parseInt(range[1]);
                while (last >= first) {                           //writing the port range to the ArrayList
                    String currentHost = String.join(".",
                            octet[0], octet[1], octet[2], Integer.toString(first));
                    hostList.add(currentHost);
                    first++;
                }
            } else
                hostList.add(hosts[i]);
        }
        return hostList;
    }

    public ArrayList<Integer> getPort(String inputText) {    //max range 0-65536

        int indexSpace;
        String input;
        ArrayList<Integer> portList = new ArrayList<>();

        String[] data = inputText.split("-p ");
        indexSpace = data[1].indexOf(" ");
        if (indexSpace != -1)                                          //if input sting contains "-t"
            input = data[1].substring(0, indexSpace);        //getting a single string with port(s)
        else
            input = data[1];
        String[] ports = input.split(",");             //getting strings with port or range of ports
        for (int i = 0; i < ports.length; i++) {
            if (ports[i].contains("-")) {                           //check a port range
                String[] range = ports[i].split("-");
                int first = Integer.parseInt(range[0]);
                int last = Integer.parseInt(range[1]);
                while (last >= first) {                              //writing the port range to the ArrayList
                    portList.add(first);
                    first++;
                }
            } else
                portList.add(Integer.parseInt(ports[i]));
        }
        return portList;
    }

    public int numberOfThread(String inputText) {

        int threads;
        String[] input = inputText.split("-t ");

        if (input.length > 1)
            threads = Integer.parseInt(input[1]);
        else
            threads = 1;
        return (threads);
    }
}
