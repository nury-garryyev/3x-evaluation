package io.mend.sast.controller.cwe;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.mend.sast.util.ApplicationConstants;
import io.mend.sast.util.MailUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/mail_relay")
public class mail_relay {
   
    private JavaMailSender javaMailSender = MailUtil.getMailSender();

    @PostMapping("/unsafe/send_email1")
    public ResponseEntity<String> sendEmailUnsafe1(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String sender = "";
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("email")){
                sender = cookie.getValue();
            }
        }

        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject(request.getParameter("subject"));
            message.setFrom(sender);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(request.getParameter("to")));
            message.setText(request.getParameter("email_body"));
            javaMailSender.send(message); //SINK
            return new ResponseEntity<>("Email successfully sent.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/unsafe/send_email2")
    public ResponseEntity<String> sendMailUnsafe2(HttpServletRequest request, HttpServletResponse response){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.example.com");
        Session session = Session.getDefaultInstance(props);
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ApplicationConstants.EMAIL_ADDRESS));
            message.setRecipient(Message.RecipientType.TO,
                new InternetAddress(request.getParameter("to"))
            );
            message.setSubject("Custom Notification");
            message.setText(request.getParameter("email_body"));
            Transport.send(message); // SINK
            return new ResponseEntity<>("Email sent", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/safe/send_email")
    public ResponseEntity<String> sendMail(HttpServletRequest request, HttpServletResponse response) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("username", "password");
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ApplicationConstants.EMAIL_ADDRESS));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("to@example.com"));
            message.setSubject("Notification");
            message.setText("Hello, this is an email notification.");
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>("Password reset email sent!", HttpStatus.OK);
    }
}
