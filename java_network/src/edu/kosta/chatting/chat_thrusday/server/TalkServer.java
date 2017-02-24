package edu.kosta.chatting.chat_thrusday.server;


import edu.kosta.chatting.chat_thrusday.framework.Delegator;
import edu.kosta.chatting.chat_thrusday.framework.io.SocketReceptionist;

public class TalkServer {

	public static void main(String[] args) {

		TalkMessageHandler handler = new TalkMessageHandler();
		Delegator delegator = new ClientMessageDelegator(handler);
		SocketReceptionist receptionist = new SocketReceptionist(10000, delegator);
		TalkingRoom talkingRoom = new TalkingRoom(receptionist);
		receptionist.setClientMonitor(talkingRoom);
		handler.setTalkingRoom(talkingRoom);

		talkingRoom.openTalkingRoom();
	}
}
