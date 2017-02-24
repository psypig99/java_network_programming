package edu.kosta.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by sejun on 17. 2. 24.
 */
public class UdpServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(10001);

        byte[] buf = new byte[1024];

        while (true){
            DatagramPacket datagramPacket = new DatagramPacket(buf, 1024);
            datagramSocket.receive(datagramPacket);
            System.out.println(new String(datagramPacket.getData()));
        }

    }
}
