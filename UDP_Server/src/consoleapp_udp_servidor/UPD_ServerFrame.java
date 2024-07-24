package consoleapp_udp_servidor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UPD_ServerFrame extends JFrame{
    public UPD_ServerFrame(){
        serverPanel srvpanel = new serverPanel();
        this.setSize(300, 400);
        this.add(srvpanel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
class serverPanel extends JPanel{
    private JTextArea textArea;
    private JButton sendButton;
    public serverPanel(){
        setLayout(new BorderLayout());

        // //Object input Stream Reader for convert input bytes into characters
        // InputStreamReader  inputStreamReader = new InputStreamReader(textfield.getText());
        // //Object bufferedreader that provides a bufer for read characters
        // BufferedReader in = new BufferedReader(inputStreamReader);


        JLabel testlbl = new JLabel("ServertUDP");
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(300, 400));
        // textArea.setBounds(1, 10, 200, 500);
        sendButton = new JButton("Send");
        textArea.setBounds(150, 100, 300, 50);
        // this.setBackground(Color.BLACK);
        add(testlbl);
        add(textArea, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);
        
        new Thread(()->{
            try {
                //creating the socket
                DatagramSocket socket = new  DatagramSocket(7000);
                byte[] byte_messages = new byte[200];
                String message = new String(byte_messages);
                String comMessage = "";
                
                DatagramPacket pack = new DatagramPacket(byte_messages, 200);
                DatagramPacket envPacket = new DatagramPacket(byte_messages, 200);
                
                int port;
                InetAddress address;
                byte[] byte_messages2 = new byte[200];
                do {
                    //Receibing the pakage
                   socket.receive(pack); 
                   //we formated
                   message = new String(byte_messages);
                   //we show the message in the frame
                   textArea.append("\n" + message);
                   System.out.print("\n" + message);
    
                   port = pack.getPort();
                   address = pack.getAddress();
                   comMessage = "";
                   if(message.startsWith("fin")){
                        comMessage = "Bye client";
                   }
                   byte_messages2 = comMessage.getBytes();
    
                   envPacket = new DatagramPacket(byte_messages2, comMessage.length(), address, port);
                   socket.send(envPacket);
    
                } while (1 > 0); 
    
            } catch (Exception e) {
                System.err.println("Theres a error: " + e.getMessage());
            }
        }).start();

        
    }
}
