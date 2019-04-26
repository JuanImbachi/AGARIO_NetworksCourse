package client;

public class ThreadWaitsSong extends Thread{
	
	
	private ThreadReceiveMusic th;
	
	public ThreadWaitsSong(ThreadReceiveMusic thh) {
		th = thh;
	}
	
	
	@Override
	public void run() {

		try {
		
			this.sleep(3000);
			
			th.setCond(true);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
	

}
