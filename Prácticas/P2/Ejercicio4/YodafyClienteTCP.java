//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteTCP {

	public static void main(String[] args) {
		
		byte []buferEnvio;
		byte []buferRecepcion=new byte[256];
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		DatagramSocket socket = null;
                DatagramPacket paquete = null;
                DatagramPacket paqueteModificado = null;
                InetAddress direccion = null;
                
                String fraseYodificada;
                
		
		
		try {
			
                        socket = new DatagramSocket();
			
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
                
                try {
			
                        direccion = InetAddress.getByName(host);
			
		} catch (UnknownHostException e) {
			System.err.println("Error al recuperar la direccion.");
		}
                
                buferEnvio = "Al monte del volcan debes ir sin demora".getBytes();
                
                try {
			paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
                        socket.send(paquete);
                        
                        paqueteModificado = new DatagramPacket(buferRecepcion, buferRecepcion.length);
			socket.receive(paqueteModificado);
                        
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
                
                fraseYodificada = new String(paqueteModificado.getData());
                
                System.out.println("\nRecibido: " + fraseYodificada + "\n");
                socket.close();
        
        }
}
