package edu.kosta.chatting.chat_wednesday.framework.io;

import edu.kosta.chatting.chat_wednesday.framework.Dispatcher;

import java.io.IOException;


public class SocketDispatcher implements Dispatcher {

	private SocketWorker socketWorker;
	
	public SocketDispatcher(SocketWorker socketWorker) {
		this.socketWorker = socketWorker;
	}
	
	@Override
	public void dispatch(String message) {
		try {
			this.socketWorker.write(message);			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
