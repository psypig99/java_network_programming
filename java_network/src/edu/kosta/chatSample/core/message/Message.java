package edu.kosta.chatSample.core.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class Message {
    //
    private MessageHeader header;
    private MessageBody body;
    
    public Message(MessageType messageType, String sender) {
        //
        this.header = new MessageHeader();
        this.header.setMessageType(messageType);
        this.header.setSender(sender);
        
        this.body = new MessageBody();
    }
    
    public static Message createInstance(byte[] messageBytes) {
        //
        try {
            //
            ByteInputStream bis = new ByteInputStream(messageBytes, messageBytes.length);
            DataInputStream dis = new DataInputStream(bis);
            
            byte messageType = dis.readByte();
            
            byte[] senderBytes = new byte[50];
            dis.read(senderBytes, 0, 50);
            String sender = new String(senderBytes).trim();
            
            byte[] receiverBytes = new byte[50];
            dis.read(receiverBytes, 0, 50);
            String receiver = new String(receiverBytes).trim();
            
            int bodyLength = dis.readInt();
            byte[] bodyBytes = new byte[bodyLength];
            dis.read(bodyBytes);
            
            String bodyContents = new String(bodyBytes).trim();
            
            Message message = new Message(MessageType.findBy(messageType), sender);
            message.setReceiver(receiver);
            message.setContents(bodyContents);
            
            dis.close();
            
            return message;
            
        } catch(IOException e) {
            throw new RuntimeException("Unknown request message.");
        }
    }
    
    public void setReceiver(String receiver) {
		// 
    	this.header.setReceiver(receiver);
	}
	public String getReceiver() {
		//
		return this.header.getReceiver();
	}    

	public void setContents(String contents) {
        // 
        this.body.setContents(contents);
        
    }
	
    public byte[] toBytes() throws IOException {
        //
        byte[] headerBytes = header.toBytes();
        byte[] bodyBytes = body.toBytes();
        
        int headerLength = headerBytes.length;
        int bodyLength = bodyBytes.length;

        int messageLength = headerLength + bodyLength;
        
        ByteOutputStream bos = new ByteOutputStream(4 + messageLength);
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(messageLength);
        dos.write(headerBytes);
        dos.write(bodyBytes);
        
        byte[] fullBytes = bos.getBytes();
        dos.close();
        
        return fullBytes;
    }

	public String getSender() {
		// 
		return this.header.getSender();
	}

	public MessageType getMessageType() {
		//
		return this.header.getMessageType();
	}

    public String getContents() {
        //
        return this.body.getContents();
    }

	public void setMessageType(MessageType messageType) {
		// 
		this.header.setMessageType(messageType);
	}

	public void setSender(String sender) {
		//
		this.header.setSender(sender);
	}
}
