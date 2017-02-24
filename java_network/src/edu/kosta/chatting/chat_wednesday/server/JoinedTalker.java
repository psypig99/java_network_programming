package edu.kosta.chatting.chat_wednesday.server;


import edu.kosta.chatting.chat_wednesday.framework.ConnectedClient;

public class JoinedTalker {

	private ConnectedClient connectedClient;

	public JoinedTalker(ConnectedClient connectedClient) {
		this.connectedClient = connectedClient;
	}

	public void sendMessage(String message) {
		this.connectedClient.getDispatcher().dispatch(message);
	}
	
	public ConnectedClient getConnectedClient() {
		return connectedClient;
	}

}
