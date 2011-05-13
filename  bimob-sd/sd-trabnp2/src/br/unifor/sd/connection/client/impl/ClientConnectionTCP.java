package br.unifor.sd.connection.client.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.listener.ClientConnectionListener;
import br.unifor.sd.connection.listener.ConnectionEvent;

public class ClientConnectionTCP implements ClientConnection{

	private static ClientConnectionTCP instance;
	
	private static final String HOST = "localhost";
	private static final int PORT = 555;
	private Socket socket = null;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private int clientID;
	
	private ClientConnectionTCP() {
		super();
	}
	
	public static ClientConnectionTCP getInstance() {
		if (instance == null) {
			instance = new ClientConnectionTCP();
		}
		return new ClientConnectionTCP();
	}
	
	@Override
	public boolean connect(String name, ClientConnectionListener listener) {
		boolean connected = false;
		
		try {
			socket = new Socket(HOST, PORT);
			
			// envia pedido de conexao
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			// envia pedido de conexão e a porta do cliente para receber mensagens do servidor
			outputStream.writeObject(UtilConnection.CONEXAO);
			outputStream.flush();
			
			// envia o nome do cliente
			outputStream.writeObject(name);
			outputStream.flush();
			
			// le o retorno para verificar se a conexão foi aceita
			inputStream = new ObjectInputStream(socket.getInputStream());
			final int callback = (Integer) inputStream.readObject();
			
			if (callback == UtilConnection.CONEXAO_OK) {
				connected = true;
				
				clientID = (Integer) inputStream.readObject();
				
				waitMessages(listener);
			}
			
			System.out.println("connected: "+ connected);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connected;
	}

	private void waitMessages(final ClientConnectionListener listener) {
		new Thread(){
			@Override
			public void run() {
				try {
					// aguarda o recebimento de mensagens do servidor
					while (true) {
				
						// recebe o objeto enviado pelo servidor
						final Object object = inputStream.readObject();
						
						final ConnectionEvent event = new ConnectionEvent();
						event.setObject(object);
						
						listener.receive(event);
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
	public void send(Object object) throws IOException {
		
		outputStream.writeObject(UtilConnection.MSG);
		
		outputStream.writeObject(clientID);
		
		outputStream.writeObject(object);
		outputStream.flush();
		
	}

	@Override
	protected void finalize() throws Throwable {
		if (socket != null) {
			socket.close();
		}
	}
}
