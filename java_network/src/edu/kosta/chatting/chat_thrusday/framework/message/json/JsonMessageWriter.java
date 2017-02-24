package edu.kosta.chatting.chat_thrusday.framework.message.json;

import com.google.gson.Gson;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;
import edu.kosta.chatting.chat_thrusday.framework.message.MessageWriter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sejun on 17. 2. 23.
 */
public class JsonMessageWriter implements MessageWriter {

    private DataOutputStream dos;
    private Gson gson;

    public JsonMessageWriter(OutputStream os) {
        this.dos = new DataOutputStream(os);
        this.gson = new Gson();
    }

    @Override
    public void write(BlockMessage message) throws IOException {
        String jsonMessage = gson.toJson(message);
        System.out.println("write json : " + jsonMessage);
        dos.writeUTF(jsonMessage);
    }
}
