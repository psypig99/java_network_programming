package edu.kosta.chatSample.client;

import edu.kosta.chatSample.client.gui.ChatClientDialog;
import edu.kosta.chatSample.client.room.ClientChatRoom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class NamooChatClient extends JFrame {

    private static final long serialVersionUID = -4584567523522404286L;

    public static void main(String[] args) {
        //
        while (true) {
            //
            String nickname = (String) JOptionPane.showInputDialog(
                    new JFrame(),
                    "대화명을 입력하세요.",
                    "NamooChat", JOptionPane.INFORMATION_MESSAGE);
            
            // Cancel을 선택한 경우 프로그램을 끝낸다.
            if (nickname == null) {
                break;
            }
            
            // 값을 입력한 경우 채팅창을 띄운다. (입력하지 않고 확인을 누른경우 계속 입력창을 띄운다.)
            if (nickname != null && nickname.length() > 0) {
                
                ChatClientDialog dialog = new ChatClientDialog(nickname);
                
                ClientChatRoom chatRoom = new ClientChatRoom(dialog);
                chatRoom.joinRoom(nickname);
                
                break;
            }
        }
    }
}
