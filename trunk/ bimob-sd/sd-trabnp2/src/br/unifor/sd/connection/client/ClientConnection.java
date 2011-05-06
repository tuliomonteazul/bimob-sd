package br.unifor.sd.connection.client;

import java.io.IOException;

import br.unifor.sd.connection.client.listener.ClientConnectionListener;

public interface ClientConnection {

	boolean connect(ClientConnectionListener listener);
	
	void send(Object objec) throws IOException;
}
