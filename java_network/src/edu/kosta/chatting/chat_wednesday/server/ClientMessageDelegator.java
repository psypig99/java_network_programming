package edu.kosta.chatting.chat_wednesday.server;


import edu.kosta.chatting.chat_wednesday.framework.Delegator;

public class ClientMessageDelegator implements Delegator {

	private TalkMessageHandler handler;

	public ClientMessageDelegator(TalkMessageHandler handler) {
		this.handler = handler;
	}

	@Override
	public void delegate(String message) {
		handler.handle(message);
	}

}
