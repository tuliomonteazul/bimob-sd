package br.unifor.sd.connection;

import java.net.InetAddress;

public class ClientUDP implements Client {
	private static final long serialVersionUID = -6690192905028882714L;
	
	private InetAddress address;
	private int port;
	private int clientID;
	
	public ClientUDP() {
		super();
	}

	
	public ClientUDP(InetAddress address, int port, int clientID) {
		super();
		this.address = address;
		this.port = port;
		this.clientID = clientID;
	}


	public int getClientID() {
		return clientID;
	}

	public void setCLientID(int clientID) {
		this.clientID = clientID;
	}

	@Override
	public InetAddress getAddress() {
		return address;
	}
	
	
	public void setAddress(InetAddress address) {
		this.address = address;
	}

	@Override
	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


}
