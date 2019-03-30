package world;

public class ThreadCollisionTwoSeconds extends Thread {

	private static int TIME = 10;
	private PlayerBall player1, player2;
	private double millis;
	private boolean eated;
	private AgarIO game;
	
	public ThreadCollisionTwoSeconds(PlayerBall player1, PlayerBall player2, AgarIO game) {
		this.game = game;
		this.player1 = player1;
		this.player2 = player2;
		this.millis = 0;
		eated = false;

	}
	
	@Override
	public void run() {
		
		while (millis < 2000 && !eated) {
	
			millis += 10;
						
			 if(!eated) {
				eated = player1.checkCollision(player2);
			 }
			try {
				Thread.sleep(TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		if(millis >= 2000 && eated == true) {
		
			game.stopGamePlayer(player2.getId());
			game.increaseMassPlayer(player1, player2);
			game.setPlayersCounter(game.getPlayersCounter() -1);
		}
	}

}
