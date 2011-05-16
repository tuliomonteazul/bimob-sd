package br.unifor.sd.view.tabuleiro;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Player;

public class BoardController {
	private BoardPanel boardPanel;
	
	private void setPosJogador(Player jogador, int pos) {
		boardPanel.getCasas().get(jogador.getPosicao()).removeJogador(jogador);
		
		boardPanel.getCasas().get(pos).addJogador(jogador);
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		BoardPanel tabuleiro = new BoardPanel();
		frame.add(tabuleiro, BorderLayout.CENTER);
		
		tabuleiro.addCarta(new Card("Disco Virtual", 120, 10, 0, null));
		tabuleiro.addCarta(new Card("Sky Drive", 150, 20, 0, null));
		tabuleiro.addCarta(new Card("Dropbox", 200, 30, 0, null));
		tabuleiro.addCarta(new Card("Mercado Livre", 180, 10, 1, null));
		tabuleiro.addCarta(new Card("Paypal", 230, 10, 1, null));
		tabuleiro.addCarta(new Card("eBay", 250, 10, 1, null));
		tabuleiro.addCarta(new Card("Amazon", 290, 10, 1, null));
		
		
		tabuleiro.addCarta(new Card("Megaupload", 190, 10, 2, null));
		tabuleiro.addCarta(new Card("4shared", 280, 10, 2, null));
		tabuleiro.addCarta(new Card("Rapidshare", 320, 10, 2, null));
		tabuleiro.addCarta(new Card("Youtube", 320, 10, 3, null));
		tabuleiro.addCarta(new Card("Skype", 280, 10, 3, null));
		tabuleiro.addCarta(new Card("Grooveshark", 190, 10, 3, null));

		tabuleiro.addCarta(new Card("Windows Phone", 190, 10, 4, null));
		tabuleiro.addCarta(new Card("Symbian", 210, 10, 4, null));
		tabuleiro.addCarta(new Card("Android", 340, 10, 4, null));
		tabuleiro.addCarta(new Card("iOS", 350, 10, 4, null));
		tabuleiro.addCarta(new Card("LinkedIn", 240, 10, 5, null));
		tabuleiro.addCarta(new Card("Orkut", 410, 10, 5, null));
		tabuleiro.addCarta(new Card("Facebook", 490, 10, 5, null));
		
		tabuleiro.addCarta(new Card("Foursquare", 340, 10, 6, null));
		tabuleiro.addCarta(new Card("Twitter", 390, 10, 6, null));
		tabuleiro.addCarta(new Card("IE", 10, 10, 7, null));
		tabuleiro.addCarta(new Card("Safari", 440, 10, 7, null));
		tabuleiro.addCarta(new Card("Chrome", 450, 10, 7, null));
		tabuleiro.addCarta(new Card("Firefox", 460, 10, 7, null));
		
		
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
