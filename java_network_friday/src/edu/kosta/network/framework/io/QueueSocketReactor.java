package edu.kosta.network.framework.io;

import edu.kosta.network.framework.Delegator;
import edu.kosta.network.framework.message.BlockMessage;
import edu.kosta.network.framework.message.MessageQueue;

/**
 * Created by sejun on 17. 2. 24.
 */
public class QueueSocketReactor extends SocketReactor {

    private MessageQueue queue;

    public QueueSocketReactor(SocketWorker socketWorker, Delegator delegator, MessageQueue queue) {
        super(socketWorker, delegator);
        this.queue = queue;
    }

    @Override
    public void receive(BlockMessage message) {
        this.queue.put(message);
    }
}
