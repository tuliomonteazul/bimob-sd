package br.unifor.sd.view.tabuleiro;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.unifor.sd.entity.Jogador;

public class CasaPanel extends JPanel {

	private static final long serialVersionUID = 509085596174732939L;
	private List<Jogador> jogadores = new ArrayList<Jogador>(); 
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (Jogador jog : jogadores) {
			
			switch (jog.getCor()) {
			case VERMELHO:
				g.drawImage(jog.getCor().getImage(), 2, 0, this);
				break;
			case VERDE:
				g.drawImage(jog.getCor().getImage(), 20, 0, this);
				break;
			case AZUL:
				g.drawImage(jog.getCor().getImage(), 38, 0, this);
//				g.drawImage(corJog.getImage(), 2, 20, this);
				break;
			case BRANCO:
				g.drawImage(jog.getCor().getImage(), 56, 0, this);
//				g.drawImage(corJog.getImage(), 20, 20, this);
				break;
			}
			
		}
	}
	
	public void addJogador(Jogador jogador) {
		jogadores.add(jogador);
		jogador.setPosicao(jogadores.size()-1);
	}
	
	public void removeJogador(Jogador jogador) {
		jogadores.remove(jogador);
	}
}
