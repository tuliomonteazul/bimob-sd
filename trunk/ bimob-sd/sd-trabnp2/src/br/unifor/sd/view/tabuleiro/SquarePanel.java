package br.unifor.sd.view.tabuleiro;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.unifor.sd.entity.ColorPlayer;

public class SquarePanel extends JPanel {

	private static final long serialVersionUID = 509085596174732939L;
	private List<ColorPlayer> jogadores = new ArrayList<ColorPlayer>();
	
	private ColorPlayer owner;
	private Image ownRed = Toolkit.getDefaultToolkit().getImage("images/crown_red.png");
	private Image ownGreen = Toolkit.getDefaultToolkit().getImage("images/crown_green.png");
	private Image ownBlue = Toolkit.getDefaultToolkit().getImage("images/crown_blue.png");
	private Image ownWhite = Toolkit.getDefaultToolkit().getImage("images/crown_white.png");
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (ColorPlayer jog : jogadores) {
			
			switch (jog) {
			case VERMELHO:
				g.drawImage(jog.getImage(), 2, 0, this);
				break;
			case VERDE:
				g.drawImage(jog.getImage(), 22, 0, this);
				break;
			case AZUL:
				g.drawImage(jog.getImage(), 42, 0, this);
				break;
			case BRANCO:
				g.drawImage(jog.getImage(), 62, 0, this);
				break;
			}
			
		}
		
		if (owner != null) {
			switch (owner) {
				case VERMELHO:
					g.drawImage(ownRed, 2, 57, this);
					break;
				case VERDE:
					g.drawImage(ownGreen, 2, 57, this);
					break;
				case AZUL:
					g.drawImage(ownBlue, 2, 57, this);
					break;
				case BRANCO:
					g.drawImage(ownWhite, 2, 57, this);
					break;
			}
		}
	}
	
	public boolean hasPlayer(ColorPlayer player) {
		for (ColorPlayer playerAux : jogadores) {
			if (playerAux.equals(player)) {
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
