package br.unifor.sd.connection.server;

import br.unifor.sd.connection.Client;
import br.unifor.sd.connection.server.listener.ServerConnectionListener;


public interface ServerConnection {
	
	void startServer(ServerConnectionListener listener);
	
	void sendAll(Object object);
	
	void send(int clientID, Object... object);
	
	void addClient(Client cliente);
	
	void removeClient(Client cliente);

}
