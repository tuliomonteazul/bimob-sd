package br.unifor.sd.view.tabuleiro;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import br.unifor.sd.entity.Carta;
import br.unifor.sd.entity.Jogador;

public class TabuleiroController {
	private TabuleiroPanel tabuleiroPanel;
	
	private void setPosJogador(Jogador jogador, int pos) {
		tabuleiroPanel.getCasas().get(jogador.getPosicao()).removeJogador(jogador);
		
		tabuleiroPanel.getCasas().get(pos).addJogador(jogador);
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
