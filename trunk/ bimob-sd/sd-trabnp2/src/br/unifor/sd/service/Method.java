package br.unifor.sd.service;

import java.io.Serializable;

public class Method implements Serializable {
	
	public static final int CONECTOU = 0;
	public static final int LIBERAR_VEZ = 1;
	public static final int EXIBIR_MSG = 2;
	
	public static final int JOGADA_ANDAR = 3;
	public static final int MOVER = 4;
	public static final int POSSIBILITA_COMPRA = 5;
	public static final int JOGADA_COMPRA = 6;
	public static final int ATUALIZA_COMPRA = 7;
	public static final int JOGADA_PASSAR = 8;
	public static final int COBRAR_ALUGUEL = 9;
	public static final int JOGADA_PAGAR = 10;
	public static final int JOGADAR_SAIR = 11;
	public static final int ATUALIZA_DINHEIRO = 12;
	public static final int FIM_JOGO = 13;
	public static final int REMOVER_JOGADOR = 14;
	
	public static final int ESCREVER_CONSOLE = 15;
	
	
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
