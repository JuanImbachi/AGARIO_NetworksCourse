package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import world.AgarIO;

public class ThreadWaitingSpectators extends Thread {

	private Server server;

	public ThreadWaitingSpectators(Server s) {

		server = s;

	}

	@Override
	public void run() {

		try {

			DatagramSocket socket = new DatagramSocket(Server.PORT_UDP);

			byte[] data = new byte[1024];

			while (!server.getGame().getStatus().equals(AgarIO.GAME_FINISHED)) {

				DatagramPacket firstPacket = new DatagramPacket(data,
						data.length);

				socket.receive(firstPacket);

				int clientPort = firstPacket.getPort();
				InetAddress clientAddress = firstPacket.getAddress();
				ThreadSendInfoUDP th = new ThreadSendInfoUDP(server, clientAddress, clientPort);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	

}
