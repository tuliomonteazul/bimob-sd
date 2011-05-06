package br.unifor.sd.connection;

import java.io.Serializable;
import java.net.InetAddress;

public class Client implements Serializable{
	private static final long serialVersionUID = -6690192905028882714L;
	
	private InetAddress address;
	private int porta;
	
	
	public Client() {
		super();
	}
	public Client(InetAddress address) {
		super();
		this.address = address;
	}
	public InetAddress getAddress() {
		return address;
	}
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	
}
