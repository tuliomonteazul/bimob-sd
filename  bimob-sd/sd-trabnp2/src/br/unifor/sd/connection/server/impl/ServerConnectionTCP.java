package br.unifor.sd.connection.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.connection.server.listener.ConnectionEvent;
import br.unifor.sd.connection.server.listener.ServerConnectionListener;

public class ServerConnectionTCP implements ServerConnection {
	
	private static ServerConnectionTCP instance;
	
	public static final int PORT = 555;
	
	private ServerSocket serverSocket;
	
	private Socket socket;
	
	private List<InetAddress> users = new ArrayList<InetAddress>();
	
	private ServerConnectionTCP() {
		super();
	}
	
	public static ServerConnectionTCP getInstance() {
		if (instance == null) {
			instance = new ServerConnectionTCP();
		}
		return new ServerConnectionTCP();
	}
	
	@Override
	public void startServer(ServerConnectionListener listener) {
		try {
			serverSocket = new ServerSocket(PORT);
			
			// aguarda o recebimento de mensagens dos clientes
			while (true) {
				socket = serverSocket.accept();
				
				final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		
				final int acao = inputStream.read();

				final ConnectionEvent event = new ConnectionEvent();
				// se for um pedido de conexão
				if (acao == UtilConnection.CONEXAO) {
					event.setConnectRequest(true);
					event.setOutputStream(socket.getOutputStream());
				} else {
					final Object object = inputStream.readObject();
					event.setObject(object);
				}
				event.setAddress(socket.getInetAddress());
				listener.receive(event);
				
				
				inputStream.close();
				socket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(InetAddress address) {
		users.add(address);
	}

	@Override
	public void removeUser(InetAddress address) {
		users.remove(address);
	}

}
