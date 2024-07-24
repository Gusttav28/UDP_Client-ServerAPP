package consoleapp_udp_servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author gustavocamacho
 */
public class ConsoleApp_UDP_Servidor {
    public static void main(String[] args) {
        boolean fin = false;

        try {
            DatagramSocket socket = new DatagramSocket(7000);
            byte[] mensaje_bytes = new byte[200];
            String mensaje = new String(mensaje_bytes);
            String mensajeComp = "";;

            DatagramPacket paquete = new DatagramPacket(mensaje_bytes, 200);
            DatagramPacket evnpaquete = new DatagramPacket(mensaje_bytes, 200);

            int puerto;
            InetAddress address;
            byte[] mensaje2_bytes = new byte[200];
            System.out.println("Servidor listo, esperando al cliente");
            do {
                socket.receive(paquete);
                mensaje = new String(mensaje_bytes);
                System.out.println(mensaje);
                puerto = paquete.getPort();
                address = paquete.getAddress();
                mensajeComp = "";
                if(mensaje.startsWith("fin")){
                    mensajeComp = "Chauuu cliente";
                }
                if (mensaje.startsWith("hola")){
                    mensajeComp = "hola cliente";
                }
                if(mensaje.startsWith("comoestas?")){
                    mensajeComp = "Bien y tu?";
                }

                mensaje2_bytes = mensajeComp.getBytes();
                evnpaquete = new DatagramPacket(mensaje2_bytes, mensajeComp.length(), address, puerto);
                socket.send(evnpaquete);
            } while (1 > 0);
        } catch (Exception e) {
            System.out.println("Error en el servidor: "+ e.getMessage());
        }
    }
    
}
