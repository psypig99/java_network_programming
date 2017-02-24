package edu.kosta.network.framework.message;

import edu.kosta.network.framework.Delegator;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sejun on 17. 2. 24.
 */
public class QueueMonitor implements Runnable {

    private MessageQueue queue;
    private Delegator delegator;

    private ExecutorService executorService;

    public QueueMonitor(MessageQueue queue, Delegator delegator) {
        this.queue = queue;
        this.delegator = delegator;
        this.executorService = Executors.newFixedThreadPool(5);
    }

    @Override
    public void run() {
        while(true){
            BlockMessage message = queue.take();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    delegator.delegate(message);
                }
            });

        }
    }
}
