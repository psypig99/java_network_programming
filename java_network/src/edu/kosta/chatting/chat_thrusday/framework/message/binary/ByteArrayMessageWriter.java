package edu.kosta.chatting.chat_thrusday.framework.message.binary;

import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;
import edu.kosta.chatting.chat_thrusday.framework.message.MessageWriter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created by sejun on 17. 2. 23.
 */
public class ByteArrayMessageWriter implements MessageWriter {

    private DataOutputStream dos;

    public ByteArrayMessageWriter(OutputStream os) {
        dos = new DataOutputStream(os);
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
}
