package edu.kosta.chatting.chat_wednesday.client;


import edu.kosta.chatting.chat_wednesday.client.cli.InputListener;
import edu.kosta.chatting.chat_wednesday.framework.Dispatcher;

public class Talker implements InputListener {

	private Dispatcher dispatcher;

	public Talker(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void sendMessage(String message) {
		dispatcher.dispatch(message);
	}

	public void receiveMessage(String message) {
		System.out.println("Received:" + message);
	}

	@Override
	public void inputMessage(String message) {
		this.sendMessage(message);
	}

}
