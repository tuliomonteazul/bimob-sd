package br.unifor.sd.view.client;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.Player;
import br.unifor.sd.entity.SpecialType;
import br.unifor.sd.service.client.ClientOutputService;
import br.unifor.sd.view.client.tabuleiro.BoardPanel;
import br.unifor.sd.view.client.tabuleiro.CardPanel;
import br.unifor.sd.view.client.tabuleiro.ConsolePanel;
import br.unifor.sd.view.client.tabuleiro.DicePanel;
import br.unifor.sd.view.client.tabuleiro.SquarePanel;

public class PlayerController {
	
	private BoardPanel boardPanel;
	private ConsolePanel consolePanel;
	
	private Player player;
	
	private ClientOutputService clientOutputService = ClientOutputService.getInstance();
	
	private boolean jogando = true;
	
	private static PlayerController instance;
	private PlayerController() {
		super();
		boardPanel = new BoardPanel();
		consolePanel = new ConsolePanel();
	}
	public static PlayerController getInstance() {
		if (instance == null) {
			instance = new PlayerController();
		}
		return instance;
	}
	
	
	public void init() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(boardPanel, BorderLayout.CENTER);
		
		for (Card card : getCards()) {
			boardPanel.addCarta(card);
		}
		
		frame.add(consolePanel, BorderLayout.SOUTH);
		
		frame.setTitle("Weblopoly - Monopolize a internet");
		frame.setVisible(true);
		frame.setSize(500, 800);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	/**
	 * Exibe o dado para o jogador possa jogar.
	 */
	public void liberarVez() {
		boardPanel.exibirDado();
	}


	/**
	 * Exibe uma mensagem dentro do tabuleiro.
	 * @param msg
	 */
	public void exibirMsg(final String msg) {
		if (jogando) {
			boardPanel.getLbMsg().setText(msg);
		}
	}
	
	/**
	 * Move o jogador N casas, onde N é a variável 'casas' passada por parâmetro.
	 * @param player jogador que será movido
	 * @param casas quantidade de casas que ele irá percorrer
	 */
	public void mover(final Player player, final int casas) {
		SwingUtilities.windowForComponent(boardPanel).setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		boardPanel.getPnDado().setAndando(true);
		
		int posPanelAtual = -1;
		// procura a casa onde o jogador está
		for (int i = 0; i < boardPanel.getCasas().size(); i++) {
			final SquarePanel squarePanel = boardPanel.getCasas().get(i);
			if (squarePanel.hasPlayer(player.getCor())) {
				squarePanel.removePlayer(player.getCor());
				squarePanel.updateUI();
				posPanelAtual = i;
				break;
			}
		}
		
		// realiza o movimento de andar pelas casas
		for (int i = posPanelAtual + 1; i < posPanelAtual + casas; i++) {
			// utiliza mod 30 pois são 30 casas no tabuleiro e é preciso recomeçar da posição 0 depois da 30
			final SquarePanel squarePanel = boardPanel.getCasas().get(i % 30);
			squarePanel.addPlayer(player.getCor());
			squarePanel.updateUI();
			// aplica intervalo entre os movimentos para que o usuário visualize a ação
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			squarePanel.removePlayer(player.getCor());
			squarePanel.updateUI();
		}
		
		// adiciona o jogador na última casa aonde ele deve ficar
		// utiliza mod 30 pois são 30 casas no tabuleiro e é preciso recomeçar da posição 0 depois da 30
		final SquarePanel squarePanel = boardPanel.getCasas().get((posPanelAtual + casas) % 30);
		squarePanel.addPlayer(player.getCor());
		squarePanel.updateUI();
		boardPanel.esconderDado();
		
		SwingUtilities.windowForComponent(boardPanel).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		boardPanel.getPnDado().setAndando(false);
	}

