package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public final static String IP_SERVER="172.30.179.30";
	public static final int PORT = 36556;
	
	private static Socket socket;
	
	private static  ServerSocket serverSocket;
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;
		
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("::Servidor en l�nea, esperando conexi�n::");
			
			while(true) {
				
				socket = serverSocket.accept();
				
				System.out.println("Conexi�n iniciada");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				String mensajeObtenidoCliente = in.readUTF();
				System.out.println("=================" + mensajeObtenidoCliente);
				String respuestaServer = "::Bienvenido::";
				out.writeUTF(respuestaServer);
				
				socket.close();
				System.out.println("::Cliente desconectado::");
			
			}
	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
