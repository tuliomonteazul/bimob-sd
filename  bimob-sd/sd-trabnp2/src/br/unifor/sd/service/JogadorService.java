package br.unifor.sd.service;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import br.unifor.sd.entity.Jogador;

public class JogadorService {
	
	private static JogadorService instance;
	public static final int MAX_JOGADORES = 4;
	
	private final List<Color> cores;
	
	private JogadorService() {
		super();
		cores = Arrays.asList(new Color[]{Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE});
	}
	
	public static JogadorService getInstance(){
		if (instance == null) {
			instance = new JogadorService();
		}
		return instance;
	}
	
	public Jogador findJogador(List<Jogador> jogadores, String nome) {
		for (Jogador jogador : jogadores) {
			if (jogador.getNome().equalsIgnoreCase(nome)) {
				return jogador;
			}
		}
		return null;
	}
	
	public Color nextColor() {
		if (cores != null && !cores.isEmpty()) {
			Color cor = cores.get(0);
			cores.remove(0);
			return cor;
		} else {
			return null;
		}
	}
}
