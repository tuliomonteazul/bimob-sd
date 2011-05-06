package br.unifor.sd.connection.server.listener;

import java.io.OutputStream;
import java.net.InetAddress;

import br.unifor.sd.connection.UtilConnection;

public class ConnectionEvent {
	private InetAddress address;
	private Object object;
	private boolean connectRequest;
	private OutputStream outputStream;
	
	public void acceptConnection() {
		try {
			outputStream.write(UtilConnection.CONEXAO_OK);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rejectConnection() {
		try {
			outputStream.write(UtilConnection.CONEXAO_FAIL);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}


	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public boolean isConnectRequest() {
		return connectRequest;
	}

	public void setConnectRequest(boolean connectRequest) {
		this.connectRequest = connectRequest;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	

	
}
