package client;

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


import dataBaseServer.DataBaseServer;
import server.Server;



public class Client {
	
	public static final int PORT_TCP = 3425;
	public static final String IP_MULTICAST = "229.5.6.7";

	
	private String IpServer;

	private Socket clientConnectionDB;
	
	private Socket clientConnectionServer;
	
	private boolean waitingForPlay;
	
	private boolean startedGame;
		
	private String nickname;
	
	private int score;
		
	private Socket clientSocket;
	
	private MulticastSocket mcSocket;

//	public Client(String nickname, String IpServer) {
//		
//		this.nickname = nickname;
//		score=0;
//		startedGame  = false;
//		waitingForPlay = true;
//		this.IpServer = IpServer;
//	}
	
	
	public Client() throws IOException{
		
		BufferedReader	reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Escriba nickname, password y email separados por una coma cada uno");
		
		String info[] = reader.readLine().split(",");
		String message = DataBaseServer.REGISTER_DB + "," + info[0] + "," +info[1]+","+info[2];
		connectWithDB(message);
		
		
		
	}
	
	
//	public void connectWithServer() throws IOException {
//
//		//CONEXION TCP
//
//		clientSocket = new Socket(IpServer, PORT_TCP);
//		DataInputStream in = new DataInputStream(clientSocket.getInputStream());
//		DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
//		String nickToServer = this.nickname ;
//		out.writeUTF(nickToServer);
//	//	String mensaje = in.readUTF();
//		//System.out.println("MENSAJE OBTENIDO DESDE EL SERVER . " +mensaje);
//		//if(mensaje.compareToIgnoreCase("BIENVENIDO")==0) {
//			waitingForPlay = true;
//		//}
//		
//		//HiloAtentoAlMulticast atento = new HiloAtentoAlMulticast(this);
//		//atento.start();
//		
//	}
	
	
	private void connectWithDB(String message){
		
		
		try {
			
			clientConnectionDB = new Socket(Server.IP_SERVER, DataBaseServer.DB_PORT);
			DataInputStream in = new DataInputStream(clientConnectionDB.getInputStream());
			DataOutputStream out = new DataOutputStream(clientConnectionDB.getOutputStream());
			
			out.writeUTF(message);
			
			System.out.println(in.readUTF());
			
		} catch (Exception e) {
			System.out.println("Error connecting to data base");
		}
			
		
		
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


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
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


	public static void main(String[] args) throws IOException {
		
		
			Client c = new Client();
			
		
		
		
        
	
//		DataInputStream in;
//		
//		DataOutputStream out;
//		 
//		try {
//			clientConnection = new Socket(Server.IP_SERVER, Server.PORT);
//			
//			while(true) {
//			
////			BufferedReader	clientReader = new BufferedReader(new InputStreamReader(System.in));
////			String palabra=	clientReader.readLine();
////			
////			
////			
////			String n="";
//			in = new DataInputStream(clientConnection.getInputStream());
//			out = new DataOutputStream(clientConnection.getOutputStream());
//			
//			out.writeUTF(Server.CONNECTED_CLIENT);
//			System.out.println(in.readUTF());
//		}
////			clientConnection.close();
////			in.close();
////			out.close();
//			
//		} catch (Exception e) {
//			System.out.println("Se generó una excepcion");
//		} 

	}


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
	

}