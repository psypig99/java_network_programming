package edu.kosta.chatSample.core.message;

import java.io.IOException;
import java.util.Arrays;

public class MessageHeader {
    //
    private MessageType messageType;
    private String sender;
    private String receiver;
    
    public MessageType getMessageType() {
        return messageType;
    }
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	//--------------------------------------------------------------------------
	
	public byte[] toBytes() throws IOException {
        //
		// messageType(1) + sender(50) + receiver(50)
        byte[] bytes = new byte[1 + 50 + 50]; 
        
        byte[] senderBytes = sender.getBytes("UTF-8");

        Arrays.fill(bytes, (byte)0x00);
        bytes[0] = messageType.getTypeCode();
        System.arraycopy(senderBytes, 0, bytes, 1, senderBytes.length);
        
        if (receiver != null) {
        	byte[] receiverBytes = receiver.getBytes("UTF-8");
        	System.arraycopy(receiverBytes, 0, bytes, 51, receiverBytes.length);
        }
        return bytes;
    }
}
