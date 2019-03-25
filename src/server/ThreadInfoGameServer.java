package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ThreadInfoGameServer extends Thread {

	private Server server;
	

	public ThreadInfoGameServer(Server s) {

		server = s;
		
	}

	@Override
	public void run() {
 
		try {
			
			Socket socket = server.getServerSocketGame().accept();
			
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
