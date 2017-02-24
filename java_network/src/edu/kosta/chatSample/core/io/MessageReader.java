package edu.kosta.chatSample.core.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.kosta.chatSample.core.message.Message;

public class MessageReader {

    private InputStream inputStream;
    
    public MessageReader(InputStream inputStream) {
        //
        this.inputStream = inputStream;
    }
    
    public Message readMessage() throws IOException {
        //
        DataInputStream dis = new DataInputStream(inputStream);
        int totalBytes = dis.readInt();
        byte[] fullBytes = new byte[totalBytes];
        dis.read(fullBytes, 0, totalBytes);
        
        return Message.createInstance(fullBytes);
    }
}
