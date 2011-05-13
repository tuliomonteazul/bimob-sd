package br.unifor.sd.entity;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

public class Jogador implements Serializable {
	private static final long serialVersionUID = -7597865929169679095L;
	private static final double START_MONEY = 2500.0;
	private String nome;
	private int clientID;
	private Color cor;
	private double dinheiro;
	private List<Carta> cartas;
	private int posicao;

	public Jogador() {
		super();
		this.dinheiro = START_MONEY;
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

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public double getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	

}
