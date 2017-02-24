package edu.kosta.chatting.chat_thrusday.framework.io;

import edu.kosta.chatting.chat_thrusday.framework.Delegator;
import edu.kosta.chatting.chat_thrusday.framework.DisconnectEventListener;
import edu.kosta.chatting.chat_thrusday.framework.Reactor;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
