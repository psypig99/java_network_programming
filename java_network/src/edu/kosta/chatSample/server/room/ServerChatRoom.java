package edu.kosta.chatSample.server.room;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.core.message.MessageType;

public class ServerChatRoom {

	private Map<String, Participant> participants;
	
	public ServerChatRoom() {
		//
		this.participants = new LinkedHashMap<String, Participant>();
	}
	
	public void joinRoom(Participant participant) {
		//
		this.participants.put(participant.getName(), participant);
		
		Message message = new Message(MessageType.AddParticipant, "Server");
		message.setContents(participant.getName());
		
		sendMessage(message);
	}
	
	public void sendMessage(Message message) {
		// 
		// 비밀메시지인 경우 한사람에게만 보낸다.
		if (message.getMessageType() == MessageType.SecretMessage) {
			//
			System.out.println("[ChatRoomServer] Send secret message to " + message.getReceiver());
			Participant participant = participants.get(message.getReceiver());
			participant.sendMessage(message);
			
		} else {
			//
			System.out.println("[ChatRoomServer] Number of participants : " + participants.size());
			for (Entry<String, Participant> entry : participants.entrySet()) {
				//
				Participant participant = entry.getValue();
				participant.sendMessage(message);
			}
		}
		
	}

	public void exitRoom(String participant) {
		//
		this.participants.remove(participant);
		
		Message message = new Message(MessageType.RemoveParticipant, "Server");
		message.setContents(participant);
		
		sendMessage(message);
	}

	public List<String> getParticipantList() {
		//
		List<String> participantList = new ArrayList<String>();
		for (Entry<String, Participant> entry : participants.entrySet()) {
			//
			Participant participant = entry.getValue();
			participantList.add(participant.getName());
		}
		
		return participantList;
	}
}
