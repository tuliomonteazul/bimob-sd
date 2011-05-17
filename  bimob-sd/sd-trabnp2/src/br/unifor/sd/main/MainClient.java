package br.unifor.sd.main;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.factory.ConnectionProtocol;
import br.unifor.sd.service.client.ClientGameService;

public class MainClient {
	public static void main(String[] args) {
		
		ConnectionFactory.createClient(ConnectionProtocol.TCP);
		
		ClientGameService gameService = new ClientGameService();
		gameService.playGame();
	}
}
