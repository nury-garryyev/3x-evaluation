package io.mend.sast.controller.cwe;

import io.mend.sast.service.EchoServer;
import io.mend.sast.service.FtpService;
import io.mend.sast.websocket.WebSocketClientEndpoint;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.net.ftp.FTPSClient;
import org.glassfish.tyrus.client.ClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.*;
import java.sql.DriverManager;

@RestController
@RequestMapping("/cwe941")
public class cwe941 {

    private static final Logger logger = LoggerFactory.getLogger(cwe941.class);

    @Autowired
    private FtpService ftpService;

    @GetMapping(value = "/dbConnect")
    public void dbConnect(HttpServletRequest request) {
        String url = request.getParameter("url");

        try {
            Class.forName ("org.h2.Driver");
            DriverManager.getConnection(url, "sa", "password"); // SINK
        } catch (Exception e) {
            logger.error("DB error");
        }

    }

    @GetMapping(value = "/ftpConnect")
    public void ftpConnect(HttpServletRequest request) {
        String url = request.getParameter("url");

        try {
            FTPSClient ftpClient = ftpService.loginFtp(url, 21, "root", "password"); // SINK in FtpService
            ftpService.printTree("/", ftpClient);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @GetMapping(value = "/websocketConnect")
    public void websocketConnect(HttpServletRequest request) {
        String url = request.getParameter("url");

        ClientManager client = ClientManager.createClient();

        try {
            URI uri = new URI(url);
            client.connectToServer(WebSocketClientEndpoint.class, uri); // SINK
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @GetMapping(value = "/udpSend")
    public String udpSend(HttpServletRequest request) throws IOException {
        String message = request.getParameter("message");

        new EchoServer().start();

        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");

        byte[] buf = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);

        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        return new String(packet.getData(), 0, packet.getLength());
    }
}
