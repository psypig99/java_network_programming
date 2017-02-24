package edu.kosta.chatting.chat_wednesday.client;

import edu.kosta.chatting.chat_wednesday.client.cli.TalkCli;
import edu.kosta.chatting.chat_wednesday.framework.Dispatcher;
import edu.kosta.chatting.chat_wednesday.framework.io.SocketDispatcher;
import edu.kosta.chatting.chat_wednesday.framework.io.SocketReactor;
import edu.kosta.chatting.chat_wednesday.framework.io.SocketWorker;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class TalkClient {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("localhost", 10000);

		System.out.println("Connected to server..." + socket);
		SocketWorker socketWorker = new SocketWorker(socket);
		Dispatcher dispatcher = new SocketDispatcher(socketWorker);

		TalkMessageHandler handler = new TalkMessageHandler();
		ServerMessageDelegator delegator = new ServerMessageDelegator(handler);
		SocketReactor reactor = new SocketReactor(socketWorker, delegator);

		Talker talker = new Talker(dispatcher);
		handler.setTalker(talker);

		TalkCli talkCli = new TalkCli();
		talkCli.addInputListener(talker);

		new Thread(reactor).start();
		new Thread(talkCli).start();
		System.out.println("Start talking...");

	}
}
