package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ThreadWaitingClients extends Thread {

	private Server server;

	public ThreadWaitingClients(Server s) {
		server = s;

	}

	@Override
	public void run() {

		while (server.isWaitingClients()) {
			try {

				Socket socket = server.getServerSocket().accept();
				// server.agregarSocketAActivos(socket);
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				String mensaje = in.readUTF();
				System.out.println("Cedula : " + mensaje.split(",")[0] + " Caballo : " + mensaje.split(",")[1]
						+ " Monto : $" + mensaje.split(",")[2]);
				// server.agregarClienteParaPersistir(mensaje);
				// server.apostarPorCaballo(mensaje);
				out.writeUTF("CONFIRMADO");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
