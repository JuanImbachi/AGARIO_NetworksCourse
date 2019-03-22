package server;

public class ThreadTimer extends Thread{
	
	private Server server;
	
	private int seconds;
	
	private int minutes;
	
	public ThreadTimer(Server s) {
		server = s;
	}
	
	
	@Override
	public void run() {
		
		while(server.isWaitingClients()) {
			
			seconds++;
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private void advanceTime() {
		
		
		
	}


	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

}
