package io.mend.sast.service;

import io.mend.sast.model.GreetingMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GreetingService {

    public GreetingMessage buildGreetingMessage(String name) {
        return GreetingMessage.of("Say Hello to " + name + " at " + LocalDateTime.now());
    }
}
