package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;


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
					
					info=info.substring(3);
					
					System.out.println(info);
					
					String[] infoBig = info.split("_");
					
					String[] infoPlayers = infoBig[0].split(",");
					String[] infoBalls = infoBig[1].split(",");
					
					client.initializeWorld(infoPlayers,infoBalls);
					
					
					
				}else {
					
//					System.out.println(info);
					String[] infoBig = info.split("_");
					
					String[] infoPlayers = infoBig[0].split(",");
					String[] infoBalls = infoBig[1].split(",");
					
					client.updateGame(infoPlayers, infoBalls);
					
				}
				
				
				int id =  client.getId();
//				System.out.println(id);
				double x = client.getGame().getPlayer(id).getPosX();
				double y = client.getGame().getPlayer(id).getPosY();
				boolean isPlaying = client.getGame().getPlayer(id).isPlaying();
				 int mass = client.getGame().getPlayer(id).getMass();
				
				
				ArrayList<Integer> eatenBalls= client.getEatenBalls();
				client.setEatenBalls(new ArrayList<Integer>());
				
				String b = "";
				for(int i=0;i<eatenBalls.size();i++) {
					if(i<eatenBalls.size()-1) {
						String m = eatenBalls.get(i)+"/";
						b+=m;
					}else{
						b+=(eatenBalls.get(i)+"");
					}
				}
				
				out.writeUTF(id+"/"+x+"/"+y+"/"+isPlaying+"/"+mass+","+b);
				
				
				
				
				
				
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
