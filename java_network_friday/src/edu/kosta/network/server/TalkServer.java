package edu.kosta.network.server;

import edu.kosta.network.framework.Delegator;
import edu.kosta.network.framework.io.SocketReceptionist;
import edu.kosta.network.framework.message.MessageQueue;
import edu.kosta.network.framework.message.QueueMonitor;

public class TalkServer {

	public static void main(String[] args) {

		TalkMessageHandler handler = new TalkMessageHandler();
		Delegator delegator = new ClientMessageDelegator(handler);

		MessageQueue queue = new MessageQueue();
		SocketReceptionist receptionist = new SocketReceptionist(10000, delegator, queue);
		TalkingRoom talkingRoom = new TalkingRoom(receptionist);
		receptionist.setClientMonitor(talkingRoom);
		handler.setTalkingRoom(talkingRoom);

		QueueMonitor queueMonitor = new QueueMonitor(queue, delegator);
		new Thread(queueMonitor).start();

		talkingRoom.openTalkingRoom();
	}
}
