package br.unifor.sd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.unifor.sd.view.tabuleiro.BoardController;

public class Game implements Serializable {
	private static final long serialVersionUID = 9078767081361432461L;
	private List<Player> jogadores;
	private List<Card> casas;
	
	public Game() {
		super();
		this.jogadores = new ArrayList<Player>();
		this.casas = BoardController.getCards();
	}

	public List<Player> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Player> jogadores) {
		this.jogadores = jogadores;
	}

	public List<Card> getCasas() {
		return casas;
	}

	public void setCasas(List<Card> casas) {
		this.casas = casas;
	}

}
