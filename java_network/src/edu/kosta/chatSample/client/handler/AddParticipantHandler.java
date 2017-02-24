package edu.kosta.chatSample.client.handler;

import edu.kosta.chatSample.client.room.ClientChatRoom;
import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;

public class AddParticipantHandler implements MessageHandler {

    private ClientChatRoom chatRoom;
    
    public AddParticipantHandler(ClientChatRoom chatRoom) {
        //
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message request) {
        //
        this.chatRoom.addParticipant(request.getContents(), true);
    }

}
