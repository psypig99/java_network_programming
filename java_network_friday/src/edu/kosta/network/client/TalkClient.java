package edu.kosta.network.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.kosta.network.client.cli.TalkCli;
import edu.kosta.network.framework.Dispatcher;
import edu.kosta.network.framework.io.SocketDispatcher;
import edu.kosta.network.framework.io.SocketReactor;
import edu.kosta.network.framework.io.SocketWorker;

public class TalkClient {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("localhost", 10000);

		System.out.println("Connected to server..." + socket);
		SocketWorker socketWorker = new SocketWorker(socket);
		Dispatcher dispatcher = new SocketDispatcher(socketWorker);

		TalkMessageHandler handler = new TalkMessageHandler();
		ServerMessageDelegator delegator = new ServerMessageDelegator(handler);
		SocketReactor reactor = new SocketReactor(socketWorker, delegator);

		Talker talker = new Talker("홍세준", dispatcher);
		handler.setTalker(talker);

		TalkCli talkCli = new TalkCli();
		talkCli.addInputListener(talker);

		new Thread(reactor).start();
		new Thread(talkCli).start();
		System.out.println("Start talking...");

	}
}
