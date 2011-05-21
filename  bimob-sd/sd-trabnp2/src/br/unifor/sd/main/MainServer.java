package br.unifor.sd.main;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.factory.ConnectionProtocol;
import br.unifor.sd.service.server.ServerInputService;
import br.unifor.sd.view.server.GameController;

public class MainServer {
	public static void main(String[] args) {
		
		ConnectionFactory.createServer(ConnectionProtocol.UDP);
		
		final ServerInputService serverInputService = ServerInputService.getInstance();
		
		System.out.println("Iniciando servidor...");
		serverInputService.createServer();
		
		GameController.getInstance().startGame();
		
	}
}
