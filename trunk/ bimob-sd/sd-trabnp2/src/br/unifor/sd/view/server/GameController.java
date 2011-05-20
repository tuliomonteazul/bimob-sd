package br.unifor.sd.view.server;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.unifor.sd.service.server.ServerInputService;

public class GameController extends JFrame {
	
	private static final long serialVersionUID = -237398252752864333L;

	private final ServerInputService serverInputService = ServerInputService.getInstance();
	
	private static GameController instance;
	private GameController() {
		super();
	}
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public void startGame() {
		final JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		JButton button = new JButton("Iniciar Jogo");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (serverInputService.startGame()) {
					System.out.println("Jogo iniciado!");
					((JButton) e.getSource()).setEnabled(false);
					serverInputService.getJogo().setIniciado(true);
				} else {
					JOptionPane.showMessageDialog(null, "É preciso pelo menos 2 jogadores para iniciar o jogo.");
				}
			}
		});
		frame.add(button);
		frame.setSize(200, 90);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
