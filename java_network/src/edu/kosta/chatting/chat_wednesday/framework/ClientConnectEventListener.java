package edu.kosta.chatting.chat_wednesday.framework;

public interface ClientConnectEventListener {

	void connect(ConnectedClient connectedClient);
	void disconnect(ConnectedClient connectedClient);

}
