package client;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ThreadInfoChatServer extends Thread{

	public Client client;
	
	public ThreadInfoChatServer(Client client) {

		this.client = client;
	}
	public void run() {
		//enviar esta info a un panel en la interfaz para mostrarle al usuario el chat
		DataInputStream in;
		Socket socket ;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		try {
			
			while(client.isChatService()) {
				socket = client.getChatSocket();
				in = new DataInputStream(socket.getInputStream());
				String serverMessage = in.readUTF();
				bw.write(serverMessage.replaceAll(";", " : ")+"\n");
				bw.flush();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
