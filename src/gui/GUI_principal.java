	package gui;

	import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;

	public  class GUI_principal extends JFrame
	{
		private Gui_SignUp jdSignUp;
		private Gui_LogIn jdLogIn;
		private FirstPanel jdFirstPanel;
		private Gui_IP jdIp;

		public GUI_principal() {
			//Font normal = new Font("Arial", Font.BOLD, 14);
			setTitle("Icesi Games SA - AgarIO");	
			setLayout(new BorderLayout());
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setMinimumSize(new Dimension(700, 600));
			setResizable(false);
			setLocationRelativeTo(null);
			
			jdSignUp = new Gui_SignUp(this);
			jdLogIn = new Gui_LogIn(this);
			jdFirstPanel = new FirstPanel(this);
			jdIp= new Gui_IP(this);
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
		
		public void oldPlayer(String email, String password) {
			System.out.println("jugador antiguo");
		}
		public void newPlayer(String email, String nickname, String password) {
			System.out.println("nuevo jugador");
		}
	
		public void connectServer(String ip) {
			//intenta conectarse al server
			//en caso de que pueda conectar, hace dispose() del JDialog ip y llama a firstPanel()
			//caso contrario, vuelve a mostrarlo para que re ingrese la ip
			
			jdIp.dispose();
			firstPanel();
		}
		
		public static void main(String[] args) {
			GUI_principal principal = new GUI_principal();
			//principal.firstPanel();
			principal.jdIp();
		}
	}
