package edu.kosta.network.framework.message;

import java.io.IOException;

public interface MessageWriter {

	void write(BlockMessage message) throws IOException;
}
