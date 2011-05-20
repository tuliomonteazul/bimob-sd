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

public class ServerGameService {
	
	private ServerConnection serverConnection = ConnectionFactory.getServerConnection();
	
	private ServerPlayerService jogadorService = ServerPlayerService.getInstance();
	
	private Game jogo = new Game();
	
	private static ServerGameService instance;
	private ServerGameService() {
		super();
	}
	public static ServerGameService getInstance(){
		if (instance == null) {
			instance = new ServerGameService();
		}
		return instance;
	}
	
	public void createServer(){
		
//		popularCasas();
		
		serverConnection.startServer(new ServerConnectionListener() {
			
			@Override
			public void requestConnection(ConnectionEvent event) {
				
				// se ainda não existir um jogador com esse nome
				if (jogo.getJogadores().size() == ServerPlayerService.MAX_JOGADORES) {
					// TODO msg detalhe da rejeicao
					event.rejectConnection();
				} else {
					event.acceptConnection();
					
					final Player jogador = new Player();
					jogador.setClientID(event.getClient().getClientID());
					jogador.setCor(jogadorService.nextColor());
					jogador.setPosicao(0);
					
					jogo.getJogadores().add(jogador);
					
					final Method method = new Method(Method.CONECTOU, jogador);
					serverConnection.send(event.getClient().getClientID(), method);
					
					System.out.println("O jogador " + event.getClient().getClientID() + " foi conectado.");
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
			jogadorService.exibirMsg("Jogo iniciado, aguardando o próximo jogador.");
			jogadorService.liberarVez(getProximoJogador());
			
			return true;
		} else {
			return false;
		}
	}
	
	private void doMethod(Method method) {
		Player jogador = null;
		switch (method.getIdMethod()) {
			case Method.JOGADA_ANDAR:
				jogador = (Player) method.getParams()[0];
				int valor = (Integer) method.getParams()[1];
				andarCasas(jogador, valor);
				break;
			case Method.JOGADA_COMPRA:
				jogador = (Player) method.getParams()[0];
				Card card = (Card) method.getParams()[1];
				comprar(jogador, card);
				break;
			case Method.PROXIMO_JOG:
//				jogador = (Player) method.getParams()[0];
//				Card card = (Card) method.getParams()[1];
				proximoJog();
				break;
		}
			
	}
	
	
	private void andarCasas(Player player, int casas) {
		final Player playerAux = jogadorService.findJogador(jogo.getJogadores(), player.getClientID());
		playerAux.addPosicao(casas);
		
		// faz com que o jogador se mova para todos os clientes
		final Method method = new Method(Method.MOVER, player, casas);
		serverConnection.sendAll(method);
		
		final Card card = jogo.getCasas().get(playerAux.getPosicao());
		// se a casa não tiver nenhum proprietário
		if (card.getJogador() == null) {
			// permite que o jogador compre
			serverConnection.send(player.getClientID(), new Method(Method.POSSIBILITA_COMPRA, card));
		}
	}
	
	private void comprar(Player player, Card card) {
		final Player playerAux = jogadorService.findJogador(jogo.getJogadores(), player.getClientID());
		playerAux.addCarta(card);
		
		final Card cardAux = findCarta(jogo.getCasas(), card);
//		final Card cardAux = jogo.getCasas().get(playerAux.getPosicao());
		cardAux.setJogador(playerAux);
		
		// debita o dinheiro do player
		playerAux.addDinheiro(- cardAux.getValor());
		
		serverConnection.sendAll(new Method(Method.ATUALIZA_COMPRA, playerAux, cardAux));
		
		proximoJog();
	}
	
	private void proximoJog() {
		jogadorService.exibirMsg("Aguardando o próximo jogador.");
		jogadorService.liberarVez(getProximoJogador());
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
	
}
