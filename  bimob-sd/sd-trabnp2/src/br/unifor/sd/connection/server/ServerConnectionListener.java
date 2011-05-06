package br.unifor.sd.connection.server;


public interface ServerConnectionListener {
	
	void receive(ConnectionEvent event);
}
