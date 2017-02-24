package edu.kosta.chatSample.server.handler;

import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.server.room.ServerChatRoom;

public class SimpleMessageHandler implements MessageHandler {

    private ServerChatRoom chatRoom;
    
    public SimpleMessageHandler(ServerChatRoom chatRoom) {
    	//
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message request) {
        //
        chatRoom.sendMessage(request);
    }

}
