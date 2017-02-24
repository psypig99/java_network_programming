package edu.kosta.network.framework.message.binary;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import edu.kosta.network.framework.message.BlockMessage;
import edu.kosta.network.framework.message.MessageWriter;

public class ByteArrayMessageWriter implements MessageWriter {

	private DataOutputStream dos;

	public ByteArrayMessageWriter(OutputStream os) {
		this.dos = new DataOutputStream(os);
	}

	@Override
	public void write(BlockMessage message) throws IOException {
		String sender = message.getSender();
		String contents = message.getContents();
		
		byte[] senderBytes = sender.getBytes();
		senderBytes = Arrays.copyOf(senderBytes, 50);
		byte[] contentsBytes = contents.getBytes();
		
		int totalLength = 50 + contentsBytes.length;
		
		dos.writeInt(totalLength);
		dos.write(senderBytes);
		dos.write(contentsBytes);
	}

	
	public static void main(String[] args) {
		String sender = "kchuh";
		byte[] senderBytes = sender.getBytes();
		senderBytes = Arrays.copyOf(senderBytes, 50);
		System.out.println(new String(senderBytes));
	}
	
	
	
	
}
