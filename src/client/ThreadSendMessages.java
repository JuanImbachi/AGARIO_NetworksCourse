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

			String mensaje = "";
			Socket socket;
			
			while(client.isChatService()) {
				if(client.getUserMessages().size()>0) {
					socket = client.getChatSocket();
					out = new DataOutputStream(socket.getOutputStream());
					mensaje = client.getNickname() + ";";
					
					for (int i = 0; i < client.getUserMessages().size(); i++) {
						mensaje = client.getNickname() + ";";
						mensaje += client.getUserMessages().get(i);
						out.writeUTF(mensaje);
					}
					client.eraseMessages();
				}		
		}
	}
		catch(Exception e) {
			
		}
		
			
			
			
		
		
	}

}
