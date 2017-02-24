package edu.kosta.network.framework.message.object;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import edu.kosta.network.framework.message.BlockMessage;
import edu.kosta.network.framework.message.MessageWriter;

public class ObjectMessageWriter implements MessageWriter {

	private ObjectOutputStream oos;

	public ObjectMessageWriter(OutputStream os) {
		try {
			this.oos = new ObjectOutputStream(os);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(BlockMessage message) throws IOException {
		System.out.println("write object:" + message.toString());
		oos.writeObject(message);
	}

}
