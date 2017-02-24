package edu.kosta.network.framework.io;

import java.io.IOException;
import java.net.Socket;

import edu.kosta.network.framework.message.BlockMessage;
import edu.kosta.network.framework.message.MessageReader;
import edu.kosta.network.framework.message.MessageWriter;
import edu.kosta.network.framework.message.object.ObjectMessageReader;
import edu.kosta.network.framework.message.object.ObjectMessageWriter;

public class SocketWorker {

	private MessageReader reader;
	private MessageWriter writer;

	private Socket socket;

	public SocketWorker(Socket socket) {
		this.socket = socket;
		try {
			this.writer = new ObjectMessageWriter(socket.getOutputStream());
			this.reader = new ObjectMessageReader(socket.getInputStream());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// }
		// try {
		// this.dis = new DataInputStream(socket.getInputStream());
		// this.dos = new DataOutputStream(socket.getOutputStream());
		// } catch (IOException e) {
		// throw new RuntimeException(e);
		// }
	}

	public BlockMessage read() throws IOException {
		// return this.dis.readUTF();
		return reader.read();
	}

	public void write(BlockMessage message) throws IOException {
		// this.dos.writeUTF(message);
		writer.write(message);
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// do nothing...
		}
	}

}
