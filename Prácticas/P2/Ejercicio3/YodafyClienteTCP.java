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
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteTCP {

	public static void main(String[] args) {
		// Cambiamos el tipo de dato byte por String.
		String buferEnvio;
		String buferRecepcion;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión TCP
		Socket socketServicio=null;
		
		try {
			// Creamos un socket que se conecte a "hist" y "port":
			//////////////////////////////////////////////////////
			// socketServicio= ... (Completar)
            socketServicio = new Socket(host, port);
			//////////////////////////////////////////////////////			
			
			InputStream inputStream = socketServicio.getInputStream();
			OutputStream outputStream = socketServicio.getOutputStream();
			
			// Eliminamos el metodo getBytes(), ya que ahora el tipo de dato es String.
			buferEnvio="Al monte del volcán debes ir sin demora";
			
			// Enviamos el buferEnvio usando PrintWriter
			//////////////////////////////////////////////////////
            PrintWriter outPrinter = new PrintWriter(outputStream, true);
            outPrinter.println(buferEnvio);
			//////////////////////////////////////////////////////
			
			//////////////////////////////////////////////////////
			// ... .flush(); (Completar)
            outPrinter.flush();
			//////////////////////////////////////////////////////
			
			// Leemos la respuesta del servidor usando para ello un objeto
			//BufferedReader.
			//////////////////////////////////////////////////////
            BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
            buferRecepcion = inReader.readLine();
			//////////////////////////////////////////////////////
			
			// Mostremos el String recibido:
			System.out.println("Recibido: " + buferRecepcion + "\n");
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			// ... close(); (Completar)
            socketServicio.close();
			//////////////////////////////////////////////////////
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
