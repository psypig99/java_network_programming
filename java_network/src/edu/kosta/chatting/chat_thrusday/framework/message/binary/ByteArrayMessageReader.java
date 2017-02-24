package edu.kosta.chatting.chat_thrusday.framework.message.binary;

import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;
import edu.kosta.chatting.chat_thrusday.framework.message.MessageReader;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sejun on 17. 2. 23.
 */
public class ByteArrayMessageReader implements MessageReader {

    private DataInputStream dis;

    public ByteArrayMessageReader(InputStream is) {
        dis = new DataInputStream(is);
    }

    @Override
    public BlockMessage read() throws IOException {
        int totalLength = dis.readInt();
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
