package br.unifor.sd.connection.server.listener;


public interface ServerConnectionListener {
	
	void receiveMessage(ConnectionEvent event);
	
	void requestConnection(ConnectionEvent event);
}
