package edu.kosta.chatSample.client.handler;

import edu.kosta.chatSample.client.room.ClientChatRoom;
import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.core.message.MessageType;

public class JoinRoomHandler implements MessageHandler {

    private ClientChatRoom chatRoom;
    
    public JoinRoomHandler(ClientChatRoom chatRoom) {
        //
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message message) {
        //
        this.chatRoom.sendMessage(MessageType.ParticipantList);
    }
}
