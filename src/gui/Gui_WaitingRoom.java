package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class Gui_WaitingRoom extends JDialog  {

	private JLabel lblTitle,lblTimer,lblInfo;
	private JList jlPlayers;
	private DefaultListModel listModel;
	
	private GUI_principal principal;
	
	public Gui_WaitingRoom(GUI_principal pri) {
		principal=pri;
		listModel = new DefaultListModel();
		
		setTitle("Icesi Games SA - AgarIO");
		setLayout(new BorderLayout());
		this.setSize(new Dimension(230, 300));
		
		lblTitle = new JLabel("\n      Agar.IO      \n");
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		Font font = new Font("Snap ITC", Font.PLAIN, 36);
		lblTitle.setFont(font);
		
		lblTimer = new JLabel("00:00");
		lblTimer.setHorizontalAlignment(JLabel.CENTER);
		
		lblInfo = new JLabel("La partida comenzará en: ");
		lblInfo.setHorizontalAlignment(JLabel.CENTER);
		
		
		jlPlayers = new JList();
		jlPlayers.setModel(listModel);
		JScrollPane scrollPane = new JScrollPane(jlPlayers); 
		scrollPane.setBounds(10,30,200,110); 
		
		jlPlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlPlayers.setLayoutOrientation(JList.VERTICAL);
		
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		TitledBorder border1 = BorderFactory.createTitledBorder("Players in room");
		border1.setTitleColor(Color.BLUE);
		p1.setBorder(border1);
		p1.add(scrollPane);
		
		JPanel p2= new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(lblTitle);
		
		JPanel p3= new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(lblInfo); p3.add(lblTimer);

		add(p2, BorderLayout.NORTH);
		add(p1, BorderLayout.CENTER);
		add(p3,BorderLayout.SOUTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
	}
	
	
	public void refresh(String[] data){
		lblTimer.setText(data[0]);
	}
	

	public void addPlayer(String nickname) {
		listModel.addElement(nickname);
	}
	
	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public JLabel getLblTimer() {
		return lblTimer;
	}

	public void setLblTimer(JLabel lblTimer) {
		this.lblTimer = lblTimer;
	}

	public JLabel getLblInfo() {
		return lblInfo;
	}

	public void setLblInfo(JLabel lblInfo) {
		this.lblInfo = lblInfo;
	}

	public JList getJlPlayers() {
		return jlPlayers;
	}

	public void setJlPlayers(JList jlPlayers) {
		this.jlPlayers = jlPlayers;
	}

	public DefaultListModel getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}

}
