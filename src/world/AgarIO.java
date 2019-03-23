package world;

import java.awt.Color;
import java.util.ArrayList;

public class AgarIO {
	
	public static final int  GAME_MINUTES = 2;
	
	public static final String  WAITING = "Esperando se conecten otros jugadores...";
	
	public static final String  PLAYING = "¡Comienza el juego!";
	
	public static final String  GAME_FINISHED = "¡La partida ha terminado!";
	
	private PlayerBall[] players;
	
	private boolean isRunning;
	
	private PlayerBall winner;

	
	//private HiloCaballo[] hilos ;
	
	private String status;
	
	private boolean winnerExist;
	
	private boolean gameEnded;
	
	public AgarIO(int numbPlayers , ArrayList<String> nicks) {
		
		players = new PlayerBall[numbPlayers];
		isRunning = false;
		winner = null;
		status = WAITING;
		winnerExist=false;
		gameEnded = false;
		
	}
	
	public void initializePlayers(ArrayList<String> nicks) {
		
		for (int i = 0; i < players.length; i++) {
			
			String nick = nicks.get(i);
			
			int R = (int)(Math.random()*256);
			int G = (int)(Math.random()*256);
			int B= (int)(Math.random()*256);

			Color color = new Color(R, G, B);
			int posX=0;
			int posY=0;
			
			PlayerBall player = new PlayerBall(nick, color, posX, posY, 0, false, false);
			players[i] = player;
		}
	
	
	}
    
}
