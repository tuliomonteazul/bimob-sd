package br.unifor.sd.view.client.tabuleiro;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.unifor.sd.service.client.ClientOutputService;

public class DicePanel extends JPanel {
	
	private ClientOutputService clientOutputService = ClientOutputService.getInstance();
	
	private JLabel lbDado;
	private static final List<Integer> valores = Arrays.asList(new Integer[]{
			1, 2, 29, 25
//			1, 2, 3, 4, 5, 6,
//			30,
	});
	private boolean rodando;
	private boolean andando;
	
	public DicePanel() {
		super();
		
		lbDado = new JLabel();
		lbDado.setFont(new Font("Arial", Font.BOLD, 140));
		setOpaque(true);
		add(lbDado);
		setVisible(false);
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (!andando) {
					SwingUtilities.windowForComponent(e.getComponent()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//					e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (rodando) {
					rodando = false;
					synchronized (lbDado) {
						clientOutputService.walk(Integer.parseInt(lbDado.getText()));
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (!andando) {
					SwingUtilities.windowForComponent(e.getComponent()).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//					e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
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
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			};
		};
		thread.start();
	}

	public boolean isRodando() {
		return rodando;
	}

	public void setRodando(boolean rodando) {
		this.rodando = rodando;
	}

	public JLabel getLbDado() {
		return lbDado;
	}

	public void setLbDado(JLabel lbDado) {
		this.lbDado = lbDado;
	}

	public boolean isAndando() {
		return andando;
	}

	public void setAndando(boolean andando) {
		this.andando = andando;
	}
	
}
