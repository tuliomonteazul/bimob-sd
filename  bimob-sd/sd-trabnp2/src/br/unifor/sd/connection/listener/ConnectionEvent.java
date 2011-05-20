package br.unifor.sd.connection.listener;

import br.unifor.sd.connection.Client;
import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.server.ServerConnection;

public class ConnectionEvent {
	private Client client;
	private Object object;
	private ServerConnection serverConnection;
	
	public ConnectionEvent() {
		serverConnection = ConnectionFactory.getServerConnection();
	}
	
	public void acceptConnection() {
		serverConnection.addClient(client);
		serverConnection.send(client.getClientID(), UtilConnection.CONEXAO_OK, client.getClientID());
	}
	
	public void rejectConnection() {
		serverConnection.addClient(client);
		serverConnection.send(client.getClientID(), UtilConnection.CONEXAO_FAIL);
		serverConnection.removeClient(client);
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
