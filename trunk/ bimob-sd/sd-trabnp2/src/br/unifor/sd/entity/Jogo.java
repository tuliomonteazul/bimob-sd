package br.unifor.sd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogo implements Serializable {
	private static final long serialVersionUID = 9078767081361432461L;
	private List<Jogador> jogadores;
	private Map<Integer, Carta> casas;
	
	public Jogo() {
		super();
		this.jogadores = new ArrayList<Jogador>();
		this.casas = new HashMap<Integer, Carta>();
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public Map<Integer, Carta> getCasas() {
		return casas;
	}

	public void setCasas(Map<Integer, Carta> casas) {
		this.casas = casas;
	}

}
