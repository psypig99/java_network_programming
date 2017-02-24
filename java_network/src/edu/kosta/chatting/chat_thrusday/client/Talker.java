package edu.kosta.chatting.chat_thrusday.client;


import edu.kosta.chatting.chat_thrusday.client.cli.InputListener;
import edu.kosta.chatting.chat_thrusday.framework.Dispatcher;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

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
