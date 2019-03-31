package gui;

//
//import world.AgarIO;
//
//public class ThreadCollisionPlayers extends Thread{
//
//	private static final int TIME = 10;
//	private Server server;
//	
//	public ThreadCollisionPlayers(Server game) {
//		
//		this.server = game;
//	}
//
//	public void run() {
//		while(server.getGame().getStatus().equals(AgarIO.PLAYING)) {			
////			try {
//				server.checkCollisionPlayers();
//				
//				if(server.getGame().getPlayersCounter() <=1) {
//					server.getGame().setStatus(AgarIO.GAME_FINISHED);
//				}
////				Thread.sleep(TIME);
////			} catch (InterruptedException e) {
////				e.printStackTrace();
////			}
//		}
//	}
//}


public class ThreadCollisionPlayers extends Thread{

	private static final int TIME = 10;
	private GUI_principal principal;

	public ThreadCollisionPlayers(GUI_principal principal) {

		this.principal = principal;
	}

	public void run() {
		while(principal.getAgario().getPlayer(principal.getPlayer().getId()).isPlaying()) {			
			try {
				principal.checkCollisionPlayers();
				Thread.sleep(TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(principal.getAgario().getPlayer(principal.getPlayer().getId()).isPlaying() == false){
			principal.showDeath();
		}
		
	}
}
