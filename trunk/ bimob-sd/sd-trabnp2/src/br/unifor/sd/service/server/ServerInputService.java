package br.unifor.sd.service.server;

import java.util.List;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.listener.ConnectionEvent;
import br.unifor.sd.connection.listener.ServerConnectionListener;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Game;
import br.unifor.sd.entity.Player;
import br.unifor.sd.service.Method;

public class ServerInputService {
	
	private ServerConnection serverConnection = ConnectionFactory.getServerConnection();
	
	private ServerOutputService serverOutputService = ServerOutputService.getInstance();
	
	private Game jogo = new Game();
	
	private static ServerInputService instance;
	private ServerInputService() {
		super();
	}
	public static ServerInputService getInstance(){
		if (instance == null) {
			instance = new ServerInputService();
		}
		return instance;
	}
	
	public void createServer(){
		
//		popularCasas();
		
		serverConnection.startServer(new ServerConnectionListener() {
			
			@Override
			public void requestConnection(ConnectionEvent event) {
				
				// se o limite de jogadores for atingido ou o jogo já tiver iniciado
				if (jogo.getJogadores().size() == ServerOutputService.MAX_JOGADORES || jogo.isIniciado()) {
					// TODO msg detalhe da rejeicao
					event.rejectConnection();
				} else {
					event.acceptConnection();
					
					final Player jogador = new Player();
					jogador.setClientID(event.getClient().getClientID());
					jogador.setCor(serverOutputService.nextColor());
					jogador.setPosicao(0);
					
					jogo.getJogadores().add(jogador);
					
					// TODO
					final Method method = new Method(Method.CONECTOU, jogador);
					serverConnection.send(event.getClient().getClientID(), method);
					
					System.out.println("O jogador " + event.getClient().getClientID() + " foi conectado.");
					serverConnection.sendAll(new Method(Method.ESCREVER_CONSOLE, null, "O jogador "+jogador.getCor().getText() + " entrou no jogo."));
				}
			
			}
			
			@Override
			public void receiveData(ConnectionEvent event) {
				if (event.getObject() instanceof Method) {
					doMethod((Method) event.getObject());
				}
			}
		});
	}
	
	public boolean startGame() {
		if (jogo.getJogadores().size() >= 1) {
			
			System.out.println("Jogo iniciado");
			serverOutputService.exibirMsg("Jogo iniciado, aguardando o próximo jogador.");
			serverOutputService.liberarVez(getProximoJogador());
			
			return true;
		} else {
			return false;
		}
	}
	
	private void doMethod(Method method) {
		Player jogador = null;
		Card card = null;
		switch (method.getIdMethod()) {
			case Method.JOGADA_ANDAR:
				jogador = (Player) method.getParams()[0];
				int valor = (Integer) method.getParams()[1];
				andarCasas(jogador, valor);
				break;
			case Method.JOGADA_COMPRA:
				jogador = (Player) method.getParams()[0];
				card = (Card) method.getParams()[1];
				comprar(jogador, card);
				break;
			case Method.JOGADA_PASSAR:
				proximoJog();
				break;
			case Method.JOGADA_PAGAR:
				jogador = (Player) method.getParams()[0];
				card = (Card) method.getParams()[1];
				pagar(jogador, card);
				break;
			case Method.JOGADAR_SAIR:
				jogador = (Player) method.getParams()[0];
				sair(jogador);
				break;
			case Method.ESCREVER_CONSOLE:
				jogador = (Player) method.getParams()[0];
				String msg = (String) method.getParams()[1];
				escreverConsole(jogador, msg);
				break;
		}
			
	}
	
