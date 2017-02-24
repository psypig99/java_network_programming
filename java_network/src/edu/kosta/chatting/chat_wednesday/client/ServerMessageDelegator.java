package edu.kosta.chatting.chat_wednesday.client;


import edu.kosta.chatting.chat_wednesday.framework.Delegator;
import edu.kosta.chatting.chat_wednesday.framework.Handler;

public class ServerMessageDelegator implements Delegator {

	private Handler handler;

	public ServerMessageDelegator(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void delegate(String message) {
		handler.handle(message);
	}

}
