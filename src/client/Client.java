package client;

import gui.GUI_principal;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MulticastSocket;
import java.net.Socket;

//import Cliente.HiloAtentoAlMulticast;

import java.util.ArrayList;

import dataBaseServer.DataBaseServer;
import server.Server;
import world.AgarIO;
import world.Ball;
import world.PlayerBall;

public class Client {

	public static final int PORT_TCP = 3425;
	public static final String IP_MULTICAST = "229.5.6.7";

	private String IpServer;

	private Socket clientConnectionDB;

	private Socket clientConnectionServer;

	private ThreadWaitingRoom threadWR;

	private boolean waitingForPlay;

	private boolean startedGame;

	private ThreadInfoGameClient threadIGC;

	// private AgarIO game;

	private String nickname;

	private int id;

	private GUI_principal gui;

	private Socket clientSocket;

	private Socket gameSocket;

	private MulticastSocket mcSocket;

	private ArrayList<Integer> eatenBalls;

	// public Client(String nickname, String IpServer) {
	//
	// this.nickname = nickname;
	// score=0;
	// startedGame = false;
	// waitingForPlay = true;
	// this.IpServer = IpServer;
	// }

	public Client(GUI_principal theGui) throws IOException {
		gui = theGui;

		clientSocket = new Socket(Server.IP_SERVER, Server.PORT_WR);
		gameSocket = new Socket(Server.IP_SERVER, Server.PORT_INFO);
		eatenBalls = new ArrayList<Integer>();


	}

	public ArrayList<Integer> getEatenBalls() {
		return eatenBalls;
	}

	public GUI_principal getGui() {
		return gui;
	}

	public void setEatenBalls(ArrayList<Integer> e) {
		eatenBalls = e;
	}

	public void registerPlayer(String email, String pass, String nick)
			throws IOException {

		String message = DataBaseServer.REGISTER_DB + "," + nick + "," + pass
				+ "," + email;
		connectWithDB(message);

	}

	public void loginPlayer(String email, String password) throws IOException {

		String message = DataBaseServer.LOGIN_DB + "," + email + "," + password;
		connectWithDB(message);

	}

	public void startGame() {

		startedGame = true;

		threadIGC = new ThreadInfoGameClient(this);

		threadIGC.start();

	}
//
//	private boolean cond;

	public void updateGame(String[] players, String[] food) {

		for (int i = 0; i < players.length; i++) {
			if (i != id) {
				String[] player = players[i].split("/");
				int id = Integer.parseInt(player[0]);
				double x = Double.parseDouble(player[1]);
				double y = Double.parseDouble(player[2]);

				boolean isPlaying = false;

				
				if (player[3].equalsIgnoreCase("true")) {
					isPlaying = true;
					
				}

				int mass = Integer.parseInt(player[4]);
				gui.getAgario().updatePlayer(id, x, y, isPlaying, mass);
				gui.getSpace().setPlayers(gui.getAgario().getPlayers());
//				if (!cond) {
//					System.out.println(x + "  " + y
//							+ "   INFO ENVIADA POR SERVER");
//					cond = true;
//				}
			}
//			else{
//				
//				String[] player = players[i].split("/");
//				int id = Integer.parseInt(player[0]);
//				
//
//				boolean isPlaying = false;
//
//				
//				if (player[3].equalsIgnoreCase("true")) {
//					isPlaying = true;
//					
//				}
//
//				int mass = Integer.parseInt(player[4]);
//				gui.getAgario().updateOwnPlayer(id,isPlaying, mass);
//				gui.getSpace().setPlayers(gui.getAgario().getPlayers());
//				
//				
//			}

		}

		gui.getAgario().upDateFoodList(food);

		// gui.setAgario(game);

	}

	// public void connectWithServer() throws IOException {
	//
	// //CONEXION TCP
	//
	// clientSocket = new Socket(IpServer, PORT_TCP);
	// DataInputStream in = new DataInputStream(clientSocket.getInputStream());
	// DataOutputStream out = new
	// DataOutputStream(clientSocket.getOutputStream());
	// String nickToServer = this.nickname ;
	// out.writeUTF(nickToServer);
	// // String mensaje = in.readUTF();
	// //System.out.println("MENSAJE OBTENIDO DESDE EL SERVER . " +mensaje);
	// //if(mensaje.compareToIgnoreCase("BIENVENIDO")==0) {
	// waitingForPlay = true;
	// //}
	//
	// //HiloAtentoAlMulticast atento = new HiloAtentoAlMulticast(this);
	// //atento.start();
	//
	// }

	private void connectWithDB(String message) {

		try {

			clientConnectionDB = new Socket(Server.IP_SERVER,
					DataBaseServer.DB_PORT);
			DataInputStream in = new DataInputStream(
					clientConnectionDB.getInputStream());
			DataOutputStream out = new DataOutputStream(
					clientConnectionDB.getOutputStream());

			out.writeUTF(message);

			String result = in.readUTF();

			gui.connectionResult(result);
			boolean r = false;
			if (result.equals(DataBaseServer.CONF_ACCESS)
					|| result.equals(DataBaseServer.PLAYER_SAVED)) {
				r = true;
				String information = in.readUTF();
				nickname = information;

				connectWithServer();
			}
			gui.setConnectionResult(r);

			clientConnectionDB.close();

		} catch (Exception e) {
			System.out.println("Error connecting to data base");
		}

	}

