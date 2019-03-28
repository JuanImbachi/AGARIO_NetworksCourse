package world;

import gui.GUI_principal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AgarIO {
	
	public static final int GAME_WIDTH = 1200;
	
	public static final int GAME_HEIGHT = 800;
	    
	public static final int  GAME_MINUTES = 5;
	
	public static final int MAX_PLAYERS = 5;
	
	public static final String  WAITING = "Esperando se conecten otros jugadores...";
	
	public static final String  PLAYING = "�Comienza el juego!";
	
	public static final String  GAME_FINISHED = "�La partida ha terminado!";
	
	public static final int MAX_FOOD = 90;
	
	private ArrayList<PlayerBall> players;
	
	private boolean isRunning;
	
	private PlayerBall winner;

	private ArrayList<Ball> foods;
	
	private int foodCounter;
	
	private int playersCounter;
	
	private String status;
	
	private boolean winnerExist;
	
	private boolean gameEnded;
	
	
	public AgarIO() {
		
		players = new ArrayList<PlayerBall>();
		isRunning = false;
		winner = null;
		status = WAITING;
		winnerExist=false;
		gameEnded = false;
		foods = new ArrayList<Ball>();
		foodCounter = 0;
		playersCounter = 0;
		
	}
	
	public AgarIO(ArrayList<PlayerBall> players, ArrayList<Ball> foods){
		this.players = players;
		System.out.println(players.get(0).getPosX()+"  "+players.get(0).getPosY()+" CONSTRUCTOR");
		isRunning = false;
		winner = null;
		status = WAITING;
		winnerExist=false;
		gameEnded = false;
		this.foods = foods;
		foodCounter = 0;
		playersCounter = 0;
	}
	
	public void initializePlayers(ArrayList<String> nicks) {
		
		for (int i = 0; i < nicks.size(); i++) {
			
			String nick = nicks.get(i);			
			PlayerBall player = new PlayerBall(playersCounter,nick, GAME_WIDTH, GAME_HEIGHT);
			//System.out.println("AGARIO INITIALIZE PLAYERS pos X: "+player.getPosX()+" pos y: "+ player.getPosY());
			players.add(player);
			playersCounter +=1;
		}
		
		this.setStatus(PLAYING);
	}
	
	public void createFood() {
		
		Ball food = new Ball(GAME_WIDTH, GAME_HEIGHT, true, foodCounter);
		foods.add(food);
		foodCounter+=1;
	}
	
	public Ball searchFood(int id) {
		
		for (int i = 0; i < foods.size(); i++) {
			if(foods.get(i).getFoodID() == id) {
				Ball element = foods.get(i);
				return element;
			}
		}
		return null;
	}
	public void eatFood(int id) {
		Ball element = searchFood(id);
		if(element != null) {
			this.foods.remove(element);
		}
	}
	
	public PlayerBall getPlayer(int id) {
		for (int i = 0; i < players.size(); i++) {
			PlayerBall player = players.get(i);
			if(player.getId() == id) {
				return player;
			}
		}
		return null;
	}

	public void movePlayer(int id, double posX, double posY) {
		PlayerBall player= this.getPlayer(id);
		if(player!= null) {
			player.moveBall(posX, posY);
			
		}
	}
	
	public  int checkCollisionPlayerFood(int idPlayer) {
		
		
		PlayerBall player= getPlayer(idPlayer);
		
		if(player != null) {
			
			for (int i = 0; i < foods.size(); i++) {
				Ball food= foods.get(i);
				boolean eated=player.checkCollisionFood(food);
			
				if(eated) {
					
					return food.getFoodID();
					//foods.remove(food);
					
					//break; 
				}
			}
		}
		
		return -1;
	}
	
	public void removeFood(int idFood) {
		foods.remove(idFood);
	}
	
	public void checkCollisionPlayers() {
		for (int i = 0; i < players.size(); i++) {
			PlayerBall player1 = players.get(i);
			for (int j = i+1; j < players.size(); j++) {
				PlayerBall player2 = players.get(j);
				boolean eliminated= player1.checkCollision(player2);
				if(eliminated) {
					playersCounter--;
				}
			}
		}
		
		if(playersCounter <=1) {
			this.setStatus(GAME_FINISHED);
		}
	}
	
	public ArrayList<PlayerBall> getTop(int maxTop ){
		ArrayList<PlayerBall> playersTop = new ArrayList<PlayerBall>();
		for (int i = 0; i < players.size(); i++) {
			playersTop.add(players.get(i));
		}
		Collections.sort(playersTop, new Comparator<PlayerBall>() {
	            public int compare(PlayerBall p1, PlayerBall p2) {
	                return p2.getMass() - p1.getMass();
	            }            
	        }
		);
		
		if(playersTop.size() > maxTop) {
			playersTop.subList(maxTop, playersTop.size()).clear();
		}
		
		return playersTop;
        
	}
	
	public ArrayList<PlayerBall> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PlayerBall> players) {
		this.players = players;
	}

	public int getPlayersCounter() {
		return playersCounter;
	}

	public void setPlayersCounter(int playersCounter) {
		this.playersCounter = playersCounter;
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

	public ArrayList<Ball> getFoods() {
		return foods;
	}

	public void setFoods(ArrayList<Ball> foods) {
		this.foods = foods;
	}

	public int getFoodCounter() {
		return foodCounter;
	}

	public void setFoodCounter(int foodCounter) {
		this.foodCounter = foodCounter;
	}

	public void updatePlayer(int id, double x, double y, boolean isPlaying, int mass) {
		
		players.get(id).setPosX(x);
		players.get(id).setPosY(y);
		players.get(id).setMass(mass);
		players.get(id).setPlaying(isPlaying);
		
		
		
	}
	
	public void upDateFoodList(String[] food) {
		ArrayList<Ball> foods = new ArrayList<Ball>();
		for (int i = 0; i < food.length; i++) {
			String[] fd = food[i].split("/");
			
			
//			System.out.println("======"+food[i]);

			int rgb= Integer.parseInt(fd[0]);
			double posX = Double.parseDouble(fd[1]);
			double posY = Double.parseDouble(fd[2]);
			int id = Integer.parseInt(fd[3]);
		
			Ball b = new Ball(10, 10, true, id);
			b.setPosX(posX);
			b.setColor(new Color(rgb));
			b.setPosY(posY);
			foods.add(b);
		}
		
		setFoods(foods);
	}

	public void updateFood(String[] food) {
		
		for(int i=0; i<food.length;i++) {
			eatFood(Integer.parseInt(food[i]));
		}
		
	}	
}
