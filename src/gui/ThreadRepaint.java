package gui;

import world.AgarIO;

public class ThreadRepaint extends Thread{

	public GUI_principal principal;
	
	public ThreadRepaint(GUI_principal principal)  {

		this.principal = principal;
		this.start();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (principal.getAgario().getStatus().equals(AgarIO.PLAYING)) {
				principal.repaint();
			
		}
		
	}

}
