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
		
		public void newPlayer(String email, String nickname, String password) {
			System.out.println("nuevo jugador");
		}
	
		public static void main(String[] args) {
			GUI_principal principal = new GUI_principal();
			principal.firstPanel();
		}
	}
