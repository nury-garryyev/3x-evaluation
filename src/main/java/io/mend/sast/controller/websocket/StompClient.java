package io.mend.sast.controller.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.glassfish.tyrus.client.ClientManager;

@RestController
@RequestMapping("/websocket")
public class StompClient {


    @GetMapping(value = "/connectt")
    public void logg(HttpServletRequest request) {
        String url = request.getParameter("url");

        CountDownLatch latch = new CountDownLatch(1);
        ClientManager client = ClientManager.createClient();
        try {
            URI uri = new URI(url);
            client.connectToServer(ChatClientEndpoint.class, uri);
            latch.await();
        } catch (URISyntaxException | InterruptedException |
                 jakarta.websocket.DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

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

    @GetMapping(value = "/ssrf-spring")
    public void ssrfSpring(HttpServletRequest request2, HttpServletResponse response2) {
        String fooResourceUrl = request2.getParameter("uri");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(new String("bar"));
        try {
            restTemplate.exchange(fooResourceUrl, HttpMethod.POST, request, String.class); // $ SSRF

            {
                String body = new String("body");
                URI uri = new URI(fooResourceUrl);
                RequestEntity<String> requestEntity = RequestEntity.post(uri).body(body); // $ SSRF
                ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

                RequestEntity.put(uri); // $ SSRF
            }
        } catch (org.springframework.web.client.RestClientException | java.net.URISyntaxException e) {}
    }

    @GetMapping(value = "/ssrf-javanet")
    public void ssrfJavaNet(HttpServletRequest request, HttpServletResponse response) {
        try {

            String sink = request.getParameter("uri");
            URI uri = new URI(sink);
            URI uri2 = new URI("http", sink, "fragement");
            URL url1 = new URL(sink);

            URLConnection c1 = url1.openConnection(); // $ SSRF
            SocketAddress sa = new SocketAddress() {
            };
            URLConnection c2 = url1.openConnection(new Proxy(Proxy.Type.HTTP, sa)); // $ SSRF
            InputStream c3 = url1.openStream(); // $ SSRF

            // java.net.http
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request2 = HttpRequest.newBuilder().uri(uri2).build(); // $ SSRF
            HttpRequest request3 = HttpRequest.newBuilder(uri).build(); // $ SSRF

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}