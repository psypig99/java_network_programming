package edu.kosta.chatSample.server.room;


import edu.kosta.chatSample.core.io.MessageWriter;
import edu.kosta.chatSample.core.message.Message;

import java.io.IOException;

public class Participant {
	
	private String name;
	private MessageWriter messageWriter;

	public Participant(String name, MessageWriter messageWriter) {
		this.name = name;
		this.messageWriter = messageWriter;
	}
	
	public String getName() {
		return this.name;
	}

	public void sendMessage(Message message) {
		//
		try {
			messageWriter.writeMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
