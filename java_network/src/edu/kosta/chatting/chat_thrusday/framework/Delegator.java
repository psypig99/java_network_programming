package edu.kosta.chatting.chat_thrusday.framework;


import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

public interface Delegator {

	void delegate(BlockMessage message);
}
