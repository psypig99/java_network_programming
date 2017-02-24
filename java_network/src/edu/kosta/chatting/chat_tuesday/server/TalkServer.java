package edu.kosta.chatting.chat_tuesday.server;

import edu.kosta.chatting.chat_tuesday.framework.Delegator;
import edu.kosta.chatting.chat_tuesday.framework.io.SocketReceptionist;

/**
 * Created by sejun on 17. 2. 22.
 */
public class TalkServer {

    public static void main(String[] args){
        TalkMessageHandler handler = new TalkMessageHandler();
        Delegator delegator = new ClientMessageDelegator(handler);
        SocketReceptionist socketReceptionist = new SocketReceptionist(10000, delegator);
        TalkingRoom talkingRoom = new TalkingRoom(socketReceptionist);
        handler.setTalkingRoom(talkingRoom);
        talkingRoom.openTalkingRoom();
    }
}
