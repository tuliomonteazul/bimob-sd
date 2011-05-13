package br.unifor.sd.connection.factory;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.client.impl.ClientConnectionTCP;
import br.unifor.sd.connection.client.impl.ClientConnectionUDP;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.connection.server.impl.ServerConnectionTCP;
import br.unifor.sd.connection.server.impl.ServerConnectionUDP;

public class ConnectionFactory {

	public static ClientConnection createClient(ConnectionProtocol protocol) {
		ClientConnection connection = null;
		switch (protocol) {
		case TCP:
			connection = ClientConnectionTCP.getInstance();
			break;
		case UDP:
			connection = ClientConnectionUDP.getInstance();
			break;
		}
		return connection;
	}
	
	public static ServerConnection createServer(ConnectionProtocol protocol) {
		ServerConnection connection = null;
		switch (protocol) {
		case TCP:
			connection = ServerConnectionTCP.getInstance();
			break;
		case UDP:
			connection = ServerConnectionUDP.getInstance();
			break;
		}
		return connection;
	}
}
