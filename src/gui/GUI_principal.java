	package gui;

	import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.Client;

	public  class GUI_principal extends JFrame
	{
		private Gui_SignUp jdSignUp;
		private Gui_LogIn jdLogIn;
		private FirstPanel jdFirstPanel;
		private Gui_IP jdIp;
		private Gui_WaitingRoom jdWaitingRoom;
		
		private boolean connectionResult;
		
		private Client player;

		public GUI_principal() throws IOException {
			//Font normal = new Font("Arial", Font.BOLD, 14);
			setTitle("Icesi Games SA - AgarIO");	
			setLayout(new BorderLayout());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setMinimumSize(new Dimension(700, 600));
			setResizable(false);
			setLocationRelativeTo(null);
			
			jdSignUp = new Gui_SignUp(this);
			jdLogIn = new Gui_LogIn(this);
			jdFirstPanel = new FirstPanel(this);
			jdIp= new Gui_IP(this);
			
			
			player= new Client(this);
		}
		
		public void jdIp() {
			jdIp.setVisible(true);
		}
		
		public void firstPanel() {
			jdFirstPanel.setVisible(true);
		}
		
		public void jdLogIn() {
			jdLogIn.setVisible(true);
		}
		
		public void jdSignUp() {
			jdSignUp.setVisible(true);
		}
		
		public void oldPlayer(String email, String password) throws IOException {
			player.loginPlayer(email,password);
		}
		public void newPlayer(String email, String nickname, String password) throws IOException {
			player.registerPlayer(email, password, nickname);
		}
	
		public void connectServer(String ip) {
			//intenta conectarse al server
			//en caso de que pueda conectar, hace dispose() del JDialog ip y llama a firstPanel()
			//caso contrario, vuelve a mostrarlo para que re ingrese la ip
			
			jdIp.dispose();
			firstPanel();
		}
		
		
		public void goToWaitingRoom(){
			
			jdWaitingRoom = new Gui_WaitingRoom(this);
			jdWaitingRoom.setVisible(true);
			
		}
		
		public void refreshWR(String[] data){
			jdWaitingRoom.refresh(data);
			pack();
		}
		
		
		public static void main(String[] args) {
			
			try {
				
				GUI_principal principal;
				principal = new GUI_principal();
				principal.jdIp();
				
				
				//principal.firstPanel();
				
				
			} catch (IOException e) {
				
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		public Client getPlayer() {
			return player;
		}

		public void setPlayer(Client player) {
			this.player = player;
		}

		public void connectionResult(String result) {
			
			JOptionPane.showMessageDialog(this, result);
			
		}

		public boolean isConnectionResult() {
			return connectionResult;
		}

		public void setConnectionResult(boolean connectionResult) {
			this.connectionResult = connectionResult;
		}
	}
