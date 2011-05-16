package br.unifor.sd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game implements Serializable {
	private static final long serialVersionUID = 9078767081361432461L;
	private List<Player> jogadores;
	private Map<Integer, Card> casas;
	
	public Game() {
		super();
		this.jogadores = new ArrayList<Player>();
		this.casas = new HashMap<Integer, Card>();
	}

	public List<Player> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Player> jogadores) {
		this.jogadores = jogadores;
	}

	public Map<Integer, Card> getCasas() {
		return casas;
	}

	public void setCasas(Map<Integer, Card> casas) {
		this.casas = casas;
	}

}
