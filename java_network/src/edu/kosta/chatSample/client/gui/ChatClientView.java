package edu.kosta.chatSample.client.gui;


import edu.kosta.chatSample.client.room.ClientChatRoom;

public interface ChatClientView {

    void bindEventAndShow(ClientChatRoom chatRoom);
    void refreshParticipants(String[] participants);
    void addParticipant(String name);
    void removeParticipant(String name);
    void appendMessage(String contents);
    void showMessagePopup(String title, String message);
}
