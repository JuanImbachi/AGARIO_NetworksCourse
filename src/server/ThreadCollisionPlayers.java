package server;

import world.AgarIO;

public class ThreadCollisionPlayers extends Thread{

	private static final int TIME = 10;
	private AgarIO game;
	
	public ThreadCollisionPlayers(AgarIO game) {
		
		this.game = game;
	}

	public void run() {
		while(game.getStatus().equals(AgarIO.PLAYING)) {			
			try {
				game.checkCollisionPlayers();
				if(game.getPlayersCounter() <=1) {
					game.setStatus(AgarIO.GAME_FINISHED);
				}
				Thread.sleep(TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
