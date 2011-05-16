package br.unifor.sd.connection.factory;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.client.impl.ClientConnectionTCP;
import br.unifor.sd.connection.client.impl.ClientConnectionUDP;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.connection.server.impl.ServerConnectionTCP;
import br.unifor.sd.connection.server.impl.ServerConnectionUDP;

public class ConnectionFactory {

	private static ServerConnection serverConnection;
	private static ClientConnection clientConnection;
	
	public static ClientConnection createClient(ConnectionProtocol protocol) {
		if (clientConnection == null) {
			switch (protocol) {
			case TCP:
				clientConnection = ClientConnectionTCP.getInstance();
				break;
			case UDP:
				clientConnection = ClientConnectionUDP.getInstance();
				break;
			}
		}
		return clientConnection;
	}
	
	public static ServerConnection createServer(ConnectionProtocol protocol) {
		if (serverConnection == null) {
			switch (protocol) {
			case TCP:
				serverConnection = ServerConnectionTCP.getInstance();
				break;
			case UDP:
				serverConnection = ServerConnectionUDP.getInstance();
				break;
			}
		}
		return serverConnection;
	}
	
	public static ClientConnection getClientConnection() {
		return clientConnection;
	}
	
	public static ServerConnection getServerConnection() {
		return serverConnection;
	}
	
}
