package world;

import java.awt.Color;

public class PlayerBall extends Ball {

	private String nickname;
	
	private int score;
	
	private boolean isWinner;
	
	private boolean isPlaying;
	
	public PlayerBall( String nickname, Color color, int posX, int posY, int score, boolean isWinner, boolean isPlaying) {
		 super(color, posX, posY);
		 
		 this.nickname =  nickname;
		 this.score = score;
		 this.isWinner = isWinner;
		 this.isPlaying = isPlaying;
		
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	
}
