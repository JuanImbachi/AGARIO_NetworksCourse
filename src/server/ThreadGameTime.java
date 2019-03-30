package server;

public class ThreadGameTime extends Thread {

	private Server server;
	private int seconds;
	
	public ThreadGameTime(Server server) {
		
		this.server = server;
		seconds=0;
	}

	@Override
	public void run () {
		while(seconds < 300) {
			
			seconds++;
			//System.out.println("Hilo gameTim #jugadores= "+server.numbPlayersPlaying());
			if(seconds<300 && server.numbPlayersPlaying()>1) {
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				server.setRunningGame(false);
			}
		}
	}
}
