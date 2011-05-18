package br.unifor.sd.view.tabuleiro;

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
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.unifor.sd.entity.Card;
import br.unifor.sd.entity.ColorPlayer;
import br.unifor.sd.entity.Player;

public class BoardPanel extends JPanel {
	
	private static final long serialVersionUID = -2779961265566761801L;
	private static final int[] QT_CASAS = {
		7, // abaixo
		6, // esquerda
		7, // cima
		6, // direita
	};
	private int countCasas = 0;
	
	private SquarePanel pnInicio;
	private SquarePanel pnVisita;
	private SquarePanel pnWikipedia;
	private SquarePanel pnPrisao;
	private JPanel pnBaixo;
	private JPanel pnEsquerda;
	private JPanel pnCima;
	private JPanel pnDireita;
	private JPanel pnDentro;
	private DicePanel pnDado;
	private InfoPanel pnInfo;
	private JPanel pnMsg;
	private JLabel lbMsg;
	private List<SquarePanel> casas = new ArrayList<SquarePanel>();
	
	private GridBagConstraints gbc;
	
	public BoardPanel(){
		
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
		
		addPanelDentro();
		
		pnInicio.addJogador(new Player(ColorPlayer.AZUL));
		pnInicio.addJogador(new Player(ColorPlayer.VERMELHO));
		pnInicio.addJogador(new Player(ColorPlayer.BRANCO));
		pnInicio.addJogador(new Player(ColorPlayer.VERDE));
	}

	private void addCasaInicio() {
		pnInicio = new SquarePanel();
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
		pnVisita = new SquarePanel();
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
		pnWikipedia = new SquarePanel();
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
		pnPrisao = new SquarePanel();
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
	

	private void addPanelDentro() {
		pnDentro = new JPanel();
		pnDentro.setOpaque(true);
//		pnDentro.setBackground(Color.white);
		pnDentro.setLayout(new BoxLayout(pnDentro, BoxLayout.Y_AXIS));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(pnDentro, gbc);
		
		addPanelMsg();
		addPanelDado();
		addPanelInfo();
	}
	

	private void addPanelMsg() {
		lbMsg = new JLabel("Aguardando os outros jogadores...");
		
		pnMsg = new JPanel();
		pnMsg.add(Box.createVerticalStrut(60));
		pnMsg.setOpaque(true);
		pnMsg.add(lbMsg);
		pnDentro.add(pnMsg);
	}
	
	private void addPanelDado() {
		pnDado = new DicePanel();
		pnDentro.add(pnDado);
	}
	
	private void addPanelInfo() {
		pnInfo = new InfoPanel();
		pnInfo.setAlignmentY(BOTTOM_ALIGNMENT);
		pnDentro.add(pnInfo);
	}

	public void exibirDado() {
		lbMsg.setText("Sua vez! Clique para jogar!");
		pnDado.setVisible(true);
		pnDado.rodarDado();
	}
	
	public void addCarta(Card carta) {
		countCasas++;
		CardPanel pnCarta = null;
		if (countCasas <= QT_CASAS[0]) {
			pnCarta = new CardPanel(carta);
			pnBaixo.add(pnCarta);
			pnBaixo.add(Box.createHorizontalStrut(1));
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1]) {
			pnCarta = new CardPanel(carta, 90);
			pnEsquerda.add(pnCarta);
			pnEsquerda.add(Box.createVerticalStrut(1));
			if (countCasas == QT_CASAS[0] + QT_CASAS[1]) {
				// inverte a ordem dos comps do panel
				inverterPanel(pnEsquerda);
			}
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1] + QT_CASAS[2]) {
			pnCarta = new CardPanel(carta, 180);
			pnCima.add(pnCarta);
			pnCima.add(Box.createHorizontalStrut(1));
		} else if (countCasas <= QT_CASAS[0] + QT_CASAS[1] + QT_CASAS[2] + QT_CASAS[3]) {
			pnCarta = new CardPanel(carta, 270);
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

	public List<SquarePanel> getCasas() {
		return casas;
	}

	public JLabel getLbMsg() {
		return lbMsg;
	}
	
	public JPanel getPnMsg() {
		return pnMsg;
	}

	public InfoPanel getPnInfo() {
		return pnInfo;
	}
	
}
