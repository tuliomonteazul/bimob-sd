package br.unifor.sd.entity;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = -848706398882561177L;
	private String nome;
	private double valor;
	private double aluguel;
	private int grupoID;
	private Player jogador;

	public Card() {
		super();
	}

	
	public Card(String nome, double valor, double aluguel, int grupoID, Player jogador) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.aluguel = aluguel;
		this.grupoID = grupoID;
		this.jogador = jogador;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getAluguel() {
		return aluguel;
	}

	public void setAluguel(double aluguel) {
		this.aluguel = aluguel;
	}

	public int getGrupoID() {
		return grupoID;
	}


	public void setGrupoID(int grupoID) {
		this.grupoID = grupoID;
	}

	public Player getJogador() {
		return jogador;
	}

	public void setJogador(Player jogador) {
		this.jogador = jogador;
	}
	
}
