package edu.kosta.chatting.chat_thrusday.client;


import edu.kosta.chatting.chat_thrusday.framework.Handler;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

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
