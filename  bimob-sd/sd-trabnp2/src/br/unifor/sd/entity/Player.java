package br.unifor.sd.entity;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
	private static final long serialVersionUID = -7597865929169679095L;
	private static final double START_MONEY = 2500.0;
	private String nome;
	private int clientID;
	private ColorPlayer cor;
	private double dinheiro;
	private List<Card> cartas;
	private int posicao;
	
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

	public List<Card> getCartas() {
		return cartas;
	}

	public void setCartas(List<Card> cartas) {
		this.cartas = cartas;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	

}
