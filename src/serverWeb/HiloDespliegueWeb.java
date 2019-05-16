package serverWeb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloDespliegueWeb extends Thread {

	ServerWeb server;
	
	public HiloDespliegueWeb(ServerWeb server) {

		this.server = server;
	}

	public void run() {
		
		while(server.webService) {
			System.out.println(":::Web Server Started:::");
			ServerSocket serverSocket = server.getServerSocketWebService();
			try {
				Socket cliente = serverSocket.accept();
				HiloClientHandlerWeb hilo = new HiloClientHandlerWeb(cliente, server);
				hilo.start();	
				
			} catch (IOException e) {
				System.out.println("Exception in HiloDespliegueAppWeb");
			}
			
		}
		
	}
}
