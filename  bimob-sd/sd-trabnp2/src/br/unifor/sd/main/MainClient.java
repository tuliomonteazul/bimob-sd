package br.unifor.sd.main;

import javax.swing.JOptionPane;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.factory.ConnectionProtocol;
import br.unifor.sd.service.client.ClientInputService;

public class MainClient {
	public static void main(String[] args) {
		
		String options[] = {"TCP", "UDP"};
		int option = JOptionPane.showOptionDialog(null, "Qual o protocolo de conexão?", "Definição de protocolo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		if (option == 0) {
			ConnectionFactory.createClient(ConnectionProtocol.TCP);
		} else if (option == 1) {
			ConnectionFactory.createClient(ConnectionProtocol.UDP);
		} else {
			System.exit(0);
		}
		
		// inicia o jogo
		ClientInputService.getInstance().playGame();
	}
}
