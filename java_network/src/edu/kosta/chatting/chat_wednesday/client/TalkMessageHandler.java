package edu.kosta.chatting.chat_wednesday.client;


import edu.kosta.chatting.chat_wednesday.framework.Handler;

public class TalkMessageHandler implements Handler {

	private Talker talker;

	public TalkMessageHandler() {
		
	}

	@Override
	public void handle(String message) {
		talker.receiveMessage(message);
	}

	public void setTalker(Talker talker) {
		this.talker = talker;
	}
}
