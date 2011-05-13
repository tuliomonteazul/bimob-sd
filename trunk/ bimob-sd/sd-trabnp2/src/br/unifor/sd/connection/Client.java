package br.unifor.sd.connection;

import java.io.Serializable;
import java.net.InetAddress;

public interface Client extends Serializable{
	
	public String getName();
	
	public int getClientID();
	
	public InetAddress getAddress();

	public int getPort();

}
