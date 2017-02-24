package edu.kosta.chatting.chat_thrusday.framework.message;

import java.io.Serializable;

public class BlockMessage implements Serializable {

	private String sender;
	private String contents;

	public BlockMessage() {

	}

	public BlockMessage(String sender, String contents) {
		super();
		this.sender = sender;
		this.contents = contents;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
