package br.unifor.sd.connection.server;

import java.net.InetAddress;

public interface ServerConnection {
	
	void startServer();
	
	void sendAll();
	
	void send(InetAddress address);
	
}
