package edu.kosta.network.client;

import edu.kosta.network.client.cli.InputListener;
import edu.kosta.network.framework.Dispatcher;
import edu.kosta.network.framework.message.BlockMessage;

public class Talker implements InputListener {

	private String name;
	private Dispatcher dispatcher;

	public Talker(String name, Dispatcher dispatcher) {
		this.name = name;
		this.dispatcher = dispatcher;
	}

	public void sendMessage(String message) {
		BlockMessage blockMessage = new BlockMessage();
		blockMessage.setSender(name);
		blockMessage.setContents(message);
		dispatcher.dispatch(blockMessage);
	}

	public void receiveMessage(BlockMessage message) {
		System.out.println(String.format("[%s]%s", message.getSender(), message.getContents()));
	}

	@Override
	public void inputMessage(String message) {
		this.sendMessage(message);
	}

}
