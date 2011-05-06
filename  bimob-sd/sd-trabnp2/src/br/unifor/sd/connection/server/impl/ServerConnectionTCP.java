package br.unifor.sd.connection.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.unifor.sd.connection.server.ConnectionEvent;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.connection.server.ServerConnectionListener;

public class ServerConnectionTCP implements ServerConnection {
	
	private ServerSocket serverSocket;
	
	private ServerConnectionListener listener;
	
	public static final int PORT = 555;
	
	@Override
	public void send(InetAddress address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			
			while (true) {
				final Socket socket = serverSocket.accept();
				
				final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		
				final List<String> msgs = new ArrayList<String>();
				while (inputStream.available() > 0) {
					final String msg = inputStream.readUTF();
					msgs.add(msg);
				}
				final ConnectionEvent event = new ConnectionEvent();
				event.setAddress(socket.getInetAddress());
				event.setMsgs(msgs.toArray(new String[msgs.size()]));
				listener.receive(event);
				
				inputStream.close();
				socket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ServerConnectionListener getListener() {
		return listener;
	}

	public void setListener(ServerConnectionListener listener) {
		this.listener = listener;
	}

}
