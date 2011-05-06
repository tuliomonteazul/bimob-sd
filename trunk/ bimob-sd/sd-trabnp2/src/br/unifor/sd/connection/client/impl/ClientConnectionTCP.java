package br.unifor.sd.connection.client.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.unifor.sd.connection.client.ClientConnection;

public class ClientConnectionTCP implements ClientConnection{

	private Socket socket;
	private static final String HOST = "localhost";
	private static final int PORT = 555;
	
	@Override
	public boolean connect() {
		boolean connected = false;
		try {
			socket = new Socket(HOST, PORT);
			
			send("TESTE");
			
			final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			
			System.out.println(inputStream.readUTF());
			
			inputStream.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connected;
	}

	@Override
	public void send(String msg) throws IOException {
		final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		
		outputStream.writeUTF(msg);
		outputStream.flush();
	}

}
