package br.unifor.sd.view.client;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Player;
import br.unifor.sd.service.client.ClientOutputService;
import br.unifor.sd.view.client.tabuleiro.BoardPanel;
import br.unifor.sd.view.client.tabuleiro.CardPanel;
import br.unifor.sd.view.client.tabuleiro.SquarePanel;

public class PlayerController {
	
	private BoardPanel boardPanel;
	
	private Player player;
	
	private ClientOutputService clientOutputService = ClientOutputService.getInstance();
	
	private static PlayerController instance;
	private PlayerController() {
		super();
		boardPanel = new BoardPanel();
	}
	public static PlayerController getInstance() {
		if (instance == null) {
			instance = new PlayerController();
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
		frame.setSize(500, 800);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
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

	public void possibilitaCompra(Card card) {
		StringBuilder msg = new StringBuilder();
		msg.append("Propriedade sem dono");
		msg.append("\nNome: "+card.getNome());
		msg.append("\nValor: "+card.getValor());
		msg.append("\nAluguel: "+card.getAluguel());
		String[] options = new String[]{"Quero comprar!", "Não quero", "Queria, mas tô liso"};
		int option = JOptionPane.showOptionDialog(boardPanel, msg.toString(), "Selecione uma opção", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Quero comprar!");
		
		if (option == 0) {
			// validar dinheiro
			if (player.getDinheiro() > card.getValor()) {
				clientOutputService.buy(card);
			} else {
				JOptionPane.showMessageDialog(boardPanel, "Você não possui dinheiro suficiente!");
				clientOutputService.none();
			}
		} else {
			clientOutputService.none();
		}
		boardPanel.esconderDado();
		
	}
	
	public void atualizaCompra(Card card) {
		for (SquarePanel sqPanel : boardPanel.getCasas()) {
			if (sqPanel instanceof CardPanel) {
				CardPanel cardPanel = (CardPanel) sqPanel;
				if (cardPanel.getCarta().getNome().equals(card.getNome())) {
					sqPanel.setOwner(card.getJogador().getCor());
					sqPanel.updateUI();
					break;
				}
			}
		}
		
		// se fui eu quem comprei a carta
		if (player.getClientID() == card.getJogador().getClientID()) {
			// atualizar dinheiro
			player.setDinheiro(card.getJogador().getDinheiro());
			boardPanel.getPnInfo().updateMoney(card.getJogador().getDinheiro());
		}
		
	}
	
	public void errorConnection() {
		JOptionPane.showMessageDialog(boardPanel, "Não foi possível se conectar ao servidor");
		System.exit(0);
	}
	
	public static List<Card> getCards() {
		final List<Card> cards = new ArrayList<Card>();
		cards.add(new Card("Inicio", 0, 0, 0, null, true));
		
		cards.add(new Card("SugarSync", 120, 10, 0, null));
		cards.add(new Card("MobileMe", 150, 20, 0, null));
		cards.add(new Card("Dropbox", 200, 30, 0, null));
		cards.add(new Card("Mercado Livre", 180, 10, 1, null));
		cards.add(new Card("Paypal", 230, 10, 1, null));
		cards.add(new Card("eBay", 250, 10, 1, null));
		cards.add(new Card("Amazon", 290, 10, 1, null));
		
		cards.add(new Card("Prisao Visita", 0, 0, 0, null, true));
		
		cards.add(new Card("Yahoo! Mail", 190, 10, 2, null));
		cards.add(new Card("Hotmail", 280, 10, 2, null));
		cards.add(new Card("Gmail", 320, 10, 2, null));
		cards.add(new Card("Youtube", 320, 10, 3, null));
		cards.add(new Card("Skype", 280, 10, 3, null));
		cards.add(new Card("Grooveshark", 190, 10, 3, null));
		
		cards.add(new Card("Wikipedia", 0, 0, 0, null, true));

		cards.add(new Card("Windows Phone", 190, 10, 4, null));
		cards.add(new Card("Symbian", 210, 10, 4, null));
		cards.add(new Card("Android", 340, 10, 4, null));
		cards.add(new Card("iOS", 350, 10, 4, null));
		cards.add(new Card("LinkedIn", 240, 10, 5, null));
		cards.add(new Card("Orkut", 410, 10, 5, null));
		cards.add(new Card("Facebook", 490, 10, 5, null));
		
		cards.add(new Card("Prisao", 0, 0, 0, null, true));
		
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
