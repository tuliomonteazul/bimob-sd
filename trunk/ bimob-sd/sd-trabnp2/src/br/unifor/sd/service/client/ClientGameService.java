package br.unifor.sd.service.client;

import br.unifor.sd.connection.client.ClientConnection;
import br.unifor.sd.connection.factory.ConnectionFactory;
import br.unifor.sd.connection.listener.ClientConnectionListener;
import br.unifor.sd.connection.listener.ConnectionEvent;
import br.unifor.sd.entity.Player;
import br.unifor.sd.service.Method;
import br.unifor.sd.view.tabuleiro.BoardController;

public class ClientGameService {

	private ClientConnection clientConnection;
	
	private BoardController boardController;
	
	private static ClientGameService instance;
	private ClientGameService() {
		super();
		clientConnection = ConnectionFactory.getClientConnection();
		boardController = BoardController.getInstance();
	}
	public static ClientGameService getInstance(){
		if (instance == null) {
			instance = new ClientGameService();
		}
		return instance;
	}
	
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
		Player player;
		switch (method.getIdMethod()) {
			case Method.CONECTOU:
				player = (Player) method.getParams()[0];
				boardController.setPlayer(player);
				ClientPlayerService.player = player;
				break;
			case Method.LIBERAR_VEZ:
				boardController.liberarVez();
				break;
			case Method.EXIBIR_MSG:
				final String msg = (String) method.getParams()[0];
				boardController.exibirMsg(msg);
				break;
			case Method.MOVER:
				player = (Player) method.getParams()[0];
				final int casas = (Integer) method.getParams()[1];
				boardController.mover(player, casas);
				break;
		}
	}
}
