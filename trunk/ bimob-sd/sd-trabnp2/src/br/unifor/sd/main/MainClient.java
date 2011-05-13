package br.unifor.sd.main;

import java.io.IOException;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.client.impl.ClientConnectionTCP;
import br.unifor.sd.connection.listener.ClientConnectionListener;
import br.unifor.sd.connection.listener.ConnectionEvent;

public class MainClient {
	public static void main(String[] args) {
		// TODO parametros ip e porta do servidor
		ClientConnection clientConnection = ClientConnectionTCP.getInstance();
		clientConnection.connect("teste", new ClientConnectionListener() {
			
			@Override
			public void receive(ConnectionEvent event) {
				System.out.println("Cliente recebeu: "+event.getObject());
			}
		});
		try {
			
			clientConnection.send("mensagem de teste!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
