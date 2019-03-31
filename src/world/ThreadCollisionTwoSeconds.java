package world;

import server.Server;

public class ThreadCollisionTwoSeconds extends Thread {

	private static long TIME = 1;
	private PlayerBall player1, player2;
	private double millis;
	private boolean eated;
	private AgarIO game;
	
	public ThreadCollisionTwoSeconds(PlayerBall player1, PlayerBall player2, AgarIO game) {
		this.game = game;
		this.player1 = player1;
		this.player2 = player2;
		this.millis = 0;
		eated = true;

	}
	
	@Override
	public void run() {
		
		while (millis < 2000 && eated) {
	

			
			
			millis ++;
					
			eated = player1.checkCollision(player2);
			

			System.out.println(millis + " "+eated);
			
			 
			try {
				Thread.sleep(TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		if(millis == 2000.0 && eated == true) {
			
			System.out.println("ENTRA");
		
			game.getPlayer(player1.getId()).setValidating(false);
			
//			game.stopGamePlayer(player2.getId());
//			game.increaseMassPlayer(player1, player2);
			game.playerEated(player1, player2);
			game.setPlayersCounter(game.getPlayersCounter()-1);;
		}else{
			
			game.getPlayer(player1.getId()).setValidating(false);
			game.getPlayer(player2.getId()).setValidating(false);
		}
	}

}
