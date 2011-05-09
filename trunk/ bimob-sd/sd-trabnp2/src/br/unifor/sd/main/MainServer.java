package br.unifor.sd.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.unifor.sd.connection.server.ServerConnection;
import br.unifor.sd.connection.server.impl.ServerConnectionTCP;
import br.unifor.sd.connection.server.listener.ConnectionEvent;
import br.unifor.sd.connection.server.listener.ServerConnectionListener;

public class MainServer {
	public static void main(String[] args) {
		final ServerConnection serverConnection = ServerConnectionTCP.getInstance();
		serverConnection.startServer(new ServerConnectionListener() {
			
			@Override
			public void receiveMessage(ConnectionEvent event) {
				
				Object object = event.getObject();
				System.out.println(object);
			}
			@Override
			public void requestConnection(ConnectionEvent event) {

				event.acceptConnection();

				System.out.println("Servidor recebe porta do cliente: "+event.getClient().getPort());
			}
		});
		
		JFrame frame = new JFrame();
		JButton button = new JButton("sendAll");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				serverConnection.sendAll("Ol� clientes");
			}
		});
		frame.add(button);
		frame.pack();
		frame.setVisible(true);
		
	}
}
