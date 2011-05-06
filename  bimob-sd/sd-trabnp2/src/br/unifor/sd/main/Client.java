package br.unifor.sd.main;

import java.io.IOException;
import java.net.InetAddress;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.client.impl.ClientConnectionTCP;
import br.unifor.sd.entity.Jogador;

public class Client {
	public static void main(String[] args) {
		ClientConnection clientConnection = ClientConnectionTCP.getInstance();
		clientConnection.connect();
		try {
			
			clientConnection.send(new Jogador("Joaquim José", InetAddress.getLocalHost()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
