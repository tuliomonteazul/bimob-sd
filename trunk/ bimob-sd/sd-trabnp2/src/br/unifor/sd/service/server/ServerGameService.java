package br.unifor.sd.service.server;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.factory.ConnectionProtocol;
import br.unifor.sd.connection.listener.ConnectionEvent;
import br.unifor.sd.connection.listener.ServerConnectionListener;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.entity.Player;
import br.unifor.sd.entity.Game;
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
			jogadorService.liberarVez(getProximoJogador());
			jogadorService.exibirMsg("Jogo iniciado, aguardando o próximo jogador.");
			
			return true;
		} else {
			return false;
		}
	}
	
	private void doMethod(Method method) {
		if (method.getIdMethod() == Method.ANDAR_CASAS) {
			Player jogador = (Player) method.getParams()[0];
			int valor = (Integer) method.getParams()[1];
			andarCasas(jogador, valor);
		}
	}
	
	private void andarCasas(Player jogador, int valor) {
		// TODO Tulio parei aqui
		
	}
	
	private Player getProximoJogador() {
		final Player proximo = jogo.getJogadores().get(0);
		jogo.getJogadores().remove(proximo);
		jogo.getJogadores().add(proximo);
		return proximo;
	}
}
