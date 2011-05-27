package br.unifor.sd.service.client;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Player;
import br.unifor.sd.service.Method;

public class ClientOutputService {

	private ClientConnection clientConnection;
	
	public static Player player;	
	
	private static ClientOutputService instance;
	
	private ClientOutputService() {
		super();
		clientConnection = ConnectionFactory.getClientConnection();
	}
	public static ClientOutputService getInstance(){
		if (instance == null) {
			instance = new ClientOutputService();
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
	
	public void pay(Card card) {
		Method method = new Method(Method.JOGADA_PAGAR, player, card);
		clientConnection.send(method);
	}
	public void exit() {
		Method method = new Method(Method.JOGADAR_SAIR, player);
		clientConnection.send(method);
	}
	
	public void writeConsole(String msg) {
		Method method = new Method(Method.ESCREVER_CONSOLE, player, msg);
		clientConnection.send(method);
	}
}
