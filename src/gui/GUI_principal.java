package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.Client;
import world.AgarIO;
import world.Ball;
import world.PlayerBall;

public class GUI_principal extends JFrame {
	public static final int MAX_TOP_PLAYERS = 3;
	private Gui_SignUp jdSignUp;
	private Gui_LogIn jdLogIn;
	private FirstPanel jdFirstPanel;
	private Gui_IP jdIp;
	private Gui_WaitingRoom jdWaitingRoom;
	private AgarIO agario;
	private JFrame gameSpace;
	private ThreadMovingPlayers movingPlayers;
	private ThreadCollisionPlayers collisionPlayers;
	private ThreadRepaint repaint;
	private boolean connectionResult;
	private Gui_Game space;

	private Client player;

	public GUI_principal() throws IOException {
		// Font normal = new Font("Arial", Font.BOLD, 14);
		

		player = new Client(this);
		setTitle("Icesi Games SA - AgarIO");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700, 600));
		setResizable(false);
		setLocationRelativeTo(null);

		jdSignUp = new Gui_SignUp(this);
		jdLogIn = new Gui_LogIn(this);
		jdFirstPanel = new FirstPanel(this);
		jdIp = new Gui_IP(this);
		jdIp.setVisible(true);
		
//		System.out.println("inicializa client");
//		threadPrueba th = new threadPrueba(this);
//		th.start();

	}
	

	public void initializeGameSpace() {
		Dimension d = new Dimension(AgarIO.GAME_WIDTH, AgarIO.GAME_HEIGHT);
//		System.out.println(agario.getPlayers().get(0).getPosX()+"  "+agario.getPlayers().get(0).getPosY());
		space = new Gui_Game(agario.getPlayers(), agario.getFoods(), this);
		 
		gameSpace = new JFrame("Icesi Games SA - AgarIO");
		
		gameSpace.add(space);

		gameSpace.setSize(d);

		gameSpace.setLocationRelativeTo(null);
		gameSpace.setDefaultCloseOperation(EXIT_ON_CLOSE);

		gameSpace.setVisible(true);
		
		
	
	}

	public void initializeWorld(ArrayList<PlayerBall> players, ArrayList<Ball> foods) {
		agario = new AgarIO(players, foods);
		
		
		initializeGameSpace();
		collisionPlayers = new ThreadCollisionPlayers(this);
		collisionPlayers.start();
		movingPlayers = new ThreadMovingPlayers(player.getId(), this);
		movingPlayers.start();
		
		repaint = new ThreadRepaint(this);
		repaint.start();
	}

	public void initializePlayers(ArrayList<String> nicks) {
		if (nicks.size() < AgarIO.MAX_PLAYERS) {
			agario.initializePlayers(nicks);
		} else {
			JOptionPane.showMessageDialog(null, "More players than the allowed.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	public void repaintGame() {
		space.setPlayers(agario.getPlayers());
		space.setFoods( agario.getFoods());
		space.removeAll();
		space.repaint();
		space.revalidate();
	
	}
	public int getPosX() {
		return space.getPosX();
	}
	
	public int getPosY() {
		return space.getPosY();
	}

	public void jdIp() {
		jdIp.setVisible(true);
	}

	public void firstPanel() {
		jdFirstPanel.setVisible(true);
	}

	public void jdLogIn() {
		jdLogIn.setVisible(true);
	}

	public void jdSignUp() {
		jdSignUp.setVisible(true);
	}

	public void oldPlayer(String email, String password) throws IOException {
//		if(player==null){
//			System.out.println("es null");
//		}
		player.loginPlayer(email, password);
	}

	public void newPlayer(String email, String nickname, String password) throws IOException {
		player.registerPlayer(email, password, nickname);
	}

	public void connectServer(String ip) {
		// intenta conectarse al server
		// en caso de que pueda conectar, hace dispose() del JDialog ip y llama a
		// firstPanel()
		// caso contrario, vuelve a mostrarlo para que re ingrese la ip

		jdIp.dispose();
		firstPanel();
	}

	public void goToWaitingRoom() {

		jdWaitingRoom = new Gui_WaitingRoom(this);
		jdWaitingRoom.setVisible(true);

	}

	public void refreshWR(String[] data) {
		jdWaitingRoom.refresh(data);
		pack();
	}

	public Client getPlayer() {
		return player;
	}

	public void setPlayer(Client player) {
		this.player = player;
	}

	public void connectionResult(String result) {

		JOptionPane.showMessageDialog(this, result);

	}

	public boolean isConnectionResult() {
		return connectionResult;
	}

	public void setConnectionResult(boolean connectionResult) {
		this.connectionResult = connectionResult;
	}

	public void movePlayer(int id, double posX, double posY) {
		agario.movePlayer(id, posX, posY);
	}

	public void checkCollisionPlayerFood(int idPlayer) {
		//Revisar, estaba recibiendo pos en la lista, pero debe 
		//recibir identificador de la ball
		int n = agario.checkCollisionPlayerFood(idPlayer);
		if (n != -1) {
			player.getEatenBalls().add(n);
			player.setEatenBalls(player.getEatenBalls());
		}
	}

	public void checkCollisionPlayers() {
		agario.checkCollisionPlayers();
		
	}

	public ArrayList<PlayerBall> getPlayersTop() {
		ArrayList<PlayerBall> playersTop = agario.getTop(MAX_TOP_PLAYERS);
		return playersTop;
	}

	public Gui_SignUp getJdSignUp() {
		return jdSignUp;
	}

	public void setJdSignUp(Gui_SignUp jdSignUp) {
		this.jdSignUp = jdSignUp;
	}

	public Gui_LogIn getJdLogIn() {
		return jdLogIn;
	}

	public void setJdLogIn(Gui_LogIn jdLogIn) {
		this.jdLogIn = jdLogIn;
	}

	public FirstPanel getJdFirstPanel() {
		return jdFirstPanel;
	}

	public void setJdFirstPanel(FirstPanel jdFirstPanel) {
		this.jdFirstPanel = jdFirstPanel;
	}

	public Gui_IP getJdIp() {
		return jdIp;
	}

	public void setJdIp(Gui_IP jdIp) {
		this.jdIp = jdIp;
	}

	public Gui_WaitingRoom getJdWaitingRoom() {
		return jdWaitingRoom;
	}

	public void setJdWaitingRoom(Gui_WaitingRoom jdWaitingRoom) {
		this.jdWaitingRoom = jdWaitingRoom;
	}

	public AgarIO getAgario() {
		return agario;
	}

	public void setAgario(AgarIO agario) {
		this.agario = agario;
	}

	public JFrame getGameSpace() {
		return gameSpace;
	}

	public void setGameSpace(JFrame gameSpace) {
		this.gameSpace = gameSpace;
	}

	public ThreadMovingPlayers getMovingPlayers() {
		return movingPlayers;
	}


	public void setMovingPlayers(ThreadMovingPlayers movingPlayers) {
		this.movingPlayers = movingPlayers;
	}


	public ThreadCollisionPlayers getCollisionPlayers() {
		return collisionPlayers;
	}


	public void setCollisionPlayers(ThreadCollisionPlayers collisionPlayers) {
		this.collisionPlayers = collisionPlayers;
	}


	public ThreadRepaint getRepaint() {
		return repaint;
	}


	public void setRepaint(ThreadRepaint repaint) {
		this.repaint = repaint;
	}


	public Gui_Game getSpace() {
		return space;
	}



	public void setSpace(Gui_Game space) {
		this.space = space;
	}

	public boolean getMoved(){
		return space.isMoved();
	}
	public static void main(String[] args) {

		try {

			GUI_principal principal = new GUI_principal();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void showDeath() {
		JOptionPane.showMessageDialog(this, "YOU'VE BEEN ELIMINATED :C", "YOU LOSE", JOptionPane.ERROR_MESSAGE );
		
	}

}
