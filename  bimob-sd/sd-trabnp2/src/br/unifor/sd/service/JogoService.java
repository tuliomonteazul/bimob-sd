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
	
	public void initGame(){
		
//		popularCasas();
		
		serverConnection.startServer(new ServerConnectionListener() {
			
			@Override
			public void requestConnection(ConnectionEvent event) {
				// nome do jogador que está tentando se conectar
				final String name = event.getClient().getName();
				Jogador jogador = jogadorService.findJogador(jogo.getJogadores(), name);
				
				// se ainda não existir um jogador com esse nome
				if (jogador == null) {
					if (jogo.getJogadores().size() == JogadorService.MAX_JOGADORES) {
						// TODO msg detalhe da rejeicao
						event.rejectConnection();
					} else {
						event.acceptConnection();
						
						jogador = new Jogador();
						jogador.setClientID(event.getClient().getClientID());
						jogador.setCor(jogadorService.nextColor());
						jogador.setNome(name);
						jogador.setPosicao(0);
						
						jogo.getJogadores().add(jogador);
						
						// TODO Tulio - Parei aqui
					}
				} else {
					event.rejectConnection();
				}
			
			}
			
			@Override
			public void receiveData(ConnectionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
