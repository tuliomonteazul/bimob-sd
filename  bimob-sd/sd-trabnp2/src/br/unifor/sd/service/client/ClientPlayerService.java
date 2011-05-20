package br.unifor.sd.service.client;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Player;
import br.unifor.sd.service.Method;

public class ClientPlayerService {

	private ClientConnection clientConnection;
	
	public static Player player;	
	
	private static ClientPlayerService instance;
	
	private ClientPlayerService() {
		super();
		clientConnection = ConnectionFactory.getClientConnection();
	}
	public static ClientPlayerService getInstance(){
		if (instance == null) {
			instance = new ClientPlayerService();
		}
		return instance;
	}
	

	public void walk(int squares) {
		Method method = new Method(Method.JOGADA_ANDAR, player, squares);
		clientConnection.send(method);
	}
	
	public void buy(Card card) {
		Method method = new Method(Method.JOGADA_COMPRA, player, card);
		clientConnection.send(method);
	}
	
	public void none() {
		// não faz nada, apenas passa a jogada para o próximo jogador
		Method method = new Method(Method.JOGADA_PASSAR);
		clientConnection.send(method);
	}
}
