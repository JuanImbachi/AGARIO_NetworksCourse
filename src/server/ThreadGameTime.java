package server;

public class ThreadGameTime extends Thread {

	private Server server;
	private int seconds;

	public ThreadGameTime(Server server) {

		this.server = server;
		seconds = 0;
	}

	@Override
	public void run() {
		while (seconds < 300 && server.getGame().numberOfPlayingClients() > 0) {

			
			
			seconds++;
			

			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
//				e.printStackTrace();
			}

		}
		
		

		
		server.stopGame();
	}
}
