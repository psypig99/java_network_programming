package edu.kosta.network.framework;

import edu.kosta.network.framework.message.BlockMessage;

public interface Handler {

	void handle(BlockMessage message);
}
