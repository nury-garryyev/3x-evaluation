package io.mend.sast.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement(name="GreetingMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class GreetingMessage implements Serializable {

    @XmlElement(name="message")
    private String message;

    public static GreetingMessage of(String s) {
        final var message = new GreetingMessage();
        message.setMessage(s);
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
