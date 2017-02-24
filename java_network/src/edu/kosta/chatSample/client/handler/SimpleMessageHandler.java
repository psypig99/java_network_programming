package edu.kosta.chatSample.client.handler;

import edu.kosta.chatSample.client.room.ClientChatRoom;
import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;

public class SimpleMessageHandler implements MessageHandler {

    private ClientChatRoom chatRoom;

    public SimpleMessageHandler(ClientChatRoom chatRoom) {
        // 
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message message) {
        //
        this.chatRoom.appendMessage(message);
    }
}
