package edu.kosta.chatting.chat_thrusday.framework.io;

import edu.kosta.chatting.chat_thrusday.framework.Dispatcher;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;

import java.io.IOException;


public class SocketDispatcher implements Dispatcher {

	private SocketWorker socketWorker;
	
	public SocketDispatcher(SocketWorker socketWorker) {
		this.socketWorker = socketWorker;
	}
	
	@Override
	public void dispatch(BlockMessage message) {
		try {
			this.socketWorker.write(message);			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
