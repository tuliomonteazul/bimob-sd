package br.unifor.sd.main;

import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.connection.server.impl.ServerConnectionTCP;
import br.unifor.sd.connection.server.listener.ConnectionEvent;
import br.unifor.sd.connection.server.listener.ServerConnectionListener;
import br.unifor.sd.entity.Jogador;

public class Server {
	public static void main(String[] args) {
		ServerConnection serverConnection = ServerConnectionTCP.getInstance();
		serverConnection.startServer(new ServerConnectionListener() {
			
			@Override
			public void receive(ConnectionEvent event) {
				System.out.println(event.getAddress().getHostName() + event.getObject());
				
				if (event.isConnectRequest()) {
					event.acceptConnection();
				} else {
					Jogador jogador = (Jogador) event.getObject();
					System.out.println(jogador.getNome() + " - " + jogador.getAddress());
				}
			}
		});
	}
}
