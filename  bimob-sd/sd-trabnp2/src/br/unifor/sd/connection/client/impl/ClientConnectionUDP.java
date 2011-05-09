package br.unifor.sd.connection.client.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.client.listener.ClientConnectionListener;

// TODO Alterar para utilizar DatagramSocket e DatagramPacket
public class ClientConnectionUDP implements ClientConnection{

	private static ClientConnectionUDP instance;
	
	private static final String HOST = "localhost";
	private static final int PORT = 555;
	
	private ClientConnectionUDP() {
		super();
	}
	
	public static ClientConnectionUDP getInstance() {
		if (instance == null) {
			instance = new ClientConnectionUDP();
		}
		return new ClientConnectionUDP();
	}
	
	@Override
	public boolean connect(ClientConnectionListener listener) {
		boolean connected = false;
		InputStream inputStream = null;
		Socket socket = null;
		int port = startServer();
		System.out.println("Porta do cliente: "+ port);
		
		try {
			socket = new Socket(HOST, PORT);
			
			// envia pedido de conexao
			final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			// envia pedido de conexão e a porta do cliente para receber mensagens do servidor
			outputStream.write(UtilConnection.CONEXAO);
			outputStream.writeInt(port);
			outputStream.flush();
			
			
			// le o retorno para verificar se a conexão foi aceita
			inputStream = socket.getInputStream();
			final int callback = inputStream.read();
			
			if (callback == UtilConnection.CONEXAO_OK) {
				connected = true;
				
				
			}
			
			System.out.println("connected: "+ connected);
			inputStream.close();
			outputStream.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connected;
	}

	private int startServer() {
		int port = 0;
		try {
			final ServerSocket serverSocket = new ServerSocket(port);
			port = serverSocket.getLocalPort();
			
			waitMessages(serverSocket);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return port;
	}

	private void waitMessages(final ServerSocket serverSocket) {
		new Thread(){
			@Override
			public void run() {
				try {
					// aguarda o recebimento de mensagens do servidor
					while (true) {
						Socket socket = serverSocket.accept();
						
						final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				
						// recebe o objeto enviado pelo servidor
						final Object object = inputStream.readObject();
						System.out.println("Cliente recebeu: "+object);
						
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
	public void send(Object object) throws IOException {
		final Socket socket = new Socket(HOST, PORT);
		
		final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		
		outputStream.writeObject(object);
		outputStream.flush();
		
		outputStream.close();
		
		socket.close();
	}

}
