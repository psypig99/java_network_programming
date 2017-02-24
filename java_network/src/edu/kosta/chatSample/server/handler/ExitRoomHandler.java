package edu.kosta.chatSample.server.handler;

import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.server.room.ServerChatRoom;

public class ExitRoomHandler implements MessageHandler {

    private ServerChatRoom chatRoom;
    
    public ExitRoomHandler(ServerChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message request) {
        //
        this.chatRoom.exitRoom(request.getSender());
    }
}
