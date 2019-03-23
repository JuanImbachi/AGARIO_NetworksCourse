package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadTimer extends Thread {

	private Server server;

	private int seconds;

	private ServerSocket SsocketInfo;

	public ThreadTimer(Server s) throws IOException {
		server = s;
		SsocketInfo = new ServerSocket(Server.PORT_WR);
	}

	@Override
	public void run() {

		while (server.isWaitingClients()) {

			seconds++;

			try {
				System.out.println("entra timer");
				Socket socketInfo = SsocketInfo.accept();
				DataOutputStream outInfo = new DataOutputStream(
						socketInfo.getOutputStream());

				String clients = new String();
				ArrayList<String> clientsArray = server.getPlayers();
				for (int i = 0; i < clientsArray.size(); i++) {
					if (i == clientsArray.size() - 1) {
						clients += clientsArray.get(i);
					} else {
						String m = clientsArray.get(i) + ",";
						clients += m;
					}
				}
				outInfo.writeUTF(server.getTimer().getSeconds() + "," + clients);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			if (seconds < 120 && server.getNumberOfClients() < 5) {
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				server.setWaitingClients(false);
			}

		}

	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public int getSeconds() {
		return seconds;
	}

}
