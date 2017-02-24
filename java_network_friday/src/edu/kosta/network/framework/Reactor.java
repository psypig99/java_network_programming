package edu.kosta.network.framework;

import edu.kosta.network.framework.message.BlockMessage;

public interface Reactor {

	void receive(BlockMessage message);
}
