package edu.kosta.chatting.chat_tuesday.server;

import edu.kosta.chatting.chat_tuesday.framework.Receptionist;

/**
 * Created by sejun on 17. 2. 22.
 */
public class TalkingRoom {

    private Receptionist receptionist;

    public TalkingRoom(Receptionist receptionist){
        this.receptionist = receptionist;
    }

    public void openTalkingRoom(){
        receptionist.startServer();
    }

    public void talkToAll(String message) {
        receptionist.propagate(message);
    }
}