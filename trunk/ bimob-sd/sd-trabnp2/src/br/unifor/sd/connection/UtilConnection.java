package br.unifor.sd.connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class UtilConnection {
	public static final int CONEXAO = 0xA0;
	public static final int CONEXAO_OK = 0xA1;
	public static final int CONEXAO_FAIL = 0xA2;
	public static final int MSG = 0xB0;
	
	private static int SEQ_CLIENT_ID = 1;
	public static int generateClientID() {
		return SEQ_CLIENT_ID++;
	}
	
	public static byte[] objectToByteArray(Object objeto) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput saida = new ObjectOutputStream(bos);
		saida.writeObject(objeto);
		saida.flush();

		return bos.toByteArray();
	}
	
	public static Object byteArrayToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(bytes,0,bytes.length));
		
		return in.readObject();
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		ObjectOutput saida = new ObjectOutputStream(bos);
//		saida.writeObject(objeto);
//		saida.flush();
//		
//		return bos.toByteArray();
	}
}
