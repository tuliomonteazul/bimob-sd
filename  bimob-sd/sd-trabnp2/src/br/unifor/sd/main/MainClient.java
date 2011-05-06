package br.unifor.sd.main;

import java.io.IOException;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.client.impl.ClientConnectionTCP;

public class MainClient {
	public static void main(String[] args) {
		ClientConnection clientConnection = ClientConnectionTCP.getInstance();
		clientConnection.connect(null);
		try {
			
			clientConnection.send("mensagem de teste!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
