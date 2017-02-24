package edu.kosta.network.framework.message.binary;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.kosta.network.framework.message.BlockMessage;
import edu.kosta.network.framework.message.MessageReader;

public class ByteArrayMessageReader implements MessageReader {

	private DataInputStream dis;
	
	public ByteArrayMessageReader(InputStream is) {
		dis = new DataInputStream(is);
	}
	
	@Override
	public BlockMessage read() throws IOException {
		
		int totalLength = dis.readInt();
		
		System.out.println(totalLength);
		
		byte[] messageBytes = new byte[totalLength];
		
		dis.read(messageBytes);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(messageBytes);
		byte[] senderBytes = new byte[50];
		byte[] contentsBytes = new byte[totalLength - 50];
		
		bis.read(senderBytes);
		bis.read(contentsBytes);
		
		String sender = new String(senderBytes);
		String contents = new String(contentsBytes);
		
		return new BlockMessage(sender, contents);
	}
	

}
