package br.unifor.sd.connection.client.listener;

import br.unifor.sd.connection.server.listener.ConnectionEvent;


public interface ClientConnectionListener {
	
	void receive(ConnectionEvent event);
}
