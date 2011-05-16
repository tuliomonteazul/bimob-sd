package br.unifor.sd.service;

import java.util.Arrays;
import java.util.List;

import br.unifor.sd.entity.CorJogador;
import br.unifor.sd.entity.Jogador;

public class JogadorService {
	
	private static JogadorService instance;
	public static final int MAX_JOGADORES = 4;
	
	private final List<CorJogador> cores;
	
	private JogadorService() {
		super();
		cores = Arrays.asList(new CorJogador[]{CorJogador.VERMELHO, CorJogador.VERDE, CorJogador.AZUL, CorJogador.BRANCO});
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
	
	public CorJogador nextColor() {
		if (cores != null && !cores.isEmpty()) {
			CorJogador cor = cores.get(0);
			cores.remove(0);
			return cor;
		} else {
			return null;
		}
	}
}
