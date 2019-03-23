package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ThreadWaitingClients extends Thread {

	private Server server;

	public ThreadWaitingClients(Server s) {
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
					server.getTimer().start();	
					server.setNumberOfClients(server.getNumberOfClients()+1);
					
				}
				
				out.writeUTF(Server.CONNECTED_CLIENT);
			} catch (Exception e) {
				
			}
		}
	}

}
