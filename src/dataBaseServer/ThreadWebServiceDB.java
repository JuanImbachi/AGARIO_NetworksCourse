package dataBaseServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.net.ssl.SSLSocket;

import server.ThreadWaitingClients;

public class ThreadWebServiceDB extends Thread{

	private DataBaseServer serverDB;

	public ThreadWebServiceDB(DataBaseServer s) {
		serverDB = s;

		
	}

	@Override
	public void run() {
		try {
			while (true) {
				
				

				SSLSocket socket =(SSLSocket) serverDB.getServerSocketSSL().accept();
				
				

				DataInputStream in = new DataInputStream(
						socket.getInputStream());

				DataOutputStream out = new DataOutputStream(
						socket.getOutputStream());
				String mensaje = in.readUTF();
				if (!mensaje.equalsIgnoreCase("")) {
					String[] info = mensaje.split(",");
					String mode = info[0];

					if (mode.equals(DataBaseServer.LOGIN_DB)) {

						String email = info[1];
						String password = info[2];
						String result = serverDB.loginPlayer(email, password);
						if (result != null) {
						
							new ThreadWaitingClients(serverDB.getServer())
									.start();
							out.writeUTF(DataBaseServer.CONF_ACCESS);
							
							out.writeUTF(result);
							

						} else {
							out.writeUTF(DataBaseServer.DENIED_ACCESS);
						}

					} 

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
