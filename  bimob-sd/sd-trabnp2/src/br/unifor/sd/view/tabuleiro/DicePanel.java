package br.unifor.sd.view.tabuleiro;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DicePanel extends JPanel {
	
	private JLabel lbDado;
	private static final List<Integer> valores = Arrays.asList(new Integer[]{
			1, 2, 3, 4, 5, 6,
	});
	private boolean rodando;
	
	public DicePanel() {
		super();
		
		lbDado = new JLabel();
		lbDado.setFont(new Font("Arial", Font.BOLD, 140));
		setOpaque(true);
		add(lbDado);
		setVisible(false);
	}
	
	public void rodarDado() {
		rodando = true;
		final Thread thread = new Thread() {
			public void run() {
				Collections.shuffle(valores);
				while (rodando) {
					for (int i = 0; i < valores.size(); i++) {
						if (!rodando) {
							break;
						}
						synchronized (lbDado) {
							lbDado.setText(String.valueOf(valores.get(i)));
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			};
		};
		thread.start();
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rodando = false;
				synchronized (lbDado) {
					System.out.println(lbDado.getText());
				}
			}
		});
	}
}
