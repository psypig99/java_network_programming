package edu.kosta.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Scanner scanner = new Scanner(System.in);
		Socket socket = new Socket("localhost", 10000);
		System.out.println("Connected to server..." + socket);
		
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					System.out.println("Input message");
					String inputMessage = scanner.nextLine();
					try {
						dos.writeUTF(inputMessage);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		while(true) {
			try {
				String receivedMessage = dis.readUTF();
				System.out.println("Received message:" + receivedMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
