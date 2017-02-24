package edu.kosta.network.framework;

import edu.kosta.network.framework.message.BlockMessage;

public interface Delegator {

	void delegate(BlockMessage message);
}
