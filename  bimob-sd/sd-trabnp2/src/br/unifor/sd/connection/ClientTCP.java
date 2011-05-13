package br.unifor.sd.connection;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTCP implements Client {
	private static final long serialVersionUID = -6690192905028882714L;
	
	private Socket socket;
	private ObjectOutputStream outputStream;
	private int clientID;
	
	public ClientTCP() {
		super();
	}

	
	public ClientTCP(Socket socket, int clientID) {
		super();
		this.socket = socket;
		this.clientID = clientID;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public int getClientID() {
		return clientID;
	}

	public void setCLientID(int clientID) {
		this.clientID = clientID;
	}

	@Override
	public InetAddress getAddress() {
		return socket.getInetAddress();
	}

	@Override
	public int getPort() {
		return socket.getPort();
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(ObjectOutputStream outputStream) {
		this.outputStream = outputStream;
	}

}