	private void andarCasas(Player player, int casas) {
		final Player playerAux = findPlayer(player.getClientID());
		playerAux.addPosicao(casas);
		
		// faz com que o jogador se mova para todos os clientes
		final Method method = new Method(Method.MOVER, player, casas);
		serverConnection.sendAll(method);
		
		final Card card = jogo.getCasas().get(playerAux.getPosicao());
		// se a casa não tiver nenhum proprietário
		if (!card.isEspecial() && card.getJogador() == null) {
			// permite que o jogador compre
			serverConnection.send(player.getClientID(), new Method(Method.POSSIBILITA_COMPRA, card));
		} else {
			// é o dono da carta
			if (card.getJogador() != null && card.getJogador().getClientID() == playerAux.getClientID()) {
				// TODO
				// envia uma cobrança de aluguel para o usuário pois a propriedade tem dono
				serverConnection.send(player.getClientID(), new Method(Method.COBRAR_ALUGUEL, card));
				
			} else if (card.isEspecial()) {
				
				switch (card.getSpecialType()) {
				case INICIO:
					// recebe 200
					playerAux.addDinheiro(200);
					serverConnection.send(player.getClientID(), new Method(Method.RECEBA_200, playerAux.getDinheiro()));
					break;
				case VISITA:
					proximoJog();
					break;
				case WIKIPEDIA:
					// paga 50
					break;
				case PRISAO:
					
					break;
				}
				
			// se nao for o dono, cobra aluguel
			} else {
				// envia uma cobrança de aluguel para o usuário pois a propriedade tem dono
				serverConnection.send(player.getClientID(), new Method(Method.COBRAR_ALUGUEL, card));
			}
		}
	}
	
	private void comprar(Player player, Card card) {
		final Player playerAux = findPlayer(player.getClientID());
		
		final Card cardAux = findCarta(jogo.getCasas(), card);
		cardAux.setJogador(playerAux.clone());
		
		// debita o dinheiro do player
		playerAux.addDinheiro(- cardAux.getValor());
		
		
		serverConnection.sendAll(new Method(Method.ATUALIZA_COMPRA, cardAux.clone()));
		
		proximoJog();
	}
	
	private void pagar(Player player, Card card) {
		final Player playerAux = findPlayer(player.getClientID());
		// debita o aluguel do player
		playerAux.addDinheiro(- card.getAluguel());
		
		final Card cardAux = findCarta(jogo.getCasas(), card);
		// adiciona o dinheiro do aluguel
		cardAux.getJogador().addDinheiro(card.getAluguel());
		
		
		serverConnection.send(player.getClientID(), new Method(Method.ATUALIZA_DINHEIRO, playerAux.getDinheiro()));
		serverConnection.send(cardAux.getJogador().getClientID(), new Method(Method.ATUALIZA_DINHEIRO, cardAux.getJogador().getDinheiro()));
		
		proximoJog();
	}
	

	private void sair(Player player) {
		removePlayer(player.getClientID());
		
		serverConnection.send(player.getClientID(), new Method(Method.FIM_JOGO));
		serverConnection.sendAll(new Method(Method.REMOVER_JOGADOR, player));
		
		// TODO verifica vencedor
		proximoJog();
	}

	private void escreverConsole(Player player, String msg) {
		serverConnection.sendAll(new Method(Method.ESCREVER_CONSOLE, player, msg));
	}
	
	private void proximoJog() {
		serverOutputService.exibirMsg("Aguardando o próximo jogador.");
		serverOutputService.liberarVez(getProximoJogador());
	}
	
	private Player getProximoJogador() {
		final Player proximo = jogo.getJogadores().get(0);
		jogo.getJogadores().remove(proximo);
		jogo.getJogadores().add(proximo);
		return proximo;
	}
	
	
	public Card findCarta(List<Card> cartas, Card card) {
		for (Card cardAux : cartas) {
			if (cardAux.getNome().equals(card.getNome())) {
				return cardAux;
			}
		}
		return null;
	}
	
	public Game getJogo() {
		return jogo;
	}
	
	private Player findPlayer(int clientID) {
		for (Player jogador : jogo.getJogadores()) {
			if (jogador.getClientID() == clientID) {
				return jogador;
			}
		}
		return null;
	}
	
	private void removePlayer(int clientID) {
		Player player = null;
		for (Player playerAux : jogo.getJogadores()) {
			if (playerAux.getClientID() == clientID) {
				player = playerAux;
			}
		}
		if (player != null) {
			jogo.getJogadores().remove(player);
		}
		
		// limpa as cartas deste jogador
		for (Card card : jogo.getCasas()) {
			if (card.getJogador() != null && card.getJogador().getClientID() == player.getClientID()) {
				card.setJogador(null);
			}
		}
	}
	
}
