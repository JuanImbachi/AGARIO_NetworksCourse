package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui_LogIn extends JDialog implements ActionListener {

	public final static String LOG_IN="Log in";
	
	private JLabel lblTitle, lblEmail, lblPassword;
	
	private JTextField txtEmail, txtPassword;
	
	private JButton btnLogIn;
	
	private GUI_principal principal;
	
	public Gui_LogIn(GUI_principal principal) {
		
		this.principal = principal;
		setTitle("Icesi Games SA - AgarIO");
		setResizable(false);
		
		this.setMinimumSize(new Dimension(350, 200));
		setLayout(new BorderLayout());
		
		lblTitle = new JLabel("Account Info");
		lblTitle.setHorizontalAlignment(JLabel.LEFT);
		Font font = new Font("Arial", Font.BOLD, 24);
		lblTitle.setFont(font);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(JLabel.CENTER);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(JLabel.CENTER);
		
		txtEmail = new JTextField();
		txtPassword = new JTextField();
		
		btnLogIn = new JButton(LOG_IN);
		btnLogIn.setActionCommand(LOG_IN);
		btnLogIn.addActionListener(this);
		
		JPanel p1= new JPanel();
		p1.setLayout(new FlowLayout());
		
		JPanel p2= new JPanel();
		p2.setLayout(new GridLayout(5,2));
		
		JPanel p3= new JPanel();
		p3.setLayout(new FlowLayout());
		
		p1.add(lblTitle);
		
		p2.add(new JLabel(""));p2.add(new JLabel(""));
		p2.add(lblEmail); p2.add(txtEmail);
		p2.add(new JLabel(""));p2.add(new JLabel(""));
		p2.add(lblPassword); p2.add(txtPassword);
		p2.add(new JLabel(""));p2.add(new JLabel(""));

		p3.add(btnLogIn);
		
		add(p1, BorderLayout.NORTH);
		add(p2,BorderLayout.CENTER); add(p3,BorderLayout.SOUTH);
		add(new JLabel("    "),BorderLayout.EAST);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		if(command.equals(LOG_IN)) {
			this.dispose();
		}
	}

}
