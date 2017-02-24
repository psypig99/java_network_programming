package edu.kosta.network.framework.message;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by sejun on 17. 2. 24.
 */
public class MessageQueue {

    private ArrayBlockingQueue<BlockMessage> queue;

    public MessageQueue() {
        this.queue = new ArrayBlockingQueue<BlockMessage>(100);
    }

    public void put(BlockMessage message){
        try {
            this.queue.put(message);
            System.out.println("Message added to queue ... queue size " + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public BlockMessage take(){
        try {
            BlockMessage message = this.queue.take();
            System.out.println("Message taken from queue ... queue size " + queue.size());
            return message;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public BlockMessage poll(){
        BlockMessage message = this.queue.poll();
        System.out.println("Message poll from queue ... queue size " + queue.size());
        return message;
    }
}
