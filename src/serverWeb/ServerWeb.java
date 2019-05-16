package serverWeb;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import dataBaseServer.DataBaseServer;

public class ServerWeb {

	
	public boolean webService ;
	public ServerSocket serverSocketWebService;
	public static final int PORT_WEB_SERVICE = 7000;
	private HiloDespliegueWeb hiloDespliegueAppWeb;
	private DataBaseServer dbServer;
	private SSLSocket clientConnectionDB;

	public ServerWeb() {
		System.setProperty("javax.net.ssl.trustStore", "myTrustStore.jts");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		webService = true;
		try {
			dbServer = new DataBaseServer();
			serverSocketWebService = new ServerSocket(PORT_WEB_SERVICE);

			
			

			hiloDespliegueAppWeb = new HiloDespliegueWeb(this);
			hiloDespliegueAppWeb.start();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	public String connectWithDB(String message) {
		String nickname=null;

		try {

			SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			
			 clientConnectionDB = (SSLSocket)factory.createSocket("localhost", DataBaseServer.DB_PORTWEB);
			
			DataInputStream in = new DataInputStream(
					clientConnectionDB.getInputStream());
			DataOutputStream out = new DataOutputStream(
					clientConnectionDB.getOutputStream());

			out.writeUTF(message);
			
			String result = in.readUTF();
			
		//	gui.connectionResult(result);
			boolean r = false;
			if (result.equals(DataBaseServer.CONF_ACCESS)
					|| result.equals(DataBaseServer.PLAYER_SAVED)) {
				r = true;
				String information = in.readUTF();
				nickname = information;
				
//				musicRoot = "gameMusic"+information+".wav";

			//	connectWithServer();
			}
		//	gui.setConnectionResult(r);
			
			clientConnectionDB.close();
			
		} catch (Exception e) {
			System.out.println("Error connecting to data base");
		}
		return nickname;
	}
	public String infoUser(String nickname) {
		
		return dbServer.infoGamesPlayer(nickname);
	}

	/**
	 * @return the webService
	 */
	public boolean isWebService() {
		return webService;
	}

	/**
	 * @param webService the webService to set
	 */
	public void setWebService(boolean webService) {
		this.webService = webService;
	}

	/**
	 * @return the serverSocketWebService
	 */
	public ServerSocket getServerSocketWebService() {
		return serverSocketWebService;
	}

	/**
	 * @param serverSocketWebService the serverSocketWebService to set
	 */
	public void setServerSocketWebService(ServerSocket serverSocketWebService) {
		this.serverSocketWebService = serverSocketWebService;
	}

	/**
	 * @return the hiloDespliegueAppWeb
	 */
	public HiloDespliegueWeb getHiloDespliegueAppWeb() {
		return hiloDespliegueAppWeb;
	}

	/**
	 * @param hiloDespliegueAppWeb the hiloDespliegueAppWeb to set
	 */
	public void setHiloDespliegueAppWeb(HiloDespliegueWeb hiloDespliegueAppWeb) {
		this.hiloDespliegueAppWeb = hiloDespliegueAppWeb;
	}
	
	public static void main(String[] args) {
		ServerWeb sw = new ServerWeb();
	}
	
}
