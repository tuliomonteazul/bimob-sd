package br.unifor.sd.connection.listener;


public interface ServerConnectionListener {
	
	void receiveData(ConnectionEvent event);
	
	void requestConnection(ConnectionEvent event);
}
