package dataBaseServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import server.Server;

public class ThreadWaitingClientsDB extends Thread {

	private DataBaseServer serverDB;
	
	

	public ThreadWaitingClientsDB(DataBaseServer s) {
		serverDB = s;

	}

	@Override
	public void run() {

		while (serverDB.isWaitingClients()) {
			try {
   
				Socket socket = serverDB.getServerSocket().accept();
				
				// server.agregarSocketAActivos(socket);
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				String mensaje = in.readUTF();
				System.out.println(mensaje);
		
				if(!mensaje.equalsIgnoreCase("")){
					String[] info = mensaje.split(",");
					String mode = info[0];
					
					if(mode.equals(DataBaseServer.LOGIN_DB)) {
						
						String nick = info[1];
						String password = info[2];
						serverDB.loginPlayer(nick, password);
						
					}else if(mode.equals(DataBaseServer.REGISTER_DB)) {
						
						String nick = info[1];
						String password = info[2];
						String email = info[3];
						serverDB.registerPlayer(nick, password, email);
						out.writeUTF("Player was saved!");
						
					}
					
				}
				
				out.writeUTF("CONFIRMADO");
			} catch (Exception e) {
				
			}
		}
	}

}
