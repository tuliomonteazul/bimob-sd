package br.unifor.sd.service.client;

import javax.swing.JOptionPane;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.listener.ClientConnectionListener;
import br.unifor.sd.connection.listener.ConnectionEvent;
import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Player;
import br.unifor.sd.service.Method;
import br.unifor.sd.view.client.PlayerController;

public class ClientInputService {

	private ClientConnection clientConnection;
	
	private PlayerController playerController;
	
	private static ClientInputService instance;
	private ClientInputService() {
		super();
		clientConnection = ConnectionFactory.getClientConnection();
		playerController = PlayerController.getInstance();
	}
	public static ClientInputService getInstance(){
		if (instance == null) {
			instance = new ClientInputService();
		}
		return instance;
	}
	
	public void playGame() {
		playerController.init();
		
		final String host = JOptionPane.showInputDialog("Digite a URL do servidor:");
		
		boolean conectou = clientConnection.connect(host, new ClientConnectionListener() {
			
			@Override
			public void receive(ConnectionEvent event) {
				if (event.getObject() instanceof Method) {
					doMethod((Method) event.getObject());
				}
			}
		});
		
		if (!conectou) {
			playerController.errorConnection();
		}
	}
	

	private void doMethod(Method method) {
		Player player;
		Card card;
		String msg;
		double dinheiro;
		switch (method.getIdMethod()) {
			case Method.CONECTOU:
				player = (Player) method.getParams()[0];
				playerController.setPlayer(player);
				ClientOutputService.player = player;
				break;
			case Method.LIBERAR_VEZ:
				playerController.liberarVez();
				break;
			case Method.EXIBIR_MSG:
				msg = (String) method.getParams()[0];
				playerController.exibirMsg(msg);
				break;
			case Method.MOVER:
				player = (Player) method.getParams()[0];
				final int casas = (Integer) method.getParams()[1];
				playerController.mover(player, casas);
				break;
			case Method.POSSIBILITA_COMPRA:
				card = (Card) method.getParams()[0];
				playerController.possibilitaCompra(card);
				break;
			case Method.ATUALIZA_COMPRA:
				card = (Card) method.getParams()[0];
				playerController.atualizaCompra(card);
				break;
			case Method.COBRAR_ALUGUEL:
				card = (Card) method.getParams()[0];
				playerController.exibeCobranca(card);
				break;
			case Method.ATUALIZA_DINHEIRO:
				final double novoDinheiro = (Double) method.getParams()[0];
				playerController.atualizaDinheiro(novoDinheiro);
				break;
			case Method.FIM_JOGO:
				playerController.fimJogo();
				break;
			case Method.REMOVER_JOGADOR:
				player = (Player) method.getParams()[0];
				playerController.removerJogador(player);
				break;
			case Method.ESCREVER_CONSOLE:
				player = (Player) method.getParams()[0];
				msg = (String) method.getParams()[1];
				playerController.escreverConsole(player, msg);
				break;
			case Method.RECEBA_200:
				dinheiro = (Double) method.getParams()[0];
				playerController.receba200(dinheiro);
				break;
			case Method.VOCE_VENCEU:
				playerController.voceVenceu();
				break;
			case Method.PAGAR_WIKIPEDIA:
				dinheiro = (Double) method.getParams()[0];
				playerController.pagarWikipedia(dinheiro);
				break;
			case Method.PRISAO:
				playerController.prisao();
				break;
			case Method.POSSIBILITA_EXPANSAO:
				card = (Card) method.getParams()[0];
				playerController.possibilitaExpansao(card);
				break;
			case Method.ATUALIZA_UPGRADE:
				card = (Card) method.getParams()[0];
				playerController.atualizaUpgrade(card);
				break;
		}
	}
}
