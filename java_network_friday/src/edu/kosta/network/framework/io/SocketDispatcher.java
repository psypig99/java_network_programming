package edu.kosta.network.framework.io;

import java.io.IOException;

import edu.kosta.network.framework.Dispatcher;
import edu.kosta.network.framework.message.BlockMessage;

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
