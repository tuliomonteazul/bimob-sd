package br.unifor.sd.entity;

import java.io.Serializable;

public class Carta implements Serializable {
	private static final long serialVersionUID = -848706398882561177L;
	private String nome;
	private double valor;
	private double aluguel;
	private GrupoCarta grupo;
	private Jogador jogador;

	public Carta() {
		super();
	}

	public Carta(String nome, double valor, double aluguel, GrupoCarta grupo, Jogador jogador) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.aluguel = aluguel;
		this.grupo = grupo;
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

	public GrupoCarta getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoCarta grupo) {
		this.grupo = grupo;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
}
