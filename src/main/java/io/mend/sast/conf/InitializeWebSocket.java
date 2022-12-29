package io.mend.sast.conf;

import io.mend.sast.websocket.WebSocketServerEndpoint;
import org.glassfish.tyrus.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeWebSocket {

    private static final Logger logger = LoggerFactory.getLogger(InitializeWebSocket.class);
    private static Server server;

    @PostConstruct
    private void InitializeServer() {
        server = new Server("localhost", 8035, "/folder", null, WebSocketServerEndpoint.class);

        try {
            server.start();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static void stop() {
        server.stop();
    }
}
