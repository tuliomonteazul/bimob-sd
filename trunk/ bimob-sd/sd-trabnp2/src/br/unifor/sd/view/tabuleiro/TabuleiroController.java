package br.unifor.sd.view.tabuleiro;

import br.unifor.sd.entity.Jogador;

public class TabuleiroController {
	private TabuleiroPanel tabuleiroPanel;
	
	private void setPosJogador(Jogador jogador, int pos) {
		tabuleiroPanel.getCasas().get(jogador.getPosicao()).removeJogador(jogador);
		
		tabuleiroPanel.getCasas().get(pos).addJogador(jogador);
	}

}
