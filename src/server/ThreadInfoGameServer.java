package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ThreadInfoGameServer extends Thread {

	private Server server;
	
	private boolean firstSend;
	

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
				
				if(!firstSend){
					
				    out.writeUTF(server.sendInfoFirstTime());
					firstSend = true;
					
				}else{
					
					
					
				}
				
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}

}
