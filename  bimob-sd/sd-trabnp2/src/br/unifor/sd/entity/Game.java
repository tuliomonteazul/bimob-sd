package br.unifor.sd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.unifor.sd.view.client.PlayerController;

public class Game implements Serializable {
	private static final long serialVersionUID = 9078767081361432461L;
	private List<Player> jogadores;
	private List<Card> casas;
	private boolean iniciado;
	
	public Game() {
		super();
		this.jogadores = new ArrayList<Player>();
		this.casas = PlayerController.getCards();
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

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

}
