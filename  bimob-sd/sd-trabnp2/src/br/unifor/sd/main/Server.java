package br.unifor.sd.main;

import br.unifor.sd.connection.server.ConnectionEvent;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.connection.server.ServerConnectionListener;
import br.unifor.sd.connection.server.impl.ServerConnectionTCP;

public class Server {
	public static void main(String[] args) {
		ServerConnection serverConnection = new ServerConnectionTCP();
		serverConnection.startServer(new ServerConnectionListener() {
			
			@Override
			public void receive(ConnectionEvent event) {
				System.out.println(event.getAddress().getHostName() + event.getMsgs()[0]);
			}
		});
	}
}
