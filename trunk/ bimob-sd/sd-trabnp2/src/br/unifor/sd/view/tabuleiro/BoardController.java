package br.unifor.sd.view.tabuleiro;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Player;

public class BoardController {
	
	private BoardPanel boardPanel;
	
	private Player player;
	
	private static BoardController instance;
	private BoardController() {
		super();
		boardPanel = new BoardPanel();
	}
	public static BoardController getInstance() {
		if (instance == null) {
			instance = new BoardController();
		}
		return instance;
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
	
	
//	private void setPosJogador(Player jogador, int pos) {
//		boardPanel.getCasas().get(jogador.getPosicao()).removePlayer(jogador.getCor());
//		
//		boardPanel.getCasas().get(pos).addPlayer(jogador.getCor());
//	}


	public void liberarVez() {
		boardPanel.exibirDado();
	}


	public void exibirMsg(final String msg) {
		boardPanel.getLbMsg().setText(msg);
	}
	
	public void mover(final Player player, final int casas) {
		int posPanelAtual = -1;
		for (int i = 0; i < boardPanel.getCasas().size(); i++) {
			final SquarePanel squarePanel = boardPanel.getCasas().get(i);
			if (squarePanel.hasPlayer(player.getCor())) {
				squarePanel.removePlayer(player.getCor());
				squarePanel.updateUI();
				posPanelAtual = i;
				break;
			}
		}
		
		for (int i = posPanelAtual + 1; i < posPanelAtual + casas; i++) {
			final SquarePanel squarePanel = boardPanel.getCasas().get(i % 30);
			squarePanel.addPlayer(player.getCor());
			squarePanel.updateUI();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			squarePanel.removePlayer(player.getCor());
			squarePanel.updateUI();
		}
		
		final SquarePanel squarePanel = boardPanel.getCasas().get((posPanelAtual + casas) % 30);
		squarePanel.addPlayer(player.getCor());
		squarePanel.updateUI();
	}
	
	public static List<Card> getCards() {
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		boardPanel.getPnInfo().showInfo(player);
	}

}
