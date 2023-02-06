package io.mend.sast.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ServerEndpoint(value = "/app")
public class WebSocketServerEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerEndpoint.class);

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected, sessionID = " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        if (message.equals("quit")) {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Bye!"));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return message;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Session " + session.getId() + " closed because " + closeReason);
    }
}