	/**
	 * Libera uma carta para o jogador poder comprar
	 * @param card
	 */
	public void possibilitaCompra(Card card) {
		StringBuilder msg = new StringBuilder();
		msg.append("Propriedade sem dono");
		msg.append("\nNome: "+card.getNome());
		msg.append("\nValor: "+card.getValor());
		msg.append("\nAluguel: "+card.getAluguel());
		String[] options = new String[]{"Quero comprar!", "Não quero", "Queria, mas tô liso"};
		// exibe a janela com as opção
		int option = JOptionPane.showOptionDialog(boardPanel, msg.toString(), "Selecione uma opção", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Quero comprar!");
		
		// se escolher que quer comprar
		if (option == 0) {
			// validar dinheiro
			if (player.getDinheiro() > card.getValor()) {
				clientOutputService.buy(card);
			} else {
				// se não tiver dinheiro
				JOptionPane.showMessageDialog(boardPanel, "Você não possui dinheiro suficiente!");
				clientOutputService.nextPlayer();
			}
		} else {
			// se não quis comprar
			clientOutputService.nextPlayer();
		}
		boardPanel.esconderDado();
		
	}

	/**
	 * Atualiza uma casa do tabuleiro de acordo com o seu novo dono que acabou de comprá-la. Um ícone com uma coroa será
	 * exibido na casa com a cor do jogador proprietário.
	 * 
	 * @param card
	 */
	public void atualizaCompra(Card card) {
		// procura a casa pelo nome
		for (SquarePanel sqPanel : boardPanel.getCasas()) {
			if (sqPanel instanceof CardPanel) {
				CardPanel cardPanel = (CardPanel) sqPanel;
				if (cardPanel.getCarta().getNome().equals(card.getNome())) {
					// seta o jogador como dono
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
	
	public void exibeCobranca(Card card) {
		if (player.getDinheiro() > card.getAluguel()) {
			JOptionPane.showMessageDialog(boardPanel, "Você deve pagar o aluguel da aplicação: "+card.getNome()+"\nValor: "+card.getAluguel());
			clientOutputService.pay(card);
		} else {
			JOptionPane.showMessageDialog(boardPanel, "Você não possui dinheiro suficiente para pagar o aluguel\nValor: "+card.getAluguel()+"\nGame over! :(");
			clientOutputService.exit();
		}
		boardPanel.esconderDado();
	}
	
	public void atualizaDinheiro(double novoDinheiro) {
		player.setDinheiro(novoDinheiro);
		boardPanel.getPnInfo().showInfo(player);
	}
	
	public void fimJogo() {
		boardPanel.getPnInfo().setVisible(false);
		boardPanel.getLbMsg().setText("Você está fora do jogo.");
		jogando = false;
	}
	
	public void removerJogador(Player player) {
		// procura a casa onde o jogador está e o remove
		for (int i = 0; i < boardPanel.getCasas().size(); i++) {
			final SquarePanel squarePanel = boardPanel.getCasas().get(i);
			if (squarePanel.hasPlayer(player.getCor())) {
				squarePanel.removePlayer(player.getCor());
				squarePanel.updateUI();
				break;
			}
		}
		
	}
	
	public void escreverConsole(Player player, String msg) {
		
		String text = "";
		if (player != null) {
			text = "Jogador " + player.getCor().getText()+": ";
		} else {
			text = "[Jogo] ";
		}
		text += msg;
		
		consolePanel.addText(text);
	}
	
	public void receba200(double dinheiro) {
		JOptionPane.showMessageDialog(boardPanel, "Parabéns! Você recebeu R$ 200,00.");
		boardPanel.esconderDado();
		player.setDinheiro(dinheiro);
		boardPanel.getPnInfo().showInfo(player);
		clientOutputService.nextPlayer();
	}
	
	public void voceVenceu() {
		JOptionPane.showMessageDialog(boardPanel, "Parabéns! Você venceu o jogo! :D");
		boardPanel.esconderDado();
		boardPanel.getLbMsg().setText("Fim de jogo! Você venceu!");
	}
	
	/**
	 * Exibe mensagem de erro de conexão.
	 */
	public void errorConnection() {
		JOptionPane.showMessageDialog(boardPanel, "Não foi possível se conectar ao servidor");
		System.exit(0);
	}
	
	/**
	 * Popula as cartas do tabuleiro.
	 * @return List
	 */
	public static List<Card> getCards() {
		final List<Card> cards = new ArrayList<Card>();
		cards.add(new Card("Inicio", 0, 0, 0, null, true, SpecialType.INICIO));
		
		cards.add(new Card("SugarSync", 120, 12, 0, null));
		cards.add(new Card("MobileMe", 150, 18, 0, null));
		cards.add(new Card("Dropbox", 200, 25, 0, null));
		cards.add(new Card("Mercado Livre", 180, 20, 1, null));
		cards.add(new Card("Paypal", 230, 22, 1, null));
		cards.add(new Card("eBay", 250, 28, 1, null));
		cards.add(new Card("Amazon", 260, 31, 1, null));
		
		cards.add(new Card("Prisao Visita", 0, 0, 0, null, true, SpecialType.VISITA));
		
		cards.add(new Card("Grooveshark", 190, 20, 2, null));
		cards.add(new Card("Skype", 280, 29, 2, null));
		cards.add(new Card("Youtube", 320, 35, 2, null));
		cards.add(new Card("Yahoo! Mail", 190, 22, 3, null));
		cards.add(new Card("Hotmail", 240, 28, 3, null));
		cards.add(new Card("Gmail", 300, 31, 3, null));
		
		cards.add(new Card("Wikipedia", 0, 0, 0, null, true, SpecialType.WIKIPEDIA));

		cards.add(new Card("Windows Phone", 190, 15, 4, null));
		cards.add(new Card("Symbian", 210, 19, 4, null));
		cards.add(new Card("Android", 340, 38, 4, null));
		cards.add(new Card("iOS", 350, 40, 4, null));
		cards.add(new Card("LinkedIn", 240, 21, 5, null));
		cards.add(new Card("Orkut", 320, 33, 5, null));
		cards.add(new Card("Facebook", 490, 54, 5, null));
		
		cards.add(new Card("Prisao", 0, 0, 0, null, true, SpecialType.PRISAO));
		
		cards.add(new Card("Foursquare", 340, 31, 6, null));
		cards.add(new Card("Twitter", 410, 43, 6, null));
		cards.add(new Card("IE", 10, 0.5, 7, null));
		cards.add(new Card("Safari", 440, 49, 7, null));
		cards.add(new Card("Chrome", 540, 62, 7, null));
		cards.add(new Card("Firefox", 550, 65, 7, null));
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
