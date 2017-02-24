package edu.kosta.chatting.chat_tuesday.server;

import edu.kosta.chatting.chat_tuesday.framework.Handler;

/**
 * Created by sejun on 17. 2. 22.
 */
public class TalkMessageHandler implements Handler {

    private TalkingRoom talkingRoom;

    public TalkMessageHandler(){
    }

    @Override
    public void handle(String message) {
        talkingRoom.talkToAll(message);
    }

    public void setTalkingRoom(TalkingRoom talkingRoom){
        this.talkingRoom = talkingRoom;
    }
}
