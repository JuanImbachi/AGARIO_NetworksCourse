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
			
			
			
			
			DataInputStream in = new DataInputStream(client.getGameSocket().getInputStream());
			
			DataOutputStream out = new DataOutputStream(client.getGameSocket().getOutputStream());
			
			while(client.isStartedGame()) {
				
				String info = in.readUTF();
				if(info.startsWith("#f#")) {
					
					String[] infoBig = info.split("*");
					
					String[] infoPlayers = infoBig[0].split("{");
					String[] infoBalls = infoBig[1].split("{");
					
					client.initializeWorld(infoPlayers,infoBalls);
					
					
					
					
					
				}else {
					
					
					double x = client.getGame().getPlayer(0).getPosX();
					double y = client.getGame().getPlayer(0).getPosY();
					
					int size = client.getGame().getPlayer(0).getRadio();
					
					boolean isPlaying = client.getGame().getPlayer(0).isPlaying();
					out.writeUTF(x+"/"+y+"/"+size+"/"+isPlaying);
					
					
					
				}
				
				
//				System.out.println(in.readUTF());
				
//				out.writeUTF("Hi im a client and my nickname is: "+client.getNickname());
				
				
				
				
				
				
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
