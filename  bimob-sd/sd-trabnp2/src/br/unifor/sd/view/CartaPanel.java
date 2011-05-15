package br.unifor.sd.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.unifor.sd.entity.Carta;
import br.unifor.sd.view.util.CoresCarta;

public class CartaPanel extends JPanel {
	private Carta carta;
	private JPanel pnCor;
	private JLabel lbNome;
	private JLabel lbValor;
	private GridBagConstraints gbc;
	private double rotateAngle;
	private static final Font FONT_NOME = new Font("Tahoma", Font.BOLD, 11);
	private static final Font FONT_VALOR = new Font("Verdana", Font.PLAIN, 10);
	
	public CartaPanel(Carta carta) {
		this(carta, 0);
	}
	public CartaPanel(Carta carta, double rotateAngle) {
		this.carta = carta;
		this.rotateAngle = rotateAngle;
		initComponents();
		setBackground(Color.WHITE);
	}

	private void initComponents() {
		setLayout(new GridBagLayout());
//		setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		
		pnCor = new JPanel();
		pnCor.setBackground(CoresCarta.getColor(carta.getGrupoID()));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.1;
		gbc.ipadx = 60;
		gbc.fill = GridBagConstraints.BOTH;
		add(pnCor, gbc);
		
		lbNome = new JLabel();
		lbNome.setText(carta.getNome());
		lbNome.setFont(FONT_NOME);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0.8;
		gbc.ipady = 15;
		gbc.insets = new Insets(5, 0, 0, 0);
		gbc.anchor = GridBagConstraints.NORTH;
		add(lbNome, gbc);
		
		lbValor = new JLabel();
		lbValor.setText("$ "+carta.getValor());
		lbValor.setFont(FONT_VALOR);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lbValor, gbc);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		Graphics2D g2d = (Graphics2D) graphics;
	    int x = this.getWidth()/2;
	    int y = this.getHeight()/2;
//	    g2d.rotate(Math.toRadians(rotateAngle), x, y);
	    super.paintComponent(g2d);
		
	}
}
