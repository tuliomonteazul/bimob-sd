package br.unifor.sd.view.tabuleiro;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.unifor.sd.entity.ColorPlayer;

public class SquarePanel extends JPanel {

	private static final long serialVersionUID = 509085596174732939L;
	private List<ColorPlayer> jogadores = new ArrayList<ColorPlayer>(); 
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (ColorPlayer jog : jogadores) {
			
			switch (jog) {
			case VERMELHO:
				g.drawImage(jog.getImage(), 2, 0, this);
				break;
			case VERDE:
				g.drawImage(jog.getImage(), 20, 0, this);
				break;
			case AZUL:
				g.drawImage(jog.getImage(), 38, 0, this);
//				g.drawImage(corJog.getImage(), 2, 20, this);
				break;
			case BRANCO:
				g.drawImage(jog.getImage(), 56, 0, this);
//				g.drawImage(corJog.getImage(), 20, 20, this);
				break;
			}
			
		}
	}
	
	public boolean hasPlayer(ColorPlayer player) {
		for (ColorPlayer playerAux : jogadores) {
			if (playerAux.equals(playerAux)) {
				return true;
			}
		}
		return false;
	}
	
	public void addPlayer(ColorPlayer player) {
		jogadores.add(player);
	}
	
	public void removePlayer(ColorPlayer player) {
		jogadores.remove(player);
	}
}
