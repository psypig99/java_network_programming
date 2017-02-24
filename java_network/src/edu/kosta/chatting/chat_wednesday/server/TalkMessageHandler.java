package edu.kosta.chatting.chat_wednesday.server;


import edu.kosta.chatting.chat_wednesday.framework.Handler;

public class TalkMessageHandler implements Handler {

	private TalkingRoom talkingRoom;

	public TalkMessageHandler() {

	}

	@Override
	public void handle(String message) {
		talkingRoom.talkToAll(message);
	}

	public void setTalkingRoom(TalkingRoom talkingRoom) {
		this.talkingRoom = talkingRoom;
	}

}
