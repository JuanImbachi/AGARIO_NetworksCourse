package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThreadSendInfoUDP extends Thread {

	private Server server;

	private InetAddress address;

	private int port;

	public ThreadSendInfoUDP(Server s, InetAddress a, int p) {
		server = s;
		address = a;
		port = p;
	}

	@Override
	public void run() {
		try {

			DatagramSocket socket = new DatagramSocket();
			
			byte[] waiting = "WAITING".getBytes();

			while (!server.getGame().isRunning()) {

				DatagramPacket packet = new DatagramPacket(waiting, waiting.length,address,port); 
				socket.send(packet);
				
			}
			
			byte[] send = server.sendInfoFirstTime().getBytes();
			DatagramPacket packet = new DatagramPacket(send,send.length,address,port);
			socket.send(packet);
			
		    while (server.getGame().isRunning()) {

				send = server.infoGame().getBytes();
				DatagramPacket packet1 = new DatagramPacket(send,send.length,address,port);
				socket.send(packet1);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
