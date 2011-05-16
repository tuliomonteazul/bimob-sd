package br.unifor.sd.service;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.factory.ConnectionProtocol;
import br.unifor.sd.connection.listener.ConnectionEvent;
import br.unifor.sd.connection.listener.ServerConnectionListener;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.entity.Jogador;
import br.unifor.sd.entity.Jogo;

public class JogoService {
	
	private ServerConnection serverConnection = ConnectionFactory.createServer(ConnectionProtocol.TCP);
	
	private JogadorService jogadorService = JogadorService.getInstance();
	
	private Jogo jogo = new Jogo();
	
	private static JogoService instance;
	private JogoService() {
		super();
	}
	public static JogoService getInstance(){
		if (instance == null) {
			instance = new JogoService();
		}
		return instance;
	}
	
	public void createServer(){
		
//		popularCasas();
		
		serverConnection.startServer(new ServerConnectionListener() {
			
			@Override
			public void requestConnection(ConnectionEvent event) {
				
				// se ainda não existir um jogador com esse nome
				if (jogo.getJogadores().size() == JogadorService.MAX_JOGADORES) {
					// TODO msg detalhe da rejeicao
					event.rejectConnection();
				} else {
					event.acceptConnection();
					
					final Jogador jogador = new Jogador();
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
	
	private void doMethod(Method method) {
		if (method.getIdMethod() == 1) {
			Jogador jogador = (Jogador) method.getParams()[0];
			int valor = (Integer) method.getParams()[1];
			jogarDado(jogador, valor);
		}
	}
	
	private void jogarDado(Jogador jogador, int valor) {
		// TODO Tulio parei aqui
	}
}
