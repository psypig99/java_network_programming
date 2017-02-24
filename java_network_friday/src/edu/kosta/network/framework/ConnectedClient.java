package edu.kosta.network.framework;

public class ConnectedClient {

	private Dispatcher dispatcher;
	private Reactor reactor;
	
	public ConnectedClient(Dispatcher dispatcher, Reactor reactor) {
		this.dispatcher = dispatcher;
		this.reactor = reactor;
	}
	
	public Dispatcher getDispatcher() {
		return dispatcher;
	}
	
	public Reactor getReactor() {
		return reactor;
	}
}
