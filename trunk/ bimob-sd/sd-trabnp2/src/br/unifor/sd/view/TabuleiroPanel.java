package br.unifor.sd.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.unifor.sd.entity.Carta;

public class TabuleiroPanel extends JPanel {
	
	private static final int[] QT_CASAS = {
		7, // abaixo
		6, // esquerda
		7, // cima
		6, // direita
	};
	private static final int LARGURA_CASA = 65;
	private static final int ALTURA_CASA = 60;
	private int countCasas = 0;
	
	private JPanel pnInicio;
	private JPanel pnBaixo;
	private JPanel pnVisita;
	private JPanel pnEsquerda;
	private JPanel pnAtalho;
	private JPanel pnCima;
	private JPanel pnPrisao;
	private JPanel pnDireita;
	private List<CartaPanel> pnCartas = new ArrayList<CartaPanel>();
	
	private GridBagConstraints gbc;
	
	public TabuleiroPanel(){
		
		setLayout(new GridBagLayout());
		
		addCasaInicio();
		addPanelBaixo();
		addCasaVisita();
		addPanelEsquerda();
		addCasaAtalho();
		addPanelCima();
		addCasaPrisao();
		addPanelDireita();
	}

	private void addCasaInicio() {
		pnInicio = new JPanel();
		pnInicio.setBackground(Color.black);
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.ipadx = ALTURA_CASA;
		gbc.ipady = ALTURA_CASA;
		add(pnInicio, gbc);
	}

	private void addPanelBaixo() {
		pnBaixo = new JPanel();
		pnBaixo.setLayout(new BoxLayout(pnBaixo, BoxLayout.X_AXIS));
		pnBaixo.setBackground(Color.BLACK);
		pnBaixo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		add(pnBaixo, gbc);
	}
	
	private void addCasaVisita() {
		pnVisita = new JPanel();
		pnVisita.setBackground(Color.BLACK);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipadx = ALTURA_CASA;
		gbc.ipady = ALTURA_CASA;
		add(pnVisita, gbc);
	}
	
	private void addPanelEsquerda() {
		pnEsquerda = new JPanel();
		pnEsquerda.setLayout(new BoxLayout(pnEsquerda, BoxLayout.Y_AXIS));
		pnEsquerda.setBackground(Color.BLACK);
		pnEsquerda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(pnEsquerda, gbc);
	}
	
	private void addCasaAtalho() {
		pnAtalho = new JPanel();
		pnAtalho.setBackground(Color.BLACK);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = ALTURA_CASA;
		gbc.ipady = ALTURA_CASA;
		add(pnAtalho, gbc);
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
		pnPrisao = new JPanel();
		pnPrisao.setBackground(Color.BLACK);
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.ipadx = ALTURA_CASA;
		gbc.ipady = ALTURA_CASA;
		add(pnPrisao, gbc);
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
		if (countCasas <= QT_CASAS[0]) {
			final CartaPanel pnCarta = new CartaPanel(carta);
			pnBaixo.add(pnCarta);
			pnBaixo.add(Box.createHorizontalStrut(1));
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1]) {
			final CartaPanel pnCarta = new CartaPanel(carta, 90);
			pnEsquerda.add(pnCarta);
			pnEsquerda.add(Box.createVerticalStrut(1));
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1] + QT_CASAS[2]) {
			final CartaPanel pnCarta = new CartaPanel(carta, 180);
			pnCima.add(pnCarta);
			pnCima.add(Box.createHorizontalStrut(1));
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1] + QT_CASAS[2] + QT_CASAS[3]) {
			final CartaPanel pnCarta = new CartaPanel(carta, 270);
			pnDireita.add(pnCarta);
			pnDireita.add(Box.createVerticalStrut(1));
		}
		
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
