package edu.kosta.chatting.chat_thrusday.client;


import edu.kosta.chatting.chat_thrusday.framework.Delegator;
import edu.kosta.chatting.chat_thrusday.framework.Handler;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

public class ServerMessageDelegator implements Delegator {

	private Handler handler;

	public ServerMessageDelegator(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void delegate(BlockMessage message) {
		handler.handle(message);
	}

}
