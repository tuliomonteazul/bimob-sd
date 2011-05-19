package br.unifor.sd.service;

import java.io.Serializable;

public class Method implements Serializable {
	
	public static final int CONECTOU = 0;
	public static final int LIBERAR_VEZ = 1;
	public static final int EXIBIR_MSG = 2;
	
	public static final int JOGADA_ANDAR = 3;
	public static final int MOVER = 4;
	
	private int idMethod;
	private Object[] params;

	public Method() {
		super();
	}
	
	public Method(int idMethod) {
		super();
		this.idMethod = idMethod;
	}

	public Method(int idMethod, Object... params) {
		super();
		this.idMethod = idMethod;
		this.params = params;
	}

	public int getIdMethod() {
		return idMethod;
	}

	public void setIdMethod(int idMethod) {
		this.idMethod = idMethod;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
