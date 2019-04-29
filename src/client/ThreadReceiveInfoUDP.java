package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import server.Server;

public class ThreadReceiveInfoUDP extends Thread {
	
	private Client client;
	
	private ViewersWR window;
	
	public ThreadReceiveInfoUDP(Client c){
		client = c;
	}
	
	@Override
	public void run() {

		try {
		
			DatagramSocket socket = new DatagramSocket();
			byte[] first = "holi".getBytes();
			DatagramPacket fPacket = new DatagramPacket(first, first.length,InetAddress.getByName(client.getIpServer()),Server.PORT_UDP);
			socket.send(fPacket);
			
			byte[] fInfo = new byte[1024];
			fPacket = new DatagramPacket(fInfo, fInfo.length);
			socket.receive(fPacket);
			
			String firstInfo = new String(fPacket.getData());
			
			String[] a = firstInfo.split("   ");
			
			firstInfo = a[0];
			
			initizalizeWindow();
			
			
			boolean cond = false;
			
			while(!cond){
//				System.out.println(firstInfo);
				if(firstInfo.startsWith("#f#")){

                    closeWindow();
					
					firstInfo = firstInfo.substring(3);
					
					System.out.println("FIRST INFO: "+firstInfo);
                    
					String[] fInfoBig = firstInfo.split("_");

					String[] fInfoPlayers = fInfoBig[0].split(",");

					String[] fInfoBalls = fInfoBig[1].split(",");

					client.initializeWorld(fInfoPlayers, fInfoBalls);
					
				}else if(firstInfo.startsWith("#end#")){
					
					
					cond=true;
					
					
					
				}else if(firstInfo.startsWith("WAITING")){
				
				
				}else{
					
					String info = firstInfo;
					
					String[] infoBig = info.split("_");

					String[] infoPlayers = infoBig[0].split(",");


					String[] infoBalls = infoBig[1].split(",");

					client.updateGame(infoPlayers, infoBalls);
					
					
					
				}
				
				fInfo = new byte[1024];
				fPacket = new DatagramPacket(fInfo, fInfo.length);
				socket.receive(fPacket);

				firstInfo = new String(fPacket.getData());
				
				a = firstInfo.split("   ");
				
				firstInfo = a[0];
				
			}
			
			client.stopGame();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
		
	}

	private void closeWindow() {
		
		window.setVisible(false);
	}

	private void initizalizeWindow() {
		
		System.out.println("STARTS WR VIEWERS WINDOW");
		
		window = new ViewersWR();
		window.setVisible(true);
	}

}
