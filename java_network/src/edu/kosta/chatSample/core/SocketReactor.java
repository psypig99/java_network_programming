package edu.kosta.chatSample.core;

import java.io.IOException;
import java.net.Socket;

import edu.kosta.chatSample.core.handler.HandlerDelegator;
import edu.kosta.chatSample.core.io.MessageReader;
import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.core.message.MessageType;

public class SocketReactor extends Thread {

    private String sender;
    private Socket socket;
    private HandlerDelegator delegator;
    
    public SocketReactor(String sender, Socket socket, HandlerDelegator delegator) {
        //
        this.sender = sender;
        this.socket = socket;
        this.delegator = delegator;
    }

    public void startEventWaitLoop() {
        //
    	this.start();
    }

    @Override
    public void run() {
        //
        MessageReader messageReader = null;
        try {
            messageReader = new MessageReader(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("소켓을 읽는 중 오류가 발생하였습니다.");
        }
        
		while (true) {
		    Message requestMessage = null;
		    try {
                // retrieve request message
		        requestMessage = messageReader.readMessage();
                
                // delegate request message
                delegator.delegate(requestMessage);
                
    		} catch (IOException e) {
    		    System.out.println("Socket may be closed.");

		        // 강제로 대화방 나가기 메시지를 발생시킴.
		        Message exitMessage = new Message(MessageType.ExitRoom, sender);
		        delegator.delegate(exitMessage);
    		    
    		    try {
                    socket.close();
                } catch (IOException e1) {
                    // Nothing to do
                }
    		    
    		    break;
    		}
		}
    }
}
