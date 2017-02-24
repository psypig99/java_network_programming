package edu.kosta.network.client;

import edu.kosta.network.framework.Handler;
import edu.kosta.network.framework.message.BlockMessage;

public class TalkMessageHandler implements Handler {

	private Talker talker;

	public TalkMessageHandler() {
		
	}

	@Override
	public void handle(BlockMessage message) {
		talker.receiveMessage(message);
	}

	public void setTalker(Talker talker) {
		this.talker = talker;
	}
}
