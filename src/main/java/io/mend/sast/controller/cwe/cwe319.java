package io.mend.sast.controller.cwe;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/cwe319")
public class cwe319 {

    private static final Logger logger = LoggerFactory.getLogger(cwe319.class);

    @GetMapping(value = "/telnet")
    public void telnet() throws IOException {
        TelnetClient telnet = new TelnetClient(); // BAD
        telnet.connect("example.com", 4287);
    }

    @GetMapping(value = "/ftp")
    public void ftp() throws IOException {
        FTPClient ftpClient = new FTPClient(); // BAD
        ftpClient.connect("example.com", 21);
        ftpClient.login("root", "password");
    }

    @GetMapping(value = "/SMTPClient")
    public void SMTPClient() throws IOException {
        SMTPClient smtpClient = new SMTPClient(); // BAD
        smtpClient.connect("example.com");
    }
}
