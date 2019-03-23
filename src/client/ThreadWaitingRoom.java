package client;

public class ThreadWaitingRoom extends Thread {
	
	private Client client;
	
	public ThreadWaitingRoom(Client c){
		client = c;
	}
	

}
