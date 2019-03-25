package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class ThreadInfoGameClient extends Thread {
	
	
	private Client client;
	
	
	
	public ThreadInfoGameClient(Client c) {
		
		client = c;
		
		
		
		
		
	}
	
	
	@Override
	public void run() {
		try {
			
			
			
			
			DataInputStream in = new DataInputStream(client.getClientSocket().getInputStream());
			
			DataOutputStream out = new DataOutputStream(client.getClientSocket().getOutputStream());
			
			while(client.isStartedGame()) {
				
				
				
				
				System.out.println(in.readUTF());
				
				out.writeUTF("Hi im a client and my nickname is: "+client.getNickname());
				
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Client getServer() {
		return client;
	}

	public void setServer(Client client) {
		this.client = client;
	}


	

}
