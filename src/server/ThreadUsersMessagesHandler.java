package server;

import java.io.DataInputStream;
import java.net.Socket;

public class ThreadUsersMessagesHandler extends Thread {
	
	private Socket socket;
	
	private Server server;

	public ThreadUsersMessagesHandler(Socket socket, Server server) {

		this.socket = socket;
		this.server = server;
	}

	
	public void run() {
		
		try {
			
			DataInputStream in;
			
			while(server.isRunningChatService()) {
				in = new DataInputStream(socket.getInputStream());
				String userMessage = in.readUTF();
				server.newMessage(userMessage);
				Thread.sleep(5);
						
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
}
