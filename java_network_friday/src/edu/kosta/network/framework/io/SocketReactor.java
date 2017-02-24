package edu.kosta.network.framework.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kosta.network.framework.Delegator;
import edu.kosta.network.framework.Reactor;
import edu.kosta.network.framework.message.BlockMessage;
import edu.kosta.network.framework.DisconnectEventListener;

public class SocketReactor implements Reactor, Runnable {

	private SocketWorker socketWorker;
	private Delegator delegator;

	private List<DisconnectEventListener> disconnectEventListeners;

	public SocketReactor(SocketWorker socketWorker, Delegator delegator) {
		this.socketWorker = socketWorker;
		this.delegator = delegator;
		this.disconnectEventListeners = new ArrayList<>();
	}

	@Override
	public void receive(BlockMessage message) {
		delegator.delegate(message);
	}

	@Override
	public void run() {
		while (true) {
			try {
				BlockMessage message = socketWorker.read();
				receive(message);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Client connection may be closed...");
				this.disconnectEventListeners.forEach(disconnectEventListener -> {
					disconnectEventListener.closed(this);
				});
				break;
			}
		}
	}

	public void addSocketMonitor(DisconnectEventListener socketMonitor) {
		this.disconnectEventListeners.add(socketMonitor);
	}

}
