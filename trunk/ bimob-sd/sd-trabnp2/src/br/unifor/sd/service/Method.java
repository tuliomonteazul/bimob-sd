package br.unifor.sd.service;

public class Method {
	
	public static final int LIBERAR_VEZ = 1;
	public static final int ANDAR_CASAS = 1;
	
	private int idMethod;
	private Object[] params;

	public Method() {
		super();
	}
	
	public Method(int idMethod) {
		super();
		this.idMethod = idMethod;
	}

	public Method(int idMethod, Object[] params) {
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
