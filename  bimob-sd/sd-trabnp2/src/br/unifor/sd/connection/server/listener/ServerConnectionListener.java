package br.unifor.sd.connection.server.listener;


public interface ServerConnectionListener {
	
	void receive(ConnectionEvent event);
}
