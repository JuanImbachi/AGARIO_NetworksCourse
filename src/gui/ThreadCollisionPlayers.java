package gui;

public class ThreadCollisionPlayers extends Thread{

	private static final int TIME = 10;
	private GUI_principal principal;
	
	public ThreadCollisionPlayers(GUI_principal principal) {
		
		this.principal = principal;
	}

	public void run() {
		while(true) {			
			try {
				principal.checkCollisionPlayers();
				Thread.sleep(TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
