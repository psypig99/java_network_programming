package edu.kosta.chatting.chat_thrusday.framework.message.json;

import com.google.gson.Gson;
import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;
import edu.kosta.chatting.chat_thrusday.framework.message.MessageReader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sejun on 17. 2. 23.
 */
public class JsonMessageReader implements MessageReader {

    private DataInputStream dis;
    private Gson gson;

    public JsonMessageReader(InputStream is) {
        this.dis = new DataInputStream(is);
        this.gson = new Gson();
    }

    @Override
    public BlockMessage read() throws IOException {
        String jsonMessage = dis.readUTF();
        System.out.println("read json : " + jsonMessage);
        BlockMessage blockMessage = gson.fromJson(jsonMessage, BlockMessage.class);
        return blockMessage;
    }
}
