package br.unifor.sd.connection.server;

import java.net.InetAddress;

import br.unifor.sd.connection.server.listener.ServerConnectionListener;


public interface ServerConnection {
	
	void startServer(ServerConnectionListener listener);
	
	void addUser(InetAddress address);
	
	void removeUser(InetAddress address);
}
