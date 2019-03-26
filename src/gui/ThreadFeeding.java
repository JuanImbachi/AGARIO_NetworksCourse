package gui;

import server.Server;

public class ThreadFeeding extends Thread{

    private static final int TIME_FEED = 1000;  

    private Server server;
	public ThreadFeeding(Server server) {
		
		this.server = server;
	}

	public void run() {
		while(true) {
			try {
				server.createFood();
				Thread.sleep(TIME_FEED);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
