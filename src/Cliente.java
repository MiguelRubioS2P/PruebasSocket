import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            //Definimos el sockets, número de bytes del buffer, y mensaje.
            DatagramSocket socket = new DatagramSocket();
            byte[] mensaje_bytes;
            String mensaje="";
            InetAddress address=InetAddress.getByName("192.168.4.68");

            //Paquete
            DatagramPacket paquete;
            String cadenaMensaje="";
            DatagramPacket servPaquete;
            byte[] RecogerServidor_bytes;

            do {
                mensaje = in.readLine();
                mensaje_bytes = mensaje.getBytes();
                paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,6000);
                socket.send(paquete);

                RecogerServidor_bytes = new byte[256];

                //Esperamos a recibir un paquete
                servPaquete = new DatagramPacket(RecogerServidor_bytes,256);
                socket.receive(servPaquete);

                cadenaMensaje = new String(RecogerServidor_bytes).trim();

                //Imprimimos el paquete recibido
                System.out.println(cadenaMensaje);
            } while (!mensaje.startsWith("fin"));
            in.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
