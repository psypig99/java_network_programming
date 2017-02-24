package edu.kosta.chatting.chat_thrusday.framework.io;

import edu.kosta.chatting.chat_thrusday.framework.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class SocketReceptionist implements Receptionist, DisconnectEventListener {

	private int port;
	private Delegator delegator;

	private Map<Reactor, ConnectedClient> connectedClientMap;
	private ClientConnectEventListener clientConnectionListener;

	public SocketReceptionist(int port, Delegator delegator) {
		this.port = port;
		this.delegator = delegator;
		this.connectedClientMap = new HashMap<>();
	}

	@Override
	public void startServer() {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server socket is opened..." + serverSocket);

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client is connected..." + socket);
				SocketWorker socketWorker = new SocketWorker(socket);

				Dispatcher dispatcher = new SocketDispatcher(socketWorker);
				SocketReactor reactor = new SocketReactor(socketWorker, delegator);
				ConnectedClient connectedClient = new ConnectedClient(dispatcher, reactor);
				connectedClientMap.put(reactor, connectedClient);
				clientConnectionListener.connect(connectedClient);
				reactor.addSocketMonitor(this);

				new Thread(reactor).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fail to open server socket...");
		}
	}

	@Override
	public void closed(Reactor reactor) {
		ConnectedClient connectedClient = connectedClientMap.get(reactor);
		clientConnectionListener.disconnect(connectedClient);
	}

	public void setClientMonitor(ClientConnectEventListener clientMonitor) {
		this.clientConnectionListener = clientMonitor;
	}
}
