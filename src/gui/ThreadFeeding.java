package gui;

public class ThreadFeeding extends Thread{

    private static final int TIME_FEED = 1000;  

    private GUI_principal principal;
	public ThreadFeeding(GUI_principal principal) {
		
		this.principal = principal;
	}

	public void run() {
		while(true) {
			try {
				principal.createFood();
				Thread.sleep(TIME_FEED);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
