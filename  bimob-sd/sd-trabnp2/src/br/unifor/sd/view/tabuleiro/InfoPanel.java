package br.unifor.sd.view.tabuleiro;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.unifor.sd.entity.Player;

public class InfoPanel extends JPanel {
	private JLabel lbCorText;
	private JLabel lbCorIcon;
	private JLabel lbDinheiroText;
	private JLabel lbDinheiro;
	
	private NumberFormat nf = NumberFormat.getCurrencyInstance();
	
	public InfoPanel(){
		
		setLayout(new FlowLayout());
		
		lbCorText = new JLabel("Sua cor:");
		add(lbCorText);
		lbCorIcon = new JLabel();
		add(lbCorIcon);
		
		lbDinheiroText = new JLabel("Dinheiro: ");
		add(lbDinheiroText);
		lbDinheiro = new JLabel();
		add(lbDinheiro);
		
		setVisible(false);
		setOpaque(false);
		setMaximumSize(new Dimension(600, 100));
	}
	
	public void showInfo(Player player) {
		lbCorIcon.setIcon(new ImageIcon(player.getCor().getImage()));
		lbDinheiro.setText(nf.format(player.getDinheiro()));
		
		setVisible(true);
	}
}
