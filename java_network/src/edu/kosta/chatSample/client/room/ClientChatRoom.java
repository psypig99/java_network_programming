package edu.kosta.chatSample.client.room;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedHashSet;
import java.util.Set;

import edu.kosta.chatSample.core.SocketReactor;
import edu.kosta.chatSample.core.handler.HandlerDelegator;
import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.core.message.MessageType;
import edu.kosta.chatSample.client.gui.ChatClientView;
import edu.kosta.chatSample.client.handler.ServerMessageDelegator;
import edu.kosta.chatSample.core.io.MessageWriter;

public class ClientChatRoom {
	
	// 향후 서버 정보는 별도파일로 분리필요함 (예, 프로퍼티 파일 등)
	private static final String SERVER_IP = "localhost";
	private static final int SERVER_PORT = 1700;
    
    private String sender;
    private Set<String> participants;
    private MessageWriter messageWriter;
    private ChatClientView clientView;
    private HandlerDelegator delegator;
    
    public ClientChatRoom(ChatClientView clientView) {
        //
        this.participants = new LinkedHashSet<String>();
        this.clientView = clientView;
        this.delegator = new ServerMessageDelegator(this);
    }
    
    public void joinRoom(String participantName) {
        //
        this.sender = participantName;
        
        Socket socket = null;
        boolean connected = false;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            this.messageWriter = new MessageWriter(socket.getOutputStream());
            
            System.out.println("Client is connected to server.");

            connected = true;
            clientView.bindEventAndShow(this);
            
            SocketReactor reactor = new SocketReactor(sender, socket, delegator);
            reactor.startEventWaitLoop();
            
            // send join room message to server
            messageWriter.writeMessage(new Message(MessageType.JoinRoom, participantName));
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (connected == false) {
            clientView.showMessagePopup("에러메시지", "서버에 접속할 수 없습니다.");
            System.exit(0);
        }
    }
    
    public synchronized void addParticipant(String participant, boolean notifiable) {
        //
        participants.add(participant);
        clientView.addParticipant(participant);
        if (notifiable) {
            clientView.appendMessage(participant + "님이 대화방에 들어왔습니다.\n");
        }
    }
    
    public synchronized void removeParticipant(String participant) {
        //
        participants.remove(participant);
        clientView.removeParticipant(participant);
        clientView.appendMessage(participant + "님이 대화방에서 나갔습니다.\n");
    }
    
    public void sendMessage(final Message message) {
        //
        writeMessage(message);

        if (message.getMessageType() != MessageType.SecretMessage) {
            clientView.appendMessage(message.getContents());
        }
    }

    public void appendMessage(Message message) {
        //
        String displayMsg = String.format("[%s] %s\n", message.getSender(), message.getContents());
        clientView.appendMessage(displayMsg);
    }
    
    public void showMessagePopup(String title, String message) {
        //
        clientView.showMessagePopup(title, message);
    }

    public void sendMessage(MessageType messageType, String contents) {
        // 
        Message message = new Message(messageType, sender);
        message.setContents(contents);
        
        writeMessage(message);
    }

    public void sendMessage(MessageType messageType) {
        //
        Message message = new Message(messageType, sender);
        writeMessage(message);
    }
    
    private synchronized void writeMessage(Message message) {
        // 
        try {
            messageWriter.writeMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            clientView.appendMessage("메시지 전송실패");
        }
    }
}
