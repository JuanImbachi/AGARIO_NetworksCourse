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
import java.util.ArrayList;
import java.util.Scanner;

import server.Server;
import server.ThreadWaitingClients;

public class DataBaseServer {

	public final static String CONF_ACCESS = "acces_confirmed";
	public final static String DENIED_ACCESS = "acces_denied";
	public final static String PLAYER_SAVED = "player_saved";
	public final static String PLAYER_NOTSAVED = "player_notsaved";

	public final static String REGISTER_DB = "register_DB";

	public final static String LOGIN_DB = "login_DB";

	public static final int DB_PORT = 35000;

	public final static String ROOT = "UsersDB/usersDB.txt";

	private int numberOfClients;

	private ServerSocket serverSocket;

	private boolean waitingClients;

	private Server server;

	private ThreadWaitingClientsDB threadWC_DB;

	public DataBaseServer(Server s) throws IOException {
		numberOfClients = 0;
		serverSocket = new ServerSocket(DB_PORT);
		threadWC_DB = new ThreadWaitingClientsDB(this);

		server = s;
		waitingClients = true;

		threadWC_DB.start();
	}

	public ServerSocket getServerSocket() {
		// holas

		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
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
			File text = new File(ROOT);
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
			// TODO Auto-generated catch block
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

}
