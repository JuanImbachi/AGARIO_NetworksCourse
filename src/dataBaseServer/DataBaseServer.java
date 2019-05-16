package dataBaseServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.security.Security;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;



import server.Server;
import server.ThreadWaitingClients;

public class DataBaseServer {

	public final static String CONF_ACCESS = "Access granted";
	public final static String DENIED_ACCESS = "Access denied";
	public final static String PLAYER_SAVED = "Player saved";
	public final static String PLAYER_NOTSAVED = "Player not saved";

	public final static String REGISTER_DB = "register_DB";

	public final static String LOGIN_DB = "login_DB";

	public static final int DB_PORT = 35000;
	public static final int DB_PORTWEB = 37700;

	public final static String ROOT = "UsersDB/usersDB.txt";
	public final static String GAMEROOT = "UsersDB/gamesDB.txt";


	private int numberOfClients;


	private boolean waitingClients;

	private Server server;
	
	private SSLServerSocket serverSocketSSL;

	private ThreadWaitingClientsDB threadWC_DB;
	
	private ThreadWebServiceDB threadWS;

	public DataBaseServer(Server s) throws IOException {
		


		
		System.setProperty("javax.net.ssl.keyStore", "myKeystore.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");

		
		SSLServerSocketFactory factory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
		setServerSocketSSL((SSLServerSocket) factory.createServerSocket(DB_PORT));
		
		numberOfClients = 0;
		threadWC_DB = new ThreadWaitingClientsDB(this);

		server = s;
		waitingClients = true;

		threadWC_DB.start();
		
	}
	
	public DataBaseServer() throws IOException {
		


		
		System.setProperty("javax.net.ssl.keyStore", "myKeystore.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");

		SSLServerSocketFactory factory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

		setServerSocketSSL((SSLServerSocket) factory.createServerSocket(DB_PORTWEB));

		initializeWebService();

	}
	
	
	
	public void initializeWebService() {

		threadWS = new ThreadWebServiceDB(this);
		
		threadWS.start();
	}
	



	public boolean isWaitingClients() {
		return waitingClients;
	}

	public void setWaitingClients(boolean waitingClients) {
		this.waitingClients = waitingClients;
	}

	public String loginPlayer(String email, String pass) {

		try {

			String r = null;

			File text = new File(ROOT);

			FileReader reader = new FileReader(text);

			BufferedReader in = new BufferedReader(reader);

			boolean cond = false;

			while (!cond) {
				String info[] = in.readLine().split(",");
				String theEmail = decryptWord(info[2]);
				String thePass = decryptWord(info[1]);
				if (email.equals(theEmail) && pass.equals(thePass)) {

					cond = true;
					r = decryptWord(info[0]);
				}

			}

			return r;

		} catch (Exception e) {
			return null;
		}

	}

	public String registerPlayer(String nick, String pass, String email) {
		String newNick = encryptWord(nick);
		String newPass = encryptWord(pass);
		String newEmail = encryptWord(email);
		
		String information = newNick + "," + newPass + "," + newEmail;
		try {

			String r = null;
			;
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			File text = new File(GAMEROOT);
			if (!text.exists()) {
				if (text.createNewFile()) {
					System.out.println("El fichero se ha creado correctamente");
				} else {
					System.out.println("No ha podido ser creado el fichero");
				}
			} else {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(text, true), "UTF8"));
				out.write(information);
				out.write("\n");
				out.close();

				r = nick;
				;

			}

			return r;

		} catch (IOException e) {
		
			return null;
		}
	}

	public static String encryptWord(String word) {

		char[] array = word.toCharArray();

		for (int i = 0; i < array.length; i++) {

			array[i] = (char) (array[i] + (char) 2);

		}

		return String.valueOf(array);
	}

	public static String decryptWord(String word) {

		char[] array = word.toCharArray();

		for (int i = 0; i < array.length; i++) {

			array[i] = (char) (array[i] - (char) 2);

		}

		return String.valueOf(array);
	}
	
	

	public ThreadWaitingClientsDB getThreadWC_DB() {
		return threadWC_DB;
	}

	public void setThreadWC_DB(ThreadWaitingClientsDB threadWC_DB) {
		this.threadWC_DB = threadWC_DB;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void addPlayer(String result) {

		server.addPlayer(result);

	}

	public int getNumberOfClients() {
		return numberOfClients;
	}

	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public SSLServerSocket getServerSocketSSL() {
		return serverSocketSSL;
	}

	public void setServerSocketSSL(SSLServerSocket serverSocketSSL) {
		this.serverSocketSSL = serverSocketSSL;
	}



	public void registerGame(String info) {
		
	
		String gameInfo = info;
		
		
		
		try {

		
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			File text = new File(GAMEROOT);
			if (!text.exists()) {
				if (text.createNewFile()) {
					System.out.println("El fichero se ha creado correctamente");
				} else {
					System.out.println("No ha podido ser creado el fichero");
				}
			} else {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(text, true), "UTF8"));
				out.write("\n");
				out.write(gameInfo);
				
				out.close();
			}

		

		} catch (IOException e) {
		
			System.out.println("La partida no pudo ser guardada");
		}
		
	}
	
	public String infoGamesPlayer(String nickname) {

		try {

			String r = "";

			File text = new File(GAMEROOT);

			FileReader reader = new FileReader(text);

			BufferedReader br = new BufferedReader(reader);

			String currentLine = br.readLine();
			
			while (currentLine!= null ) {
				String info[] = currentLine.split("-");
				String players = info[0];
				String date = info[1];
				String winner = info[2];
				String won="";
				String score = "";
				boolean played = false;
				if(winner.equals(nickname)) {
					won = "WIN";
				}else {
					won = "LOSE";
				}
				String[] eachPlayer = players.split("=");
				
				players = "";
				
				for (int i = 0; i < eachPlayer.length; i++) {
					
					String[] pl1 = eachPlayer[i].split(",");
					
					if(pl1[0].equals(nickname)) {
						score = pl1[1];
						played = true;
					}else {
						
						if(i==0) {
							players+= ":: ";
						}
						players+= pl1[0]+" :: ";
					}
		
				}
				
				if(played) {
					r += score+","+ date + ","+ players + "," + won+"=";
				}
				
				currentLine = br.readLine();
			}
			reader.close();
			br.close();

			return r;

		} catch (Exception e) {
			return "";
		}

	}

}
