package edu.kosta.chatting.chat_thrusday.framework.io;

import java.io.IOException;
import java.net.Socket;

import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;
import edu.kosta.chatting.chat_thrusday.framework.message.MessageReader;
import edu.kosta.chatting.chat_thrusday.framework.message.MessageWriter;
import edu.kosta.chatting.chat_thrusday.framework.message.binary.ByteArrayMessageReader;
import edu.kosta.chatting.chat_thrusday.framework.message.binary.ByteArrayMessageWriter;
import edu.kosta.chatting.chat_thrusday.framework.message.json.JsonMessageReader;
import edu.kosta.chatting.chat_thrusday.framework.message.json.JsonMessageWriter;
import edu.kosta.chatting.chat_thrusday.framework.message.object.ObjectMessageReader;
import edu.kosta.chatting.chat_thrusday.framework.message.object.ObjectMessageWriter;

public class SocketWorker {

	private MessageReader reader;
	private MessageWriter writer;

	private Socket socket;
	
	public SocketWorker(Socket socket) {
		this.socket = socket;
		try {
//			this.reader = new ByteArrayMessageReader(socket.getInputStream());
//			this.writer = new ByteArrayMessageWriter(socket.getOutputStream());
//			this.reader = new JsonMessageReader(socket.getInputStream());
//			this.writer = new JsonMessageWriter(socket.getOutputStream());
			this.reader = new ObjectMessageReader(socket.getInputStream());
			this.writer = new ObjectMessageWriter(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	public BlockMessage read() throws IOException {
//		return this.dis.readUTF();
		return reader.read();
	}
	
	public void write(BlockMessage message) throws IOException {
//		this.dos.writeUTF(message);
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
