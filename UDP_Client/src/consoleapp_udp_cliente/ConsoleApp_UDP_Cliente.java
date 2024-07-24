package consoleapp_udp_cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author gustavocamacho
 */
public class ConsoleApp_UDP_Cliente {

    public static void main(String[] args) {
        InputStreamReader  inputStreamReader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(inputStreamReader);
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket paquete;
            byte[] mensaje_bytes = new byte[10];

            String cadenaMensaje = "";
            DatagramPacket servPacket;
            byte[] RecogerServidor_bytes;

            String mensaje = "";
            System.out.println("Cliente listo, escriba: ");
            do {
                mensaje = in.readLine();
                mensaje_bytes = mensaje.getBytes();
                paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 7000);
                socket.send((paquete));
                RecogerServidor_bytes = new byte[10];

                servPacket = new DatagramPacket(RecogerServidor_bytes, 10);
                socket.receive(servPacket);

                cadenaMensaje = new String(RecogerServidor_bytes).trim();
                System.out.println(cadenaMensaje);
            } 
            while (!mensaje.startsWith("fin"));
        } 
        catch (Exception e) {
            System.err.println(" Error en cliente: "+ e.getMessage());
        }
    }
    
}