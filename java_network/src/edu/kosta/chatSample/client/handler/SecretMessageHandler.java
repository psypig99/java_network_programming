package edu.kosta.chatSample.client.handler;

import edu.kosta.chatSample.client.room.ClientChatRoom;
import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;

public class SecretMessageHandler implements MessageHandler {

    private ClientChatRoom chatRoom;
    
    public SecretMessageHandler(ClientChatRoom chatRoom) {
        //
        this.chatRoom = chatRoom;
    }

    @Override
    public void handle(Message message) {
        //
        String popupTitle = String.format("%s님이 보낸 메시지", message.getSender());
        String popupMessage = message.getContents();
        
        chatRoom.showMessagePopup(popupTitle, popupMessage);
    }
}
