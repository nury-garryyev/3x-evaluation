package io.mend.sast.controller.websocket;

import java.util.Scanner;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@RestController
@RequestMapping("/websocket")
public class StompClient {

//    private static String URL = "ws://localhost:8080/chat/websocket";

    @GetMapping(value = "/connect")
    public void log(HttpServletRequest request) {
        String url = request.getParameter("url");

        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connectAsync(url, sessionHandler);
        stompClient.connect(url, sessionHandler);

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}