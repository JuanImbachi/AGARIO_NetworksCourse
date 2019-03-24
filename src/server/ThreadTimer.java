package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadTimer extends Thread {

	private Server server;

	private int seconds;

	

	public ThreadTimer(Server s) throws IOException {
		server = s;
		
	}

	@Override
	public void run() {

		while (server.isWaitingClients()) {

			seconds++;

			
			
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
