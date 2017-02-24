package edu.kosta.chatting.chat_thrusday.framework.message.object;

import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;
import edu.kosta.chatting.chat_thrusday.framework.message.MessageWriter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by sejun on 17. 2. 23.
 */
public class ObjectMessageWriter implements MessageWriter {

    private ObjectOutputStream oos;

    public ObjectMessageWriter(OutputStream os) {
        try {
            this.oos = new ObjectOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(BlockMessage message) throws IOException {
        oos.writeObject(message);
        System.out.println("write object : " + message);
    }
}
