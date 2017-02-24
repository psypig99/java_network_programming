package edu.kosta.network.udp;

import java.io.IOException;
import java.net.*;

/**
 * Created by sejun on 17. 2. 24.
 */
public class UdpClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        SocketAddress socketAddress = new InetSocketAddress("localhost", 10001);

        while (true){
            byte[] buf = "hello world".getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, socketAddress);
            datagramSocket.send(datagramPacket);
            System.out.println("message sent ...");
        }


    }
}
