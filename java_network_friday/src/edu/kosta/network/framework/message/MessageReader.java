package edu.kosta.network.framework.message;

import java.io.IOException;

public interface MessageReader {

	BlockMessage read() throws IOException;
}
