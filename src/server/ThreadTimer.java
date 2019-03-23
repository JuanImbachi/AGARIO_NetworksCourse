package server;

public class ThreadTimer extends Thread{
	
	private Server server;
	
	private int seconds;
	
	
	public ThreadTimer(Server s) {
		server = s;
	}
	
	
	@Override
	public void run() {
		
		while(server.isWaitingClients()) {
			
			seconds++;
			System.out.println(seconds);
			if(seconds<120 && server.getNumberOfClients()<5) {
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
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

}
