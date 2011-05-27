package br.unifor.sd.main;

import javax.swing.JOptionPane;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.factory.ConnectionProtocol;
import br.unifor.sd.service.server.ServerInputService;
import br.unifor.sd.view.server.GameController;

public class MainServer {
	public static void main(String[] args) {
		

		String options[] = {"TCP", "UDP"};
		int option = JOptionPane.showOptionDialog(null, "Qual o protocolo de conexão?", "Definição de protocolo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (option == 0) {
			ConnectionFactory.createServer(ConnectionProtocol.TCP);
		} else if (option == 1) {
			ConnectionFactory.createServer(ConnectionProtocol.UDP);
		} else {
			System.exit(0);
		}
		
		final ServerInputService serverInputService = ServerInputService.getInstance();
		
		System.out.println("Servidor em execução!");
		serverInputService.createServer();
		
		GameController.getInstance().startGame();
		
	}
}
