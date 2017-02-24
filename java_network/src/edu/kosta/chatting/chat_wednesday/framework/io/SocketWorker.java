package edu.kosta.chatting.chat_wednesday.framework.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketWorker {

	private DataInputStream dis;
	private DataOutputStream dos;
	
	private Socket socket;
	
	public SocketWorker(Socket socket) {
		this.socket = socket;
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());	
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String read() throws IOException {
		return this.dis.readUTF();
	}
	
	public void write(String message) throws IOException {
		this.dos.writeUTF(message);
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// do nothing...
		}
	}
	
}
