package dataBaseServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
				
				if(!mensaje.equalsIgnoreCase("")){
					String[] info = mensaje.split(",");
					String mode = info[0];
					
					if(mode.equals(DataBaseServer.LOGIN_DB)) {
						
						String email = info[1];
						String password = info[2];
						boolean result = serverDB.loginPlayer(email, password);
						if(result){
							out.writeUTF(DataBaseServer.CONF_ACCESS);
						}else{
							out.writeUTF(DataBaseServer.DENIED_ACCESS);
						}
						
					}else if(mode.equals(DataBaseServer.REGISTER_DB)) {
						
						String nick = info[1];
						String password = info[2];
						String email = info[3];
						boolean result = serverDB.registerPlayer(nick, password, email);
						if(result){
							out.writeUTF(DataBaseServer.PLAYER_SAVED);
						}else{
							out.writeUTF(DataBaseServer.PLAYER_NOTSAVED);
						}
						
					}
					
				}
				
				
			} catch (Exception e) {
				
				
			}
		}
	}

}
