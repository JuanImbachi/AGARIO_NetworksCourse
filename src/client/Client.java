package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import server.Server;



public class Client {
	
	private Ball ball;
	
	static Socket clientConnection; 
	
	public static void main(String[] args) {
        
	
		DataInputStream in;
		
		DataOutputStream out;
		 
		try {
			clientConnection = new Socket(Server.IP_SERVER, Server.PORT);
			
			while(true) {
			
			BufferedReader	clientReader = new BufferedReader(new InputStreamReader(System.in));
			String palabra=	clientReader.readLine();
			
			
			
			String n="";
			in = new DataInputStream(clientConnection.getInputStream());
			out = new DataOutputStream(clientConnection.getOutputStream());
			
			out.writeUTF(palabra);
			System.out.println(in.readUTF());
		}
//			clientConnection.close();
//			in.close();
//			out.close();
			
		} catch (Exception e) {
			System.out.println("Se generó una excepcion");
		} 

	}
	

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

}
