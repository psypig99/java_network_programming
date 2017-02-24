package edu.kosta.chatting.chat_wednesday.framework.io;

import edu.kosta.chatting.chat_wednesday.framework.Delegator;
import edu.kosta.chatting.chat_wednesday.framework.DisconnectEventListener;
import edu.kosta.chatting.chat_wednesday.framework.Reactor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SocketReactor implements Reactor, Runnable {

	private SocketWorker socketWorker;
	private Delegator delegator;

	private List<DisconnectEventListener> connectionMonitors;

	public SocketReactor(SocketWorker socketWorker, Delegator delegator) {
		this.socketWorker = socketWorker;
		this.delegator = delegator;
		this.connectionMonitors = new ArrayList<>();
	}

	@Override
	public void receive(String message) {
		delegator.delegate(message);
	}

	@Override
	public void run() {
		while (true) {
			try {
				String message = socketWorker.read();
				receive(message);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Client connection may be closed...");
				this.connectionMonitors.forEach(connectionMonitor -> connectionMonitor.closed(this));
				break;
			}
		}
	}

	public void addSocketMonitor(DisconnectEventListener socketMonitor) {
		this.connectionMonitors.add(socketMonitor);
	}

}
