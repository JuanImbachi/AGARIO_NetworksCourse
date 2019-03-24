package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import client.Client;
import dataBaseServer.DataBaseServer;

public class Server {

//	public final static String IP_SERVER="172.30.179.30";
	public final static String IP_SERVER="192.168.1.15";
	public static final int PORT = 36556;
	public static final int PORT_WR = 33000;
	public final static String CONNECTED_CLIENT = "connected_client";
	
//	private Socket socket;
	
	private ServerSocket SsocketInfo;

	private DataBaseServer dbServer;
	
	private boolean waitingClients;
	
	private ThreadWaitingClients threadWC;
	
	private ThreadTimer timer;
	
	private ArrayList<String> players;
	
	
	private ArrayList<ThreadSendInfoWR> threadSIWR;
	
	private  ServerSocket serverSocket;
	
	private int numberOfClients;
	
	public Server() throws IOException {
		
		SsocketInfo = new ServerSocket(PORT_WR);
		players=new ArrayList<String>();
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server online");
		threadWC = new ThreadWaitingClients(this);
		waitingClients = true;
//		threadWC.start();
		timer = new ThreadTimer(this);
		threadSIWR=new ArrayList<ThreadSendInfoWR>();
		dbServer = new DataBaseServer(this);
		
	}
	
	
	public static void main(String[] args) {
		
		try {
			
			Server serv = new Server();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try {
//			
//			System.out.println("::Servidor en línea, esperando conexión::");
//			
//			
//			while(true) {
//				String salida="";
//				socket = serverSocket.accept();
//				
//				System.out.println("Conexión iniciada");
//				try {
//				while(!salida.equals("desconectar")) {
//					in = new DataInputStream(socket.getInputStream());
//					out = new DataOutputStream(socket.getOutputStream());
//				String mensajeObtenidoCliente = in.readUTF();
//				System.out.println("=================" + mensajeObtenidoCliente);
//				String respuestaServer = "::Bienvenido::";
//				out.writeUTF(respuestaServer);
//				salida=mensajeObtenidoCliente;
//				}
//				out = new DataOutputStream(socket.getOutputStream());
//
//				out.writeUTF("Se ha desconectado exitosamente");
//				}catch (Exception e) {
//					// TODO: handle exception
//					System.out.println("::Cliente desconectado::");
//
//				}
//				socket.close();
//				System.out.println("::Cliente desconectado::");
//			
//			}
//	
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	public ThreadWaitingClients getThreadWC() {
		return threadWC;
	}
	public void setThreadWC(ThreadWaitingClients threadWC) {
		this.threadWC = threadWC;
	}


//	public Socket getSocket() {
//		return socket;
//	}
//
//
//	public void setSocket(Socket socket) {
//		this.socket = socket;
//	}
	
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}


	public boolean isWaitingClients() {
		return waitingClients;
	}


	public void setWaitingClients(boolean waitingClients) {
		this.waitingClients = waitingClients;
	}


	public ThreadTimer getTimer() {
		return timer;
	}


	public void setTimer(ThreadTimer timer) {
		this.timer = timer;
	}


	public int getNumberOfClients() {
		return numberOfClients;
	}


	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}


	public DataBaseServer getDbServer() {
		return dbServer;
	}


	public void setDbServer(DataBaseServer dbServer) {
		this.dbServer = dbServer;
	}


	public ArrayList<String> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}


	public ArrayList<ThreadSendInfoWR> getThreadSIWR() {
		return threadSIWR;
	}


	public void setThreadSIWR(ArrayList<ThreadSendInfoWR> threadSIWR) {
		this.threadSIWR = threadSIWR;
	}


	public ServerSocket getSsocketInfo() {
		return SsocketInfo;
	}


	public void setSsocketInfo(ServerSocket ssocketInfo) {
		SsocketInfo = ssocketInfo;
	}

	
	
}
