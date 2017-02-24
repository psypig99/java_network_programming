package edu.kosta.chatSample.client.handler;

import edu.kosta.chatSample.client.room.ClientChatRoom;
import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;

public class RemoveParticipantHandler implements MessageHandler {

    private ClientChatRoom chatRoom;
    
    public RemoveParticipantHandler(ClientChatRoom chatRoom) {
        //
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message message) {
        // 
        String participant = message.getContents();
        this.chatRoom.removeParticipant(participant);
    }

}
