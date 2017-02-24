package edu.kosta.chatting.chat_thrusday;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(10000);
		System.out.println("Server socket is opened..." + serverSocket);
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		List<Socket> connectedClientSockets = new ArrayList<>();
		
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("Client is connected..." + socket);
			connectedClientSockets.add(socket);
			
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							String receivedMessage = dis.readUTF();
							System.out.print(socket);
							System.out.println(receivedMessage);
//							dos.writeUTF(receivedMessage);
							
							for(Socket connectedClientSocket : connectedClientSockets) {
								DataOutputStream dos = new DataOutputStream(connectedClientSocket.getOutputStream());
								dos.writeUTF(receivedMessage);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
							connectedClientSockets.remove(socket);
							break;
						}
					}
				}
			});
			
		}
		
	}
	
	
	
	
	
	
}
