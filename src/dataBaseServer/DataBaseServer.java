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
import java.util.Scanner;

import server.Server;
import server.ThreadWaitingClients;

public class DataBaseServer {

	public final static String REGISTER_DB = "register_DB";

	public final static String LOGIN_DB = "login_DB";

	public static final int DB_PORT = 35000;

	public final static String ROOT = "UsersDB/usersDB.txt";

	private ServerSocket serverSocket;

	private boolean waitingClients;

	private Server server;

	private ThreadWaitingClientsDB threadWC_DB;

	public DataBaseServer(Server s) throws IOException {
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

	public boolean loginPlayer(String nick, String pass) {
		
			try {
			   
				File text = new File(ROOT);
				
				FileReader reader = new FileReader(text);
				
				BufferedReader in = new BufferedReader(reader);

				boolean cond = false;
				
				String info[] = in.readLine().split(",");
				while (!info[0].equals("")) {
					String theNick = info[0];
					String thePass = info[1];
					if (nick.equals(theNick) && pass.equals(thePass)) {

						cond = true;
						
					}

					info = in.readLine().split(",");
				}
				
				return cond;

				
			} catch (Exception e) {
				return false;
			}
			
	

	}

	public boolean registerPlayer(String nick, String pass, String email) {
		String information = nick + "," + pass + "," + email;
		try {
			
			boolean r =false;
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
				
				r=true;

			}
			
			return r;
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
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

}
