package edu.kosta.network.server;

import edu.kosta.network.framework.ConnectedClient;
import edu.kosta.network.framework.message.BlockMessage;

public class JoinedTalker {

	private ConnectedClient connectedClient;

	public JoinedTalker(ConnectedClient connectedClient) {
		this.connectedClient = connectedClient;
	}

	public void sendMessage(BlockMessage message) {
		this.connectedClient.getDispatcher().dispatch(message);
	}
	
	public ConnectedClient getConnectedClient() {
		return connectedClient;
	}

}
