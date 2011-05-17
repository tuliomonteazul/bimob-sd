package br.unifor.sd.connection.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.unifor.sd.connection.Client;
import br.unifor.sd.connection.ClientTCP;
import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.listener.ConnectionEvent;
import br.unifor.sd.connection.listener.ServerConnectionListener;
import br.unifor.sd.connection.server.ServerConnection;

public class ServerConnectionTCP implements ServerConnection {
	
	private static ServerConnectionTCP instance;
	
	public static final int PORT = 555;
	
	private ServerSocket serverSocket;
	
	private List<Client> clientes = new ArrayList<Client>();
	
	private ServerConnectionTCP() {
		super();
	}
	
	public static ServerConnectionTCP getInstance() {
		if (instance == null) {
			instance = new ServerConnectionTCP();
		}
		return instance;
	}
	
	@Override
	public void startServer(final ServerConnectionListener listener) {
		new Thread() {
			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(PORT);
					
					// aguarda o recebimento de conexões dos clientes
					while (true) {
						Socket socket = serverSocket.accept();
						
						receive(listener, socket);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	protected void receive(final ServerConnectionListener listener, final Socket socket) {
		new Thread() {
			public void run() {
				try {
					
					final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
					while (true) {
	
						System.out.println("recebendo req..");
						// ação que pode ser um pedido de conexão ou uma mensagem
						final int acao = (Integer) inputStream.readObject();
	
						// cria o evento de conexão para disparar o listener
						final ConnectionEvent event = new ConnectionEvent();
	
						// se for um pedido de conexão
						if (acao == UtilConnection.CONEXAO) {
							// gera um id para identificar esse cliente em outras trocas de mensagenss
							final int clientID = UtilConnection.generateClientID();
	
							final ClientTCP client = new ClientTCP(socket, clientID);
							event.setClient(client);
							client.setOutputStream(new ObjectOutputStream(socket.getOutputStream()));
	
							listener.requestConnection(event);
						} else if (acao == UtilConnection.MSG) {
							
							final int clientID = (Integer) inputStream.readObject();
							event.setClient(findClient(clientID));
							
							final Object object = inputStream.readObject();
							event.setObject(object);
	
							listener.receiveData(event);
						}
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	@Override
	public void sendAll(Object... objects) {
		for (Client cliente : clientes) {
			send(cliente.getClientID(), objects);
		}
	}
	
	@Override
	public void send(int clientID, Object... objects) {
		try {
			final Client client = findClient(clientID);
			
			final ObjectOutputStream outputStream = ((ClientTCP) client).getOutputStream();
			
			for (Object object : objects) {
				outputStream.writeObject(object);
			}
			outputStream.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addClient(Client cliente) {
		clientes.add(cliente);
	}

	@Override
	public void removeClient(Client cliente) {
		clientes.remove(cliente);
	}
	
	private Socket findSocket(int clientID) {
		return ((ClientTCP) findClient(clientID)).getSocket();
	}
	
	private Client findClient(int clientID) {
		for (Client client : clientes) {
			if (client.getClientID() == clientID) {
				return client;
			}
		}
		return null;
	}
	
}
