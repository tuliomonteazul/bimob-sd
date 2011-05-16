package br.unifor.sd.view.tabuleiro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.unifor.sd.entity.Carta;
import br.unifor.sd.entity.CorJogador;
import br.unifor.sd.entity.Jogador;

public class TabuleiroPanel extends JPanel {
	
	private static final long serialVersionUID = -2779961265566761801L;
	private static final int[] QT_CASAS = {
		7, // abaixo
		6, // esquerda
		7, // cima
		6, // direita
	};
	private int countCasas = 0;
	
	private CasaPanel pnInicio;
	private CasaPanel pnVisita;
	private CasaPanel pnWikipedia;
	private CasaPanel pnPrisao;
	private JPanel pnBaixo;
	private JPanel pnEsquerda;
	private JPanel pnCima;
	private JPanel pnDireita;
	private List<CasaPanel> casas = new ArrayList<CasaPanel>();
	
	private GridBagConstraints gbc;
	
	public TabuleiroPanel(){
		
		setLayout(new GridBagLayout());
		setBackground(new Color(232, 236, 239));
		
		addCasaInicio();
		addPanelBaixo();
		addCasaVisita();
		addPanelEsquerda();
		addCasaAtalho();
		addPanelCima();
		addCasaPrisao();
		addPanelDireita();
		
		pnInicio.addJogador(new Jogador(CorJogador.AZUL));
		pnInicio.addJogador(new Jogador(CorJogador.VERMELHO));
		pnInicio.addJogador(new Jogador(CorJogador.BRANCO));
		pnInicio.addJogador(new Jogador(CorJogador.VERDE));
	}

	private void addCasaInicio() {
		pnInicio = new CasaPanel();
		pnInicio.setBackground(Color.WHITE);
		pnInicio.add(new JLabel(new ImageIcon("images/inicio.png")));
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 2;
		add(pnInicio, gbc);
		casas.add(pnInicio);
	}

	private void addPanelBaixo() {
		pnBaixo = new JPanel();
		pnBaixo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		pnBaixo.setLayout(new BoxLayout(pnBaixo, BoxLayout.LINE_AXIS));
		pnBaixo.setBackground(Color.BLACK);
		pnBaixo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		add(pnBaixo, gbc);
	}
	
	private void addCasaVisita() {
		pnVisita = new CasaPanel();
		pnVisita.setBackground(Color.WHITE);
		pnVisita.add(new JLabel(new ImageIcon("images/visitando.png")));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		add(pnVisita, gbc);
		casas.add(pnVisita);
	}
	
	private void addPanelEsquerda() {
		pnEsquerda = new JPanel();
		pnEsquerda.setLayout(new BoxLayout(pnEsquerda, BoxLayout.PAGE_AXIS));
		pnEsquerda.setBackground(Color.BLACK);
		pnEsquerda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(pnEsquerda, gbc);
	}
	
	private void addCasaAtalho() {
		pnWikipedia = new CasaPanel();
		pnWikipedia.setBackground(Color.WHITE);
		pnWikipedia.add(new JLabel(new ImageIcon("images/wikipedia.png")));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(pnWikipedia, gbc);
		casas.add(pnWikipedia);
	}
	
	private void addPanelCima() {
		pnCima = new JPanel();
		pnCima.setLayout(new BoxLayout(pnCima, BoxLayout.X_AXIS));
		pnCima.setBackground(Color.BLACK);
		pnCima.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		add(pnCima, gbc);
	}

	private void addCasaPrisao() {
		pnPrisao = new CasaPanel();
		pnPrisao.setBackground(Color.BLACK);
		pnPrisao.setBackground(Color.WHITE);
		pnPrisao.add(new JLabel(new ImageIcon("images/404.png")));
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(pnPrisao, gbc);
		casas.add(pnPrisao);
	}

	private void addPanelDireita() {
		pnDireita = new JPanel();
		pnDireita.setLayout(new BoxLayout(pnDireita, BoxLayout.Y_AXIS));
		pnDireita.setBackground(Color.BLACK);
		pnDireita.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(pnDireita, gbc);
	}
	
