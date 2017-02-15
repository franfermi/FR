
package chat_cliente.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Francisco Fernández Millán
 */
public class Chat_ClienteServidor {
	private ServerSocket sServidor; //Socket del servidor
	private Socket sCliente; //Socket para el cliente
	private Scanner in; //Flujo de Entrada para envio de datos
	private PrintStream out; //Flujo de Salida para recepcion de datos
	private final int puerto; // Puerto de escucha del servidor
        private String enviar_sms = ""; //Almacenará los mensajes a enviar
        private String recibir_sms = ""; //Almacenará los mensajes que serán recibidos
        private final javax.swing.Timer time; //Gestionará el tiempo de la comunicación
	
        public Chat_ClienteServidor(int p){
            this.puerto=p;
            time = new javax.swing.Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(sCliente.isConnected() && !recibir_sms.equals("adios")){
                        recibir_sms =  in.next();
                        System.out.println("-Cliente: " + recibir_sms);
                    }
                }
            });
        }

	public void iniciar(){
		try{
			//Se Crea el socket del servidor 
			sServidor=new ServerSocket(puerto);
			System.out.println(" *_* SERVIDOR INICIADO *_*");
			System.out.println(" -- Esperando Cliente --");
			/*
			El metodo accept(), espera hasta que un cliente realice 
			una conexión.
			*/
			sCliente=sServidor.accept();

			/*
			Flujos de datos de in y out del socket cliente.
			*/
			in=new Scanner(sCliente.getInputStream());
			out=new PrintStream(sCliente.getOutputStream());

			System.out.println("Cliente Conectado: "+sCliente.getInetAddress()+":"+sCliente.getPort());
			out.print("Ingrese Su Nombre -> ");
			String nombre= in.next();
			out.println("Bienvenido "+ nombre);
                        out.print("Chat Iniciado...('adios' para salir del chat)\n");
                        
                        //Iniciamos time
                        time.start();
                        Scanner lectura =  new Scanner(System.in);
                        
                        while(!recibir_sms.equals("adios")){
                            enviar_sms = lectura.next();
                            out.println("-Servidor: " + enviar_sms);
                        }

			//Cerramos la conexión
			finalizar();
		}catch(Exception e){
                        System.out.println("No se ha podido iniciar correctamente");
                        finalizar();
		}
	}

	public void finalizar(){
		try{
                        time.stop();
			in.close();
			out.close();
			sCliente.close();
			sServidor.close();
			System.out.println("Conexión Finalizada...");
		}catch(IOException e){
                    System.out.println("No se ha podido finalizar correctamente");
		}
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Chat_ClienteServidor x = new Chat_ClienteServidor(8888);
	x.iniciar();
    }
    
}
