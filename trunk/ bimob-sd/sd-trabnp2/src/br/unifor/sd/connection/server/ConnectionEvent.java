package br.unifor.sd.connection.server;

import java.net.InetAddress;

public class ConnectionEvent {
	private InetAddress address;
	private String[] msgs;

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public String[] getMsgs() {
		return msgs;
	}

	public void setMsgs(String[] msgs) {
		this.msgs = msgs;
	}

}
