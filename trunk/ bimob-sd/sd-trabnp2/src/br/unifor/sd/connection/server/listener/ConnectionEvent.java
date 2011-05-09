package br.unifor.sd.connection.server.listener;

import br.unifor.sd.connection.Client;
import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.server.impl.ServerConnectionTCP;

public class ConnectionEvent {
	private Client client;
	private Object object;
	
	public void acceptConnection() {
		ServerConnectionTCP.getInstance().addClient(client);
		
		ServerConnectionTCP.getInstance().send(client.getClientID(), UtilConnection.CONEXAO_OK, client.getClientID());
	}
	
	public void rejectConnection() {
		ServerConnectionTCP.getInstance().send(client.getClientID(), UtilConnection.CONEXAO_FAIL);
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
