package br.unifor.sd.connection.server.impl;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

import br.unifor.sd.connection.Client;
import br.unifor.sd.connection.ClientUDP;
import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.listener.ConnectionEvent;
import br.unifor.sd.connection.listener.ServerConnectionListener;
import br.unifor.sd.connection.server.ServerConnection;


public class ServerConnectionUDP implements ServerConnection {

	public static final int PORT = 555;
	
	private DatagramSocket socket;
	
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
					socket = new DatagramSocket(PORT);
					
					byte[] buffer = new byte[1000]; // Cria um buffer local
					DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
					String data;
					
					// aguarda o recebimento de mensagens dos clientes
					while (true) {
						socket.receive(pacote);
						
//						data = new String(pacote.getData(), 0, pacote.getLength());
//						
//						// ação que pode ser um pedido de conexão ou uma mensagem
//						final int acao = Integer.parseInt(data);
						Integer acao = (Integer) UtilConnection.byteArrayToObject(pacote.getData());

						// cria o evento de conexão para disparar o listener
						final ConnectionEvent event = new ConnectionEvent();

						// se for um pedido de conexão
						if (acao == UtilConnection.CONEXAO) {
							socket.receive(pacote);
							data = new String(pacote.getData(), 0, pacote.getLength());
							
							// gera um id para identificar esse cliente em outras trocas de mensagenss
							final int clientID = UtilConnection.generateClientID();
							
							// recebe a porta do cliente para posteriormente poder comunicá-lo
							final int port = Integer.parseInt(data);
							final Client client = new ClientUDP(pacote.getAddress(), port, clientID);
							event.setClient(client);
							
							listener.requestConnection(event);
						} else {
							
							socket.receive(pacote);
							
							final Object object = UtilConnection.byteArrayToObject(pacote.getData());
							event.setObject(object);
							
							listener.receiveData(event);
						}
						
						
						
//						inputStream.close();
//						socket.close();
					}
					
				} catch (Exception e) {
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
			System.out.println("enviando pacote para o cliente: "+clientID);
			
			final Client client = findClient(clientID);
			
			for (Object object : objects) {
				byte[] dados = UtilConnection.objectToByteArray(object);
				DatagramPacket pacote = new DatagramPacket(dados, dados.length,
						client.getAddress(), client.getPort());
				
				socket.send(pacote);
			}
			
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

	private Client findClient(int clientID) {
		for (Client client : clientes) {
			if (client.getClientID() == clientID) {
				return client;
			}
		}
		return null;
	}
}