	public void addCarta(Carta carta) {
		countCasas++;
		CartaPanel pnCarta = null;
		if (countCasas <= QT_CASAS[0]) {
			pnCarta = new CartaPanel(carta);
			pnBaixo.add(pnCarta);
			pnBaixo.add(Box.createHorizontalStrut(1));
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1]) {
			pnCarta = new CartaPanel(carta, 90);
			pnEsquerda.add(pnCarta);
			pnEsquerda.add(Box.createVerticalStrut(1));
			if (countCasas == QT_CASAS[0] + QT_CASAS[1]) {
				// inverte a ordem dos comps do panel
				inverterPanel(pnEsquerda);
			}
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1] + QT_CASAS[2]) {
			pnCarta = new CartaPanel(carta, 180);
			pnCima.add(pnCarta);
			pnCima.add(Box.createHorizontalStrut(1));
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1] + QT_CASAS[2] + QT_CASAS[3]) {
			pnCarta = new CartaPanel(carta, 270);
			pnDireita.add(pnCarta);
			pnDireita.add(Box.createVerticalStrut(1));
		}
		casas.add(pnCarta);
		
	}
	
	/**
	 * Inverte a ordem dos componentes de um panel.
	 * @param panel
	 */
	private void inverterPanel(JPanel panel) {
		final List<Component> comps = Arrays.asList(panel.getComponents());
		panel.removeAll();
		Collections.reverse(comps);
		for (Component comp : comps) {
			panel.add(comp);
		}
	}

	public List<CasaPanel> getCasas() {
		return casas;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		TabuleiroPanel tabuleiro = new TabuleiroPanel();
		frame.add(tabuleiro, BorderLayout.CENTER);
		
		tabuleiro.addCarta(new Carta("Disco Virtual", 120, 10, 0, null));
		tabuleiro.addCarta(new Carta("Sky Drive", 150, 20, 0, null));
		tabuleiro.addCarta(new Carta("Dropbox", 200, 30, 0, null));
		tabuleiro.addCarta(new Carta("Mercado Livre", 180, 10, 1, null));
		tabuleiro.addCarta(new Carta("Paypal", 230, 10, 1, null));
		tabuleiro.addCarta(new Carta("eBay", 250, 10, 1, null));
		tabuleiro.addCarta(new Carta("Amazon", 290, 10, 1, null));
		
		
		tabuleiro.addCarta(new Carta("Megaupload", 190, 10, 2, null));
		tabuleiro.addCarta(new Carta("4shared", 280, 10, 2, null));
		tabuleiro.addCarta(new Carta("Rapidshare", 320, 10, 2, null));
		tabuleiro.addCarta(new Carta("Youtube", 320, 10, 3, null));
		tabuleiro.addCarta(new Carta("Skype", 280, 10, 3, null));
		tabuleiro.addCarta(new Carta("Grooveshark", 190, 10, 3, null));

		tabuleiro.addCarta(new Carta("Windows Phone", 190, 10, 4, null));
		tabuleiro.addCarta(new Carta("Symbian", 210, 10, 4, null));
		tabuleiro.addCarta(new Carta("Android", 340, 10, 4, null));
		tabuleiro.addCarta(new Carta("iOS", 350, 10, 4, null));
		tabuleiro.addCarta(new Carta("LinkedIn", 240, 10, 5, null));
		tabuleiro.addCarta(new Carta("Orkut", 410, 10, 5, null));
		tabuleiro.addCarta(new Carta("Facebook", 490, 10, 5, null));
		
		tabuleiro.addCarta(new Carta("Foursquare", 340, 10, 6, null));
		tabuleiro.addCarta(new Carta("Twitter", 390, 10, 6, null));
		tabuleiro.addCarta(new Carta("IE", 10, 10, 7, null));
		tabuleiro.addCarta(new Carta("Safari", 440, 10, 7, null));
		tabuleiro.addCarta(new Carta("Chrome", 450, 10, 7, null));
		tabuleiro.addCarta(new Carta("Firefox", 460, 10, 7, null));
		
		
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
