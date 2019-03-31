package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ThreadInfoGameServer extends Thread {

	private Server server;

	private boolean firstSend;

	public ThreadInfoGameServer(Server s) {

		server = s;

		firstSend = false;
	}

	@Override
	public void run() {

		try {

			Socket socket = server.getServerSocketGame().accept();

			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());
			
			while (server.isRunningGame()) {

				if (!firstSend) {

					out.writeUTF(server.sendInfoFirstTime());
					firstSend = true;
					
					

				} else {
					
					String info = server.infoGame();
					
					

					out.writeUTF(info);
				
					
					

				}

				String received = in.readUTF();
		

				String[] s1 = received.split(",");
				String[] player = s1[0].split("/");
				String[] food = new String[0];
				if (s1.length > 1) {
					food = s1[1].split("/");
				}

				server.updateGame(player, food);
				
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
