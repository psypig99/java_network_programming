package edu.kosta.chatting.chat_thrusday.server;


import edu.kosta.chatting.chat_thrusday.framework.ConnectedClient;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

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
