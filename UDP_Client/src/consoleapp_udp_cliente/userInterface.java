package consoleapp_udp_cliente;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class userInterface extends JFrame {
    
    public userInterface(){
        clientPanel clPanel = new clientPanel();
        this.setSize(300, 400);
        this.add(clPanel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class clientPanel extends JPanel{
    private JTextField textfield;
    private JButton sendButton;
    public clientPanel(){

        // //Object input Stream Reader for convert input bytes into characters
        // InputStreamReader  inputStreamReader = new InputStreamReader(textfield.getText());
        // //Object bufferedreader that provides a bufer for read characters
        // BufferedReader in = new BufferedReader(inputStreamReader);


        JLabel testlbl = new JLabel("ClientUDP - Sever sending...");
        textfield = new JTextField(20);
        sendButton = new JButton("Send");
        textfield.setBounds(150, 100, 300, 50);
        // this.setBackground(Color.BLACK);
        add(testlbl);
        add(textfield);
        add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                test_thread thread1 = new test_thread();
                thread1.start();
                
                try {
                    Thread.sleep(50);
                    System.out.println("This is a: " + thread1);
                } catch (Exception ex) {
                    System.out.println("this is the error: " + ex);
                }
                // new Thread(()->{
                //     int count = 5;

                //     do {
                //         thread1.start();
                //         count++;
                //     } while (count <= 5);
                // }).start();
                
            }
        });

        
        textfield.addKeyListener(new KeyListener(){
            public void keyReleased(KeyEvent e){
    
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        try {
                            //we need to define the sockects, number of bytes of the buffer and the message
                            DatagramSocket socket = new DatagramSocket();
                            InetAddress address = InetAddress.getByName("localhost");
                            DatagramPacket packet; //the message/thing that we send through the socket
                            byte[] byteMessage = new byte[200];

                            //package - response of the server
                            String stringmessage = "";
                            DatagramPacket servPacket; //what we receive through the socket
                            byte[] pickUpBytes_Server; //= new byte[10]

                            String message = "Hello";

                            System.out.println("Server ready: ");
                            String textfieldmessage;
                            do {
                                textfieldmessage = textfield.getText();
                                // int count = 0;
                                // while (count >= 5){
                                //     byteMessage = message.getBytes(); //The message that we have, we converted to bytes
                                //     //we are creating here the pacage that we are gonna send
                                //     packet = new DatagramPacket(byteMessage, textfieldmessage.length(), address, 7000);
                                //     socket.send(packet);

                                //     pickUpBytes_Server = new byte[200];
                                //     //We wait for receive a package 
                                //     servPacket = new DatagramPacket(pickUpBytes_Server, 200);
                                //     socket.receive(servPacket);
                                //     //we convert the message in a string
                                //     stringmessage = new String(pickUpBytes_Server).trim();
                                //     //and than we show that message for the terminal
                                //     System.out.println(stringmessage);
                                //     System.out.println(message);
                                //     // textfield.setText("");
                                //     count++;
                                //     if(count == 5){
                                //         socket.close();
                                //         JOptionPane.showMessageDialog(null, "finalizo el bucle");
                                    
                                //     }
                                    
                                // }
                                byteMessage = textfieldmessage.getBytes();
                                packet = new DatagramPacket(byteMessage, textfieldmessage.length(), address, 7000);
                                socket.send(packet);

                                pickUpBytes_Server = new byte[200];
                                servPacket = new DatagramPacket(pickUpBytes_Server, 200);
                                socket.receive(servPacket);
                                stringmessage = new String(pickUpBytes_Server).trim();
                                JOptionPane.showMessageDialog(null, stringmessage);
                                System.out.println(stringmessage);
                                // textfield.setText("");
                                socket.close();
                            } 
                            while (!textfieldmessage.startsWith("end"));
                        } catch (Exception et) {
                            System.err.println("theres a problem with the server: " + et.getMessage());
                        }

                    }
                } catch (Exception ex) {
                    System.err.println("theres a problem with the button: " + ex.getMessage());
                }
            }
        });
    }

}