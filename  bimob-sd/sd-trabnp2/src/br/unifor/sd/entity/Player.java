package br.unifor.sd.entity;

import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = -7597865929169679095L;
	private static final double START_MONEY = 2000.0;
	private String nome;
	private int clientID;
	private ColorPlayer cor;
	private double dinheiro;
	private int posicao;
	private int prisao;
	
	public Player() {
		super();
		this.dinheiro = START_MONEY;
	}

	public Player(ColorPlayer cor) {
		this.cor = cor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}


	public ColorPlayer getCor() {
		return cor;
	}

	public void setCor(ColorPlayer cor) {
		this.cor = cor;
	}

	public double getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	public void addPosicao(int casas) {
		// adiciona as casas
		posicao += casas;
		// caso já tenha ultrapassado as 30 casas, começa denovo
		posicao = posicao % 30;
	}

	public void addDinheiro(double dinheiro) {
		this.dinheiro += dinheiro;
	}
	
	public int getPrisao() {
		return prisao;
	}

	public void setPrisao(int prisao) {
		this.prisao = prisao;
	}

	public Player clone() {
		final Player clone = new Player();
		clone.setClientID(clientID);
		clone.setCor(cor);
		clone.setDinheiro(dinheiro);
		clone.setNome(nome);
		clone.setPosicao(posicao);
		clone.setPrisao(prisao);
		return clone;
	}
}
