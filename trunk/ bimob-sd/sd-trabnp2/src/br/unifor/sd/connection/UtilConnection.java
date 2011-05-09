package br.unifor.sd.connection;

public class UtilConnection {
	public static final int CONEXAO = 0xA0;
	public static final int CONEXAO_OK = 0xA1;
	public static final int CONEXAO_FAIL = 0xA2;
	public static final int MSG = 0xB0;
	
	private static int SEQ_CLIENT_ID = 1;
	public static int generateClientID() {
		return SEQ_CLIENT_ID++;
	}
}
