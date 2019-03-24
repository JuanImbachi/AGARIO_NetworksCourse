package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadWaitingClients extends Thread {

	private Server server;
	
	
	

	public ThreadWaitingClients(Server s) throws IOException {
		server = s;
        
		
		
	}
	
	

	@Override
	public void run() {

		while (server.isWaitingClients()) {
			try {
   
				Socket socket = server.getServerSocket().accept();
				
				
				
				
				// server.agregarSocketAActivos(socket);
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				String mensaje = in.readUTF();
				System.out.println(mensaje);
		
				if(mensaje.equals(Server.CONNECTED_CLIENT)){
					
					server.setNumberOfClients(server.getNumberOfClients()+1);
					if(!server.getTimer().isAlive()){
						server.getTimer().start();	
						server.getThreadSIWR().start();
					}
					out.writeUTF(Server.CONNECTED_CLIENT);
					
					
				}
				
				
			} catch (Exception e) {
				
			}
		}
	}
	
	
	
	

}
