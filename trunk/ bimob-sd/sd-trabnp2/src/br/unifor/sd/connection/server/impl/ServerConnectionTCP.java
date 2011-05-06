package br.unifor.sd.connection.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.unifor.sd.connection.Client;
import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.connection.server.listener.ConnectionEvent;
import br.unifor.sd.connection.server.listener.ServerConnectionListener;

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
					
					// aguarda o recebimento de mensagens dos clientes
					while (true) {
						Socket socket = serverSocket.accept();
						
						final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

						// ação que pode ser um pedido de conexão ou uma mensagem
						final int acao = inputStream.read();

						// cria o evento de conexão para disparar o listener
						final ConnectionEvent event = new ConnectionEvent();
						event.setAddress(socket.getInetAddress());
						// se for um pedido de conexão
						if (acao == UtilConnection.CONEXAO) {
							// recebe a porta do cliente para posteriormente poder comunicá-lo
							final int port = inputStream.readInt();
							event.setConnectRequest(true);
							event.setPort(port);
							event.setOutputStream(socket.getOutputStream());
							
							listener.requestConnection(event);
						} else {
							final Object object = inputStream.readObject();
							event.setObject(object);
							
							listener.receiveMessage(event);
						}
						
						
						
						inputStream.close();
						socket.close();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	@Override
	public void sendAll(Object object) {
		for (Client cliente : clientes) {
			send(cliente, object);
		}
	}
	
	@Override
	public void send(Client cliente, Object object) {
		try {
			System.out.println("enviando para o cliente: "+cliente.getPorta());
			final Socket socket = new Socket(cliente.getAddress(), cliente.getPorta());
			
			final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(object);
			outputStream.flush();
			
			outputStream.close();
			
			socket.close();
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

}
