package io.mend.sast.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoServer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);

    private static DatagramSocket socket;
    private volatile boolean running;
    private final byte[] buf = new byte[256];

    public EchoServer() throws SocketException {
        if(socket == null)
            socket = new DatagramSocket(4445);
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // UDP allows the source IP address to be easily changed ('spoofed')
            InetAddress address = packet.getAddress(); // SOURCE
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port); // SINK: UDP flood attack

            String received = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("end")) {
                running = false;
                continue;
            }
            try {
                socket.send(packet);

                logger.info(address + ":" + port + " -> " + new String(packet.getData(), 0, packet.getLength()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        socket.close();
    }
}
