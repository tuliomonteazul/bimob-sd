package br.unifor.sd.main;

import br.unifor.sd.service.JogoService;

public class MainServer {
	public static void main(String[] args) {
		JogoService jogoService = JogoService.getInstance();
		
		System.out.println("Iniciando servidor...");
		jogoService.createServer();
		
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
//		JFrame frame = new JFrame();
//		JButton button = new JButton("sendAll");
//		button.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				serverConnection.sendAll("Olá clientes");
//			}
//		});
//		frame.add(button);
//		frame.pack();
//		frame.setVisible(true);
		
	}
}
