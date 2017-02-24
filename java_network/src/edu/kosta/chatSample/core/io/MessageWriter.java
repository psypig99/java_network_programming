package edu.kosta.chatSample.core.io;


import edu.kosta.chatSample.core.message.Message;

import java.io.IOException;
import java.io.OutputStream;

public class MessageWriter {

    private OutputStream outputStream;
    
    public MessageWriter(OutputStream outputStream) {
        //
        this.outputStream = outputStream;
    }
    
    public void writeMessage(Message message) throws IOException {
        //
        byte[] fullBytes = message.toBytes();
        
        outputStream.write(fullBytes);
        outputStream.flush();
    }
}
