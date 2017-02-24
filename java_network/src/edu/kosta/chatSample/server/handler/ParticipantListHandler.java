package edu.kosta.chatSample.server.handler;

import java.util.List;

import edu.kosta.chatSample.server.room.ServerChatRoom;
import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.core.message.MessageType;

public class ParticipantListHandler implements MessageHandler {

    private ServerChatRoom chatRoom;
    
    public ParticipantListHandler(ServerChatRoom chatRoom) {
        //
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message message) {
        //
        Message response = new Message(MessageType.ParticipantList, "Server");
        List<String> participantList = chatRoom.getParticipantList();
        
        int length = participantList.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(participantList.get(i));
            if (i < length - 1) {
                sb.append(",");
            }
        }
        
        response.setContents(sb.toString());
        chatRoom.sendMessage(response);
    }
}
