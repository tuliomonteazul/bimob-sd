package br.unifor.sd.main;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.factory.ConnectionProtocol;
import br.unifor.sd.service.client.ClientInputService;

public class MainClient {
	public static void main(String[] args) {
		
		ConnectionFactory.createClient(ConnectionProtocol.UDP);
		
		// inicia o jogo
		ClientInputService.getInstance().playGame();
	}
}
