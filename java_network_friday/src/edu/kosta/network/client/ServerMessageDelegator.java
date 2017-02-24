package edu.kosta.network.client;

import edu.kosta.network.framework.Delegator;
import edu.kosta.network.framework.Handler;
import edu.kosta.network.framework.message.BlockMessage;

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
