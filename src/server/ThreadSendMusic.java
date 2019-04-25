package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class ThreadSendMusic extends Thread{
	
	private Server server;
	
	public ThreadSendMusic(Server s){
		server = s;
	}
	
	
	public void run() {
		System.out.println("UDP Multicast Time Server Started");
		try {

			
			DatagramSocket socketMusic = new DatagramSocket(Server.PORT_MUSIC);
			
			byte[] data = new byte[1024];
			DatagramPacket packet;
			
			
			while(true)
			{
				
				DatagramPacket firstPacket = new DatagramPacket(data, data.length);
				socketMusic.receive(firstPacket);
				int clientPort = firstPacket.getPort();
				InetAddress clientAddress = firstPacket.getAddress();
				
//				String message =  "HELLO FROM THE SERVER";
//				System.out.println("Sending: [" + message + "]");
//				data =  message.getBytes();
//				packet =  new DatagramPacket(data, data.length, adress, clientPort);			
//				socketMusic.send(packet);
				
				send(clientAddress, clientPort);

				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println(
			"UDP Multicast Time Server Terminated");
	}
	
	
	public void send(InetAddress clientAddress,int clientPort) throws IOException, InterruptedException {
		File myFile = new File("Music/elektromania.wav");
		DatagramSocket ds = null;
		BufferedInputStream bis = null;
		try {
			ds = new DatagramSocket();
			DatagramPacket dp;
			int packetsize = 1024;
			double nosofpackets;
			nosofpackets = Math.ceil(((int) myFile.length()) / packetsize);

			bis = new BufferedInputStream(new FileInputStream(myFile));
			for (double i = 0; i < nosofpackets + 1; i++) {
				byte[] mybytearray = new byte[packetsize];
				bis.read(mybytearray, 0, mybytearray.length);
//				System.out.println("Packet:" + (i + 1));
				dp = new DatagramPacket(mybytearray, mybytearray.length,clientAddress ,clientPort);
				ds.send(dp);
			}
		} finally {
			if (bis != null)
				bis.close();
			if (ds != null)
				ds.close();
		}

	}
	

}
