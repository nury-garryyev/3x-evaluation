package io.mend.sast.websocket;

import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@ClientEndpoint
public class WebSocketClientEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerEndpoint.class);

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected: " + session.getId());

        try {
            session.getBasicRemote().sendText("start");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            logger.info("Received: " + message);

            return bufferRead.readLine();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Session " + session.getId() + " closed because " + closeReason);
    }
}
