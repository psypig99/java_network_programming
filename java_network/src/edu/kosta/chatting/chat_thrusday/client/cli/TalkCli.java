package edu.kosta.chatting.chat_thrusday.client.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TalkCli implements Runnable {

	private Scanner scanner;
	private List<InputListener> listeners;

	public TalkCli() {
		this.scanner = new Scanner(System.in);
		this.listeners = new ArrayList<>();
	}

	@Override
	public void run() {
		System.out.println("Input message:");
		while (true) {
			String inputMessage = scanner.nextLine();
			listeners.forEach(listener -> listener.inputMessage(inputMessage));
		}
	}

	public void addInputListener(InputListener listener) {
		this.listeners.add(listener);
	}

}
