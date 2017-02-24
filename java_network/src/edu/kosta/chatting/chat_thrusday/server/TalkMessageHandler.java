package edu.kosta.chatting.chat_thrusday.server;


import edu.kosta.chatting.chat_thrusday.framework.Handler;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

public class TalkMessageHandler implements Handler {

	private TalkingRoom talkingRoom;

	public TalkMessageHandler() {

	}

	@Override
	public void handle(BlockMessage message) {
		talkingRoom.talkToAll(message);
	}

	public void setTalkingRoom(TalkingRoom talkingRoom) {
		this.talkingRoom = talkingRoom;
	}

}
