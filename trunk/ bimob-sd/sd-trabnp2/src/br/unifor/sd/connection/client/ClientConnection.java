package br.unifor.sd.connection.client;

import java.io.IOException;

public interface ClientConnection {

	boolean connect();
	
	void send(String msg) throws IOException;
}
