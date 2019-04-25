package client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import server.Server;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class ThreadReceiveMusic extends Thread {

	private Client client;

	public ThreadReceiveMusic(Client c) {
		client = c;
	}

	public void run() {

		System.out.println("UDP Multicast Time Client Started");
		try {
			
			Thread.sleep(100);
			
			DatagramSocket socketMusic = new DatagramSocket(); // asigna un puerto aleatoriamente

			byte[] firstData = "holi".getBytes();
			InetAddress adressServer = InetAddress.getByName(client.getIpServer());
			DatagramPacket firstPakcet = new DatagramPacket(firstData,firstData.length, adressServer, Server.PORT_MUSIC);
			socketMusic.send(firstPakcet);

//			byte[] data = new byte[21];
//			DatagramPacket packet = new DatagramPacket(data, data.length);
//			socketMusic.receive(packet);
//			String message = new String(packet.getData());
//			System.out.println(" Message: [" + message + "]");
			
			
			
			int packetsize = 1024;
			FileOutputStream fos = null;
			fos = new FileOutputStream(Client.MUSIC_ROOT);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			double nosofpackets = Math.ceil(((int) (new File("Music/elektromania.wav")).length()) / packetsize);
			byte[] mybytearray = new byte[packetsize];
			DatagramPacket receivePacket = new DatagramPacket(mybytearray,mybytearray.length);

			System.out.println(nosofpackets + " " + mybytearray + " "+ packetsize);

			for (double i = 0; i < nosofpackets + 1; i++) {

				socketMusic.receive(receivePacket);
				byte audioData[] = receivePacket.getData();
//				System.out.println("Packet:" + (i + 1));
				bos.write(audioData, 0, audioData.length);
			}
			
			playMusic();

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("UDP Multicast Time Client Terminated");

	}

	private void playMusic() throws Exception {
		
		InputStream music = new FileInputStream(new File(Client.MUSIC_ROOT));
		AudioStream audio = new AudioStream(music);
		AudioPlayer.player.start(audio);
		
	}

}
