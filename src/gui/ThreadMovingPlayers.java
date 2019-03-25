package gui;

import java.awt.MouseInfo;
import java.awt.Point;

public class ThreadMovingPlayers extends Thread{

		public static final int TIME = 20;
		
		private int id;
		private GUI_principal principal;
	    private double posXfinal,posYfinal;
	    
	    public ThreadMovingPlayers(int id,GUI_principal pincipal) {
	    	this.id = id;
	    	this.principal = principal;
	    	this.posXfinal = 0;
	    	this.posYfinal = 0;
	    }

	    public void run() {
	    	while(true) {
	    		
	    		try {
	    			this.updatePositionMouse();
		    		principal.movePlayer(id, posXfinal, posYfinal);
		    		principal.checkCollisionPlayerFood(id);
					Thread.sleep(TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}
	    }
		
	
	 private void updatePositionMouse(){
		 Point mouse = MouseInfo.getPointerInfo().getLocation();
		 Point window = principal.getLocationOnScreen();
		 this.posXfinal = mouse.x - window.x;
		 this.posYfinal = mouse.y - window.y;
	 }
}
