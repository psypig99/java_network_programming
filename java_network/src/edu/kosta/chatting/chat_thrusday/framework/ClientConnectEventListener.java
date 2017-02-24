package edu.kosta.chatting.chat_thrusday.framework;

public interface ClientConnectEventListener {

	void connect(ConnectedClient connectedClient);
	void disconnect(ConnectedClient connectedClient);

}