	private void connectWithServer() {

		try {

			clientConnectionServer = new Socket(Server.IP_SERVER, Server.PORT);
			DataInputStream in = new DataInputStream(
					clientConnectionServer.getInputStream());
			DataOutputStream out = new DataOutputStream(
					clientConnectionServer.getOutputStream());

			out.writeUTF(Server.CONNECTED_CLIENT);

			if (in.readUTF().equals(Server.CONNECTED_CLIENT)) {
				gui.goToWaitingRoom();
				waitingForPlay = true;
				threadWR = new ThreadWaitingRoom(this);
				threadWR.start();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void refreshWaitingRoom(String[] data) {
		gui.refreshWR(data);
	}

	public String getIpServer() {
		return IpServer;
	}

	public void setIpServer(String iP) {
		IpServer = iP;
	}

	public boolean isWaitingForPlay() {
		return waitingForPlay;
	}

	public void setWaitingForPlay(boolean waitingForPlay) {
		this.waitingForPlay = waitingForPlay;
	}

	public boolean isStartedGame() {
		return startedGame;
	}

	public void setStartedGame(boolean startedGame) {
		this.startedGame = startedGame;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public MulticastSocket getMcSocket() {
		return mcSocket;
	}

	public void setMcSocket(MulticastSocket mcSocket) {
		this.mcSocket = mcSocket;
	}

	// public static void main(String[] args) throws IOException {
	//
	// Client c = new Client();
	//
	// // DataInputStream in;
	// //
	// // DataOutputStream out;
	// //
	// // try {
	// // clientConnection = new Socket(Server.IP_SERVER, Server.PORT);
	// //
	// // while(true) {
	// //
	// // // BufferedReader clientReader = new BufferedReader(new
	// // InputStreamReader(System.in));
	// // // String palabra= clientReader.readLine();
	// // //
	// // //
	// // //
	// // // String n="";
	// // in = new DataInputStream(clientConnection.getInputStream());
	// // out = new DataOutputStream(clientConnection.getOutputStream());
	// //
	// // out.writeUTF(Server.CONNECTED_CLIENT);
	// // System.out.println(in.readUTF());
	// // }
	// // // clientConnection.close();
	// // // in.close();
	// // // out.close();
	// //
	// // } catch (Exception e) {
	// // System.out.println("Se generó una excepcion");
	// // }
	//
	// }

	public Socket getClientConnectionDB() {
		return clientConnectionDB;
	}

	public void setClientConnectionDB(Socket clientConnectionDB) {
		this.clientConnectionDB = clientConnectionDB;
	}

	public Socket getClientConnectionServer() {
		return clientConnectionServer;
	}

	public void setClientConnectionServer(Socket clientConnectionServer) {
		this.clientConnectionServer = clientConnectionServer;
	}

	public ThreadWaitingRoom getThreadWR() {
		return threadWR;
	}

	public void setThreadWR(ThreadWaitingRoom threadWR) {
		this.threadWR = threadWR;
	}

	public ThreadInfoGameClient getThreadIGC() {
		return threadIGC;
	}

	public void setThreadIGC(ThreadInfoGameClient threadIGC) {
		this.threadIGC = threadIGC;
	}

	public Socket getGameSocket() {
		return gameSocket;
	}

	public void setGameSocket(Socket gameSocket) {
		this.gameSocket = gameSocket;
	}

	// public AgarIO getGame() {
	// return game;
	// }
	//
	// public void setGame(AgarIO game) {
	// this.game = game;
	// }

	public void initializeWorld(String[] infoPlayers, String[] infoBalls) {

		ArrayList<PlayerBall> p1 = new ArrayList<PlayerBall>();

		for (int i = 0; i < infoPlayers.length; i++) {
			String[] playerInfo = infoPlayers[i].split("/");
			int id = Integer.parseInt(playerInfo[0]);
			String nickname = playerInfo[1];
			if (nickname.equals(this.nickname)) {
				this.id = id;
			}
			double posX = Double.parseDouble(playerInfo[2]);
			double posY = Double.parseDouble(playerInfo[3]);
			int rgb = Integer.parseInt(playerInfo[4]);

			PlayerBall pb = new PlayerBall(id, nickname, 10, 10);
			pb.setColor(new Color(rgb));
			pb.setPosX(posX);
			pb.setPosY(posY);

			
			
			p1.add(pb);
		}

		ArrayList<Ball> b = new ArrayList<Ball>();

		for (int i = 0; i < infoBalls.length; i++) {

			String[] ballInfo = infoBalls[i].split("/");

			int rgb = Integer.parseInt(ballInfo[0]);
			double posX = Double.parseDouble(ballInfo[1]);
			double posY = Double.parseDouble(ballInfo[2]);
			int id = Integer.parseInt(ballInfo[3]);
			Ball bl = new Ball(10, 10, true, id);
			bl.setColor(new Color(rgb));
			bl.setPosX(posX);
			bl.setPosY(posY);

			b.add(bl);
		}

		gui.initializeWorld(p1, b);
		System.out.println("CLIENT game started");
		// System.out.println(p1.get(0).getPosX()+"  "+p1.get(0).getPosY());

		// game = gui.getAgario();

	}

}