package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import server.Server;

public class ThreadWaitingRoom extends Thread {

	private Client client;

	private Socket socketWR;

	public ThreadWaitingRoom(Client c) throws UnknownHostException, IOException {
		client = c;
		socketWR = new Socket(Server.IP_SERVER, Server.PORT_WR);
	}

	@Override
	public void run() {

		try {
			
			String info[] = null;
            String lastTime="0";
            

			DataInputStream in = new DataInputStream(socketWR.getInputStream());
            
            
			while (client.isWaitingForPlay()) {
                
				
				info = in.readUTF().split(",");
				while(info[0].equals(lastTime)){
					info = in.readUTF().split(",");
				}
				
				lastTime=info[0];

				

				client.refreshWaitingRoom(info);

				
//				this.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
