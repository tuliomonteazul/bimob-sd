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


//TODO Alterar para utilizar DatagramSocket e DatagramPacket
public class ServerConnectionUDP implements ServerConnection {

	public static final int PORT = 555;
	
	private ServerSocket serverSocket;
	
	private List<Client> clientes = new ArrayList<Client>();
	
	private static ServerConnection instance;
	private ServerConnectionUDP() {
		super();
	}
	
	public static ServerConnection getInstance() {
		if (instance == null) {
			instance = new ServerConnectionUDP();
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

						// a��o que pode ser um pedido de conex�o ou uma mensagem
						final int acao = inputStream.read();

						// cria o evento de conex�o para disparar o listener
						final ConnectionEvent event = new ConnectionEvent();

						// se for um pedido de conex�o
						if (acao == UtilConnection.CONEXAO) {
							// recebe a porta do cliente para posteriormente poder comunic�-lo
							final int port = inputStream.readInt();
//							event.setConnectRequest(true);
//							event.setPort(port);
//							event.setOutputStream(socket.getOutputStream());
							
							listener.requestConnection(event);
						} else {
							final Object object = inputStream.readObject();
							event.setObject(object);
							
							listener.receiveData(event);
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
	public void sendAll(Object... objects) {
		for (Client cliente : clientes) {
			send(cliente.getClientID(), objects);
		}
	}
	
	@Override
	public void send(int clientID, Object... objects) {
		try {
			System.out.println("enviando para o cliente: "+clientID);
			
			final Client client = findClient(clientID);
			
			final Socket socket = new Socket(client.getAddress(), client.getPort());
			
			final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.writeObject(objects);
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
