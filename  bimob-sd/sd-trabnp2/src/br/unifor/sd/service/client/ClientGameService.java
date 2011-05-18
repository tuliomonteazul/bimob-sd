package br.unifor.sd.service.client;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.listener.ClientConnectionListener;
import br.unifor.sd.connection.listener.ConnectionEvent;
import br.unifor.sd.service.Method;
import br.unifor.sd.view.tabuleiro.BoardController;

public class ClientGameService {

	private ClientConnection clientConnection = ConnectionFactory.getClientConnection();
	
	private BoardController boardController = new BoardController();
	
	public void playGame() {
		boardController.init();
		
		clientConnection.connect(new ClientConnectionListener() {
			
			@Override
			public void receive(ConnectionEvent event) {
				if (event.getObject() instanceof Method) {
					doMethod((Method) event.getObject());
				}
			}
		});
	}
	

	private void doMethod(Method method) {
		if (method.getIdMethod() == Method.LIBERAR_VEZ) {
			boardController.liberarVez();
		} else if (method.getIdMethod() == Method.EXIBIR_MSG) {
			final String msg = (String) method.getParams()[0];
			boardController.exibirMsg(msg);
		}
	}
}
