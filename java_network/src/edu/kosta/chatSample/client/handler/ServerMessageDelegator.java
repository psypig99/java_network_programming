package edu.kosta.chatSample.client.handler;

import edu.kosta.chatSample.client.room.ClientChatRoom;
import edu.kosta.chatSample.core.handler.HandlerDelegator;
import edu.kosta.chatSample.core.handler.MessageHandler;
import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.core.message.MessageType;

public class ServerMessageDelegator implements HandlerDelegator {
    
    private ClientChatRoom chatRoom;
    
    public ServerMessageDelegator(ClientChatRoom chatRoom) {
        //
        this.chatRoom = chatRoom;
    }
    
    @Override
    public void delegate(Message request) {
        //
        MessageType messageType = request.getMessageType();
        
        MessageHandler handler = null;
        
        switch (messageType) {
        
        case JoinRoom:
            handler = new JoinRoomHandler(chatRoom);
            break;
        case SimpleMessage:
            handler = new SimpleMessageHandler(chatRoom);
            break;
        case SecretMessage:
            handler = new SecretMessageHandler(chatRoom);
            break;
        case AddParticipant:
            handler = new AddParticipantHandler(chatRoom);
            break;
        case RemoveParticipant:
            handler = new RemoveParticipantHandler(chatRoom);
            break;
        case ParticipantList:
            handler = new ParticipantListHandler(chatRoom);
        default:
            // Nothing to do.
            break;
        }
        
        if (handler != null) {
            
            System.out.printf("[%s] execute.\n", handler.getClass().getSimpleName());
            handler.handle(request);
        }
    }

}
