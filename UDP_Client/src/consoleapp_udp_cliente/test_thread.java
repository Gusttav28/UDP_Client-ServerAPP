package consoleapp_udp_cliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane;

public class test_thread extends Thread {
    private boolean running;
    String [] coordinatess= {"Coordinate_1","Coordinate_2","Coordinate_3","Coordinate_4","Coordinate_5"};
    int [] num_coordinates = {1, 2, 3, 4}; 
    String coordinate1 = coordinatess[0];
    String coordinate2 = coordinatess[1];
    String coordinate3 = coordinatess[2];
    String coordinate4 = coordinatess[3];

    public void run(){
        int runningfirstcoordinate = 0;
        int running2coordinate = 0;
        int running3coordinate = 0;
        int count = 0;
        while (runningfirstcoordinate <= 10){       
            try {
                System.out.println("this is a test");
                Thread.sleep(500);

                DatagramSocket socket = new DatagramSocket();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet;
                byte[] byteMessage = new byte[200];
                String stringmessage = "";
                byte[] pickUpBytes_Server;   
                // for(String message: coordinates){
                //     // String message = i;
                //     System.out.println(message);
                //     do {
                //         byteMessage = message.getBytes();
                //         packet =  new DatagramPacket(byteMessage, message.length(), address, 7000);
                //         socket.send(packet);
                //         pickUpBytes_Server = new byte[200];
                //         DatagramPacket servPacket = new DatagramPacket(pickUpBytes_Server, 200);
                //         socket.receive(servPacket);
                //         stringmessage = new String(pickUpBytes_Server).trim();
                //         System.out.println(stringmessage);
                //         running++;
                //         socket.close();
                //     } while (running <= 20);
                // }
                String message = "Coordinate 1";
                
                do {
                    byteMessage = message.getBytes();
                    packet =  new DatagramPacket(byteMessage, message.length(), address, 7000);
                    socket.send(packet);

                    pickUpBytes_Server = new byte[200];
                    DatagramPacket servPacket = new DatagramPacket(pickUpBytes_Server, 200);
                    socket.receive(servPacket);
                    stringmessage = new String(pickUpBytes_Server).trim();
                    System.out.println(stringmessage);
                    runningfirstcoordinate++;
                    // message = "test2";


                    if (runningfirstcoordinate == 10){
                        message = "Coordinate 2";
                        do {
                            Thread.sleep(500);
                            byteMessage = message.getBytes();
                            packet =  new DatagramPacket(byteMessage, message.length(), address, 7000);
                            socket.send(packet);

                            pickUpBytes_Server = new byte[200];
                            DatagramPacket servPacket2 = new DatagramPacket(pickUpBytes_Server, 200);
                            socket.receive(servPacket2);
                            stringmessage = new String(pickUpBytes_Server).trim();
                            System.out.println(stringmessage);
                            running2coordinate++;
                            

                            if (running2coordinate == 10){
                                message = "Coordinate 3";
                                do {
                                    Thread.sleep(500);
                                    byteMessage = message.getBytes();
                                    packet =  new DatagramPacket(byteMessage, message.length(), address, 7000);
                                    socket.send(packet);

                                    pickUpBytes_Server = new byte[200];
                                    DatagramPacket servPacket3 = new DatagramPacket(pickUpBytes_Server, 200);
                                    socket.receive(servPacket3);
                                    stringmessage = new String(pickUpBytes_Server).trim();
                                    System.out.println(stringmessage);
                                    running3coordinate++;
                                } while (running3coordinate <= 10);
                            }
                        } while (running2coordinate <= 10);
                        }
                    socket.close();
                } while (runningfirstcoordinate <= 10);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            // if (running == 10){
            //     break;
            // }
        }
        
    }

    // public static void main(String[] args) {
    //     test_thread thread1 = new test_thread();
    //     thread1.start();

    //     try {
    //         Thread.sleep(5000);

    //     } catch (Exception e) {
    //         Thread.currentThread().interrupt();
    //     }
    //     thread1.stopThread();
    //     System.out.println("The thread has stop it");
    // }

    // public void run2(){
    //     int runningfirstcoordinate = 0;
    //     int running2coordinate = 0;
    //     try {
    //         DatagramSocket socket = new DatagramSocket();
    //         InetAddress address = InetAddress.getByName("localhost");
    //         DatagramPacket packet;
    //         byte[] byteMessage = new byte[200];
    //         String stringmessage = "";
    //         byte[] pickUpBytes_Server;   
            
    //        while (runningfirstcoordinate <= 10) {
    //             Thread.sleep(500);
    //             String message = "test_coordinate1";
    //             byteMessage = message.getBytes();
    //             packet =  new DatagramPacket(byteMessage, message.length(), address, 7000);
    //             socket.send(packet);

    //             pickUpBytes_Server = new byte[200];
    //             DatagramPacket servPacket = new DatagramPacket(pickUpBytes_Server, 200);
    //             socket.receive(servPacket);
    //             stringmessage = new String(pickUpBytes_Server).trim();
    //             System.out.println(stringmessage);
    //             runningfirstcoordinate++; 

    //             if(runningfirstcoordinate == 10){
    //                 while (running2coordinate <= 10) {
    //                     Thread.sleep(500);
    //                     message = "test_coordinate2";
    //                     byteMessage = message.getBytes();
    //                     packet =  new DatagramPacket(byteMessage, message.length(), address, 7000);
    //                     socket.send(packet);
        
    //                     pickUpBytes_Server = new byte[200];
    //                     DatagramPacket servPacket2 = new DatagramPacket(pickUpBytes_Server, 200);
    //                     socket.receive(servPacket2);
    //                     stringmessage = new String(pickUpBytes_Server).trim();
    //                     System.out.println(stringmessage);
    //                     running2coordinate++; 
        
    //                 }
    //             }
    //         }
    //         socket.close();
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //     }
    // }

}
