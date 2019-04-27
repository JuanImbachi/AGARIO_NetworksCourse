package client;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadSendMessages extends Thread{

	private Client client;
	
	public ThreadSendMessages(Client client) {

		this.client = client;
	}

	public void run() {
		
		try {
			//Realizar lectura de los mensajes del usuario
			DataOutputStream out;
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			Scanner scan = new Scanner(System.in);
			String mensaje = "";
			Socket socket;
			
			while(client.isChatService()) {
				socket = client.getChatSocket();
				out = new DataOutputStream(socket.getOutputStream());
				mensaje = client.getNickname() + ";";
				mensaje += scan.nextLine();
				out.writeUTF(mensaje);
					
		}
	}
		catch(Exception e) {
			
		}
		
			
			
			
		
		
	}

}
