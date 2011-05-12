package br.unifor.sd.connection.server;

import br.unifor.sd.connection.Client;
import br.unifor.sd.connection.listener.ServerConnectionListener;


public interface ServerConnection {

	/**
	 * Inicializa o servidor socket e executa o listener enviado quando uma nova mensagem for recebida. As
	 * mensagens recebidas podem ser pedido de conexão ou apenas dados.
	 * 
	 * @param listener
	 */
	void startServer(ServerConnectionListener listener);
	
	/**
	 * Envia um ou mais objetos para todos os clientes conectados.
	 * @param object
	 */
	void sendAll(Object... objects);
	
	/**
	 * Envia um ou mais objetos para um determinado cliente pelo seu clientID.
	 * @param clientID
	 * @param object
	 */
	void send(int clientID, Object... object);
	
	/**
	 * Adiciona um cliente na lista de clientes conectados ao servidor.
	 * @param cliente
	 */
	void addClient(Client cliente);
	
	/**
	 * Remove um cliente da lista de clientes conectados ao servidor.
	 * @param cliente
	 */
	void removeClient(Client cliente);

}
