package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import server.Server;

public class ThreadReceiveInfoUDP extends Thread {
	
	private Client client;
	
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
			
			
			String[] fInfoBig = firstInfo.split("_");

			String[] fInfoPlayers = fInfoBig[0].split(",");

			String[] fInfoBalls = fInfoBig[1].split(",");

			client.initializeWorld(fInfoPlayers, fInfoBalls);
			
			
			while(true){
				byte[] information = new byte[1024];
				DatagramPacket receive = new DatagramPacket(information, information.length);
				socket.receive(receive);
				
				String info = new String(receive.getData());
				
				String[] infoBig = info.split("_");

				String[] infoPlayers = infoBig[0].split(",");


				String[] infoBalls = infoBig[1].split(",");

				client.updateGame(infoPlayers, infoBalls);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
