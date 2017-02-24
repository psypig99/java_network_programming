package edu.kosta.chatting.chat_thrusday.server;


import edu.kosta.chatting.chat_thrusday.framework.Delegator;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

public class ClientMessageDelegator implements Delegator {

	private TalkMessageHandler handler;

	public ClientMessageDelegator(TalkMessageHandler handler) {
		this.handler = handler;
	}

	@Override
	public void delegate(BlockMessage message) {
		handler.handle(message);
	}

}
