package edu.kosta.network.framework;

public interface ClientConnectEventListener {

	void connect(ConnectedClient connectedClient);
	void disconnect(ConnectedClient connectedClient);

}
