package br.unifor.sd.connection.client;

import br.unifor.sd.connection.listener.ClientConnectionListener;

public interface ClientConnection {

	/**
	 * Realiza o pedido de conexão ao servidor e retorna true se a conexão foi estabelecida com sucesso ou
	 * false caso tenha sido recusada ou falhada. Executa o listener enviado por parâmetro quando as mensagens
	 * vindas do servidor forem recebidas.
	 * 
	 * @param listener
	 * @return boolean true se conectou ou false se não
	 */
	boolean connect(ClientConnectionListener listener);
	
	/**
	 * Envia um objeto ao servidor.
	 * @param objec
	 */
	void send(Object objec);
}
