package io.mend.sast.util;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

public class MailUtil {
    private static JavaMailSender javaMailSender;

    @Bean
    public static JavaMailSender getMailSender(){
        return javaMailSender;
    }    
}
