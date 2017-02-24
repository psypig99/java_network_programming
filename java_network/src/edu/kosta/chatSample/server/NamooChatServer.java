package edu.kosta.chatSample.server;

import edu.kosta.chatSample.core.SocketReactor;
import edu.kosta.chatSample.core.io.MessageReader;
import edu.kosta.chatSample.core.io.MessageWriter;
import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.core.message.MessageType;
import edu.kosta.chatSample.server.handler.ClientMessageDelegator;
import edu.kosta.chatSample.server.room.Participant;
import edu.kosta.chatSample.server.room.ServerChatRoom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class NamooChatServer {
    
    private ServerChatRoom chatRoom;
    
    public static void main(String[] args) {
        new NamooChatServer().launch();
    }

    private void launch() {
        //
        this.chatRoom = new ServerChatRoom();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1700);
            System.out.println("Server is running.");
            ClientMessageDelegator delegator = new ClientMessageDelegator(chatRoom);
            
            while (true) {
                
                Socket socket = serverSocket.accept();
                
                MessageWriter messageWriter = new MessageWriter(socket.getOutputStream());
                MessageReader messageReader = new MessageReader(socket.getInputStream());
                
                Message message = messageReader.readMessage();
                
                // 클라이언트 접속 후 첫 메시지는 JoinRoom 메시지여야 한다.
                if (message.getMessageType() == MessageType.JoinRoom) {
                    Message acceptMessage = new Message(MessageType.JoinRoom, message.getSender());
                    acceptMessage.setContents("대화방에 접속되었습니다.");
                    
                    Participant participant = new Participant(message.getSender(), messageWriter);
                    this.chatRoom.joinRoom(participant);
                    
                    messageWriter.writeMessage(acceptMessage);
                    
                    SocketReactor reactor = new SocketReactor(message.getSender(), socket, delegator);
                    System.out.println("A client connected.");
                    
                    reactor.startEventWaitLoop();
                    
                } else {
                    
                    Message errorMessage = new Message(MessageType.ErrorMessage, message.getSender());
                    errorMessage.setContents("비 정상적인 요청입니다.");
                    
                    messageWriter.writeMessage(errorMessage);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not start server.");
        } finally {
            //
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    //
                }
            }
        }
    }
}
