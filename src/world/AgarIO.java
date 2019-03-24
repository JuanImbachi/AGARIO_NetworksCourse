package world;

import java.awt.Color;
import java.util.ArrayList;

public class AgarIO {
	
	public static final int GAME_WIDTH = 1000;
	
	public static final int GAME_HEIGHT = 700;
	    
	public static final int  GAME_MINUTES = 2;
	
	public static final String  WAITING = "Esperando se conecten otros jugadores...";
	
	public static final String  PLAYING = "¡Comienza el juego!";
	
	public static final String  GAME_FINISHED = "¡La partida ha terminado!";
	
	private PlayerBall[] players;
	
	private ArrayList<Ball> food;
	
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
			PlayerBall player = new PlayerBall(nick, GAME_WIDTH, GAME_HEIGHT);
			players[i] = player;
		}
	}

	public PlayerBall[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerBall[] players) {
		this.players = players;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public PlayerBall getWinner() {
		return winner;
	}

	public void setWinner(PlayerBall winner) {
		this.winner = winner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isWinnerExist() {
		return winnerExist;
	}

	public void setWinnerExist(boolean winnerExist) {
		this.winnerExist = winnerExist;
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}
    
	
}
