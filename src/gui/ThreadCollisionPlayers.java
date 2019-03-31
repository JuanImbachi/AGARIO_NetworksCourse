//package gui;
//
//import world.AgarIO;
//
//public class ThreadCollisionPlayers extends Thread{
//
//	private static final int TIME = 10;
//	private AgarIO game;
//	
//	public ThreadCollisionPlayers(AgarIO game) {
//		
//		this.game = game;
//	}
//
//	public void run() {
//		while(game.getStatus().equals(AgarIO.PLAYING)) {			
//			try {
//				game.checkCollisionPlayers();
//				if(game.getPlayersCounter() <=1) {
//					game.setStatus(AgarIO.GAME_FINISHED);
//				}
//				Thread.sleep(TIME);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
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
