package edu.kosta.network.server;

import edu.kosta.network.framework.Handler;
import edu.kosta.network.framework.message.BlockMessage;

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
