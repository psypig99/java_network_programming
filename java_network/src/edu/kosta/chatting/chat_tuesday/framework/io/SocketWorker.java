package edu.kosta.chatting.chat_tuesday.framework.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by sejun on 17. 2. 22.
 * 소켓을 읽고 쓰기만 하는 녀석임.
 */
public class SocketWorker {

    private DataInputStream dis;
    private DataOutputStream dos;
    /**
     * unchecked exception은 exception이 발생해도 프로그램이 죽지 않는 exception
     * @param socket
     */
    public SocketWorker(Socket socket){
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() throws IOException {
        return this.dis.readUTF();
    }

    public void write(String message) throws IOException {
        this.dos.writeUTF(message);
    }
}
