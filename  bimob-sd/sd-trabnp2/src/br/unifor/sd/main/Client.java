package br.unifor.sd.main;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.client.impl.ClientConnectionTCP;

public class Client {
	public static void main(String[] args) {
		ClientConnection clientConnection = new ClientConnectionTCP();
		clientConnection.connect();
	}
}
