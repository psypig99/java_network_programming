package edu.kosta.chatSample.client.handler;

import edu.kosta.chatSample.client.room.ClientChatRoom;
import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;

public class ParticipantListHandler implements MessageHandler {

    private ClientChatRoom chatRoom;

    public ParticipantListHandler(ClientChatRoom chatRoom) {
        //
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message message) {
        // 
        String participantList = message.getContents();
        if (participantList != null) {
            String[] participantNames = participantList.split(",");
            
            for (String participantName : participantNames) {
                chatRoom.addParticipant(participantName, false);
            }
        }
    }

}
