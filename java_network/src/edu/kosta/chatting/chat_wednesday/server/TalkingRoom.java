package edu.kosta.chatting.chat_wednesday.server;

import edu.kosta.chatting.chat_wednesday.framework.ClientConnectEventListener;
import edu.kosta.chatting.chat_wednesday.framework.ConnectedClient;
import edu.kosta.chatting.chat_wednesday.framework.Receptionist;

import java.util.ArrayList;
import java.util.List;


public class TalkingRoom implements ClientConnectEventListener {

	private Receptionist receptionist;
	private List<JoinedTalker> joinedTalkers;

	public TalkingRoom(Receptionist receptionist) {
		this.receptionist = receptionist;
		this.joinedTalkers = new ArrayList<>();
	}

	public void openTalkingRoom() {
		receptionist.startServer();
		System.out.println("Talking Room is opened...");
	}

	public void joinNewTalker(JoinedTalker joinedTalker) {
		this.joinedTalkers.add(joinedTalker);
		System.out.println("New talker joined..." + joinedTalker);
	}

	public void exitTalker(JoinedTalker joinedTalker) {
		this.joinedTalkers.remove(joinedTalker);
		System.out.println("Talker exit the room..." + joinedTalker);
	}

	public void talkToAll(String message) {
		System.out.println("Talk to all joined talker..." + message);
		joinedTalkers.forEach(joinedTalker -> joinedTalker.sendMessage(message));
	}

	public JoinedTalker findTalkerByConnectedClient(ConnectedClient connectedClient) {
		return this.joinedTalkers.stream()
				.filter(joinedTalker -> joinedTalker.getConnectedClient().equals(connectedClient)).findFirst()
				.orElse(null);
	}

	@Override
	public void connect(ConnectedClient connectedClient) {
		JoinedTalker joinedTalker = new JoinedTalker(connectedClient);
		this.joinNewTalker(joinedTalker);
	}

	@Override
	public void disconnect(ConnectedClient connectedClient) {
		JoinedTalker joinedTalker = findTalkerByConnectedClient(connectedClient);
		this.exitTalker(joinedTalker);
	}

}
