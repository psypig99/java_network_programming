package edu.kosta.network.server;

import edu.kosta.network.framework.Delegator;
import edu.kosta.network.framework.message.BlockMessage;

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
