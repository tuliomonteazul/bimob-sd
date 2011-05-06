package br.unifor.sd.connection.server;

import java.net.InetAddress;

public interface ServerConnection {
	
	void startServer(ServerConnectionListener listener);
	
	void sendAll();
	
	void send(InetAddress address);
	
}
