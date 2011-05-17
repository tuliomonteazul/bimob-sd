package br.unifor.sd.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.factory.ConnectionProtocol;
import br.unifor.sd.service.server.ServerGameService;

public class MainServer {
	public static void main(String[] args) {
		
		ConnectionFactory.createServer(ConnectionProtocol.TCP);
		
		final ServerGameService jogoService = ServerGameService.getInstance();
		
		System.out.println("Iniciando servidor...");
		jogoService.createServer();
		
		
		JFrame frame = new JFrame();
		JButton button = new JButton("Iniciar Jogo");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jogoService.startGame()) {
					System.out.println("Jogo iniciado!");
					((JButton) e.getSource()).setEnabled(false);
				} else {
					System.out.println("É preciso pelo menos 2 jogadores para iniciar o jogo.");
				}
			}
		});
		frame.add(button);
		frame.setSize(200, 90);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		final ServerConnection serverConnection = ServerConnectionTCP.getInstance();
//		serverConnection.startServer(new ServerConnectionListener() {
//			
//			@Override
//			public void receiveData(ConnectionEvent event) {
//				
//				Object object = event.getObject();
//				System.out.println(object);
//			}
//			@Override
//			public void requestConnection(ConnectionEvent event) {
//
//				event.acceptConnection();
//
//				System.out.println("Servidor recebe porta do cliente: "+event.getClient().getPort());
//			}
//		});
//		
		
	}
}
