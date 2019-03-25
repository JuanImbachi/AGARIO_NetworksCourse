package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ThreadInfoGameServer extends Thread {

	private Server server;
	
	private Socket socket;

	public ThreadInfoGameServer(Server s, Socket so) {

		server = s;
		
		socket = so;
	}

	@Override
	public void run() {
 
		try {
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			
			while(server.isRunningGame()) {
				
				out.writeUTF("welcome to server");
				System.out.println(in.readUTF());
				
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}

}
