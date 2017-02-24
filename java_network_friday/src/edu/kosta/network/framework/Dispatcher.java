package edu.kosta.network.framework;

import edu.kosta.network.framework.message.BlockMessage;

public interface Dispatcher {

	void dispatch(BlockMessage message);
}
