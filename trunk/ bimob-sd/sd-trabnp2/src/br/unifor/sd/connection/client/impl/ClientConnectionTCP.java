package br.unifor.sd.connection.client.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.unifor.sd.connection.UtilConnection;
import br.unifor.sd.connection.client.ClientConnection;

public class ClientConnectionTCP implements ClientConnection{

	private static ClientConnectionTCP instance;
	
	private static final String HOST = "localhost";
	private static final int PORT = 555;
	

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
	public boolean connect() {
		boolean connected = false;
		InputStream inputStream = null;
		Socket socket = null;
		
		try {
			socket = new Socket(HOST, PORT);
			
			// envia pedido de conexao
			final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			outputStream.write(UtilConnection.CONEXAO);
			outputStream.flush();
			
			
			// le o retorno para verificar se a conexão foi aceita
			inputStream = socket.getInputStream();
			final int callback = inputStream.read();
			
			if (callback == UtilConnection.CONEXAO_OK) {
				connected = true;
			}
			
			System.out.println(connected);
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

	@Override
	public void send(Object object) throws IOException {
		final Socket socket = new Socket(HOST, PORT);
		
		final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		
		outputStream.writeObject(object);
		outputStream.flush();
		
		outputStream.close();
		socket.close();
		
		socket.close();
	}

}
