package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import world.AgarIO;
import world.Ball;
import world.PlayerBall;
public class Gui_Game extends JPanel implements MouseMotionListener{
	
    private ArrayList<PlayerBall> players;
    private ArrayList<Ball> foods;
    private  GUI_principal principal;
    
    public Gui_Game(ArrayList<PlayerBall> players, ArrayList<Ball> foods, GUI_principal principal) {
		// TODO Auto-generated constructor stub
    	this.players = players;
    	this.foods = foods;
    	this.principal = principal;
    	addMouseMotionListener(this);
	}
    
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
	}
	 @Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		
		g.setColor(new Color(220, 220, 220));
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, AgarIO.GAME_WIDTH, AgarIO.GAME_HEIGHT);
        
        g.setColor(new Color(220, 220, 220));
        
        int espacio = 40;
        for (int i=0; i < this.getWidth(); i+=espacio)
            g.drawLine(i, 0, i, this.getHeight());                    
        for (int j=0; j < this.getHeight(); j+=espacio)
            g.drawLine(0, j, this.getWidth(), j);
        
        
        if(this.players != null) {
        	showPlayers(players, g);
        }else {
        	System.out.println("Players null");
        }
	
        
        if(this.foods != null) {
        	showFoods(foods, g);
        }else {
        	System.out.println("Foods null");
        }
        
        ArrayList<PlayerBall> playersTop = principal.getPlayersTop();
        paintTop(playersTop, g);
        
        g.dispose();
	 }
	
	 private void showPlayers(ArrayList<PlayerBall> players , Graphics g) {
		 for (int i = 0; i < players.size(); i++) {
			PlayerBall player = players.get(i);
			
			double x = player.getPosX();
			double y = player.getPosY();
			int r = player.getRadio();
			Font font = new Font("Century Schoolbook",Font.BOLD,r/2);
            FontMetrics metrics = g.getFontMetrics(font);
            int xt =(int) x - metrics.stringWidth(player.getNickname())/2;
            int yt = (int) (y + r/4) ;
            g.setFont(font);
            
            g.drawString(player.getNickname(), xt , yt);
			
		}
	 }
	 
	 private void showFoods(ArrayList<Ball> foods, Graphics g) {
		 
		 for (int i = 0; i < foods.size(); i++) {
			Ball food = foods.get(i);
			 int r = food.getRadio();
		        g.setColor(food.getColor());
		        g.fillOval((int) (food.getPosX() - r), (int) (food.getPosY() - r), 2*r, 2*r);
		        g.setColor(Color.BLACK);
		        g.drawOval((int) (food.getPosX() - r), (int) (food.getPosY() - r), 2*r, 2*r);    
		   
		}
	 }
	 
	 private void paintTop(ArrayList<PlayerBall> playersTop, Graphics g) {
		 g.setColor(Color.DARK_GRAY);
	        g.setFont(new Font("Century Schoolbook",Font.BOLD,15));
	        g.drawString("TOP PLAYERS", (int) this.getWidth()-150, 30);
	        g.drawString("-----------------------", (int) this.getWidth()-150, 40);
	        int i = 30;
	        int pos = 1;
	        for (PlayerBall p : playersTop) {
	            i += 20;
	            g.setFont(new Font("Century Schoolbook",Font.BOLD,12));
	            g.drawString(pos+". "+p.getNickname() + ':', (int) this.getWidth()-150, i);
	            g.drawString(p.getMass() + "", (int) this.getWidth()-75, i);
	            pos += 1;
	        }
	 }

	




	public void mouseDragged(MouseEvent e) {
//		System.out.println("DRAGGED");
		
		principal.posXMouse=e.getX();
		principal.posYMouse = e.getY();
		
	}

	public void mouseMoved(MouseEvent e) {
		
		
	}
}
