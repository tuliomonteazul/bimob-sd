package br.unifor.sd.view.tabuleiro;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Player;

public class BoardController {
	private BoardPanel boardPanel = new BoardPanel();
	
	public BoardController() {
		super();
	}
	
	public void init() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(boardPanel, BorderLayout.CENTER);
		
		for (Card card : getCards()) {
			boardPanel.addCarta(card);
		}
		
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void setPosJogador(Player jogador, int pos) {
		boardPanel.getCasas().get(jogador.getPosicao()).removeJogador(jogador);
		
		boardPanel.getCasas().get(pos).addJogador(jogador);
	}


	public void liberarVez() {
		boardPanel.exibirDado();
	}


	public void exibirMsg(final String msg) {
		boardPanel.getLbMsg().setText(msg);
		boardPanel.getPnMsg().updateUI();
	}
	
	private List<Card> getCards() {
		final List<Card> cards = new ArrayList<Card>();
		cards.add(new Card("Disco Virtual", 120, 10, 0, null));
		cards.add(new Card("Sky Drive", 150, 20, 0, null));
		cards.add(new Card("Dropbox", 200, 30, 0, null));
		cards.add(new Card("Mercado Livre", 180, 10, 1, null));
		cards.add(new Card("Paypal", 230, 10, 1, null));
		cards.add(new Card("eBay", 250, 10, 1, null));
		cards.add(new Card("Amazon", 290, 10, 1, null));
		
		
		cards.add(new Card("Megaupload", 190, 10, 2, null));
		cards.add(new Card("4shared", 280, 10, 2, null));
		cards.add(new Card("Rapidshare", 320, 10, 2, null));
		cards.add(new Card("Youtube", 320, 10, 3, null));
		cards.add(new Card("Skype", 280, 10, 3, null));
		cards.add(new Card("Grooveshark", 190, 10, 3, null));

		cards.add(new Card("Windows Phone", 190, 10, 4, null));
		cards.add(new Card("Symbian", 210, 10, 4, null));
		cards.add(new Card("Android", 340, 10, 4, null));
		cards.add(new Card("iOS", 350, 10, 4, null));
		cards.add(new Card("LinkedIn", 240, 10, 5, null));
		cards.add(new Card("Orkut", 410, 10, 5, null));
		cards.add(new Card("Facebook", 490, 10, 5, null));
		
		cards.add(new Card("Foursquare", 340, 10, 6, null));
		cards.add(new Card("Twitter", 390, 10, 6, null));
		cards.add(new Card("IE", 10, 10, 7, null));
		cards.add(new Card("Safari", 440, 10, 7, null));
		cards.add(new Card("Chrome", 450, 10, 7, null));
		cards.add(new Card("Firefox", 460, 10, 7, null));
		return cards;
	}


	
}
