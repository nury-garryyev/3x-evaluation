package io.mend.sast.controller;

import io.mend.sast.conf.JDBCConfiguration;
import jakarta.ws.rs.QueryParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Controller
@RequestMapping("/GeneralSanitizer")
public class GeneralSanitizerController {

    @GetMapping(value = "/safe1")
    public void safe1(@QueryParam("input") String input) throws SQLException {

        ArrayList<String> list = new ArrayList<>(List.of("safe"));
        HashSet<String> set = new HashSet<>(List.of("safe"));
        TreeMap<String, String> map = new TreeMap<>(Map.of("safe", "safe"));
        Properties properties = new Properties();
        properties.setProperty("safe", "safe");

        if(input.equalsIgnoreCase("safe")) {
            sqli(input);
        }

        if("safe".equalsIgnoreCase(input)) {
            sqli(input);
        }

        if(input.equals("safe")) {
            sqli(input);
        }

        if("safe".equals(input)) {
            sqli(input);
        }

        if(input.contentEquals("safe")) {
            sqli(input);
        }

        if("safe".contentEquals(input)) {
            sqli(input);
        }

        if(input.contentEquals(new StringBuffer("safe"))) {
            sqli(input);
        }

        if("safe".contentEquals(new StringBuffer(input))) {
            sqli(input);
        }

        if(list.contains(input)) {
            sqli(input);
        }

        if(set.contains(input)) {
            sqli(input);
        }

        if(map.containsKey(input)) {
            sqli(input);
        }

        if(map.containsValue(input)) {
            sqli(input);
        }

        if(properties.containsKey(input)) {
            sqli(input);
        }

        if(properties.containsValue(input)) {
            sqli(input);
        }

        if(properties.contains(input)) {
            sqli(input);
        }
    }

    @GetMapping(value = "/safe2")
    public void safe2(@QueryParam("input") String input) throws SQLException {
        Level inputEnum = Level.valueOf(input);
        sqli(inputEnum.toString());
    }

    @GetMapping(value = "/safe3")
    public void safe3(@QueryParam("input") String input) throws SQLException {
        Locale inputLocale = Locale.forLanguageTag(input);
        sqli(inputLocale.toString());
    }

    @GetMapping(value = "/safe4")
    public void safe4(@QueryParam("input") String input) throws SQLException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        sqli(Arrays.toString(digest));
    }

    @GetMapping(value = "/safe5")
    public void safe5(@QueryParam("input") String input) throws SQLException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(input.getBytes());
        sqli(Arrays.toString(digest));
    }

    @GetMapping(value = "/safe6")
    public void safe6(@QueryParam("input") String input) throws SQLException {
        Integer inputInt = Integer.parseInt(input);
        sqli(String.valueOf(inputInt));
    }

    @GetMapping(value = "/safe7")
    public void safe7(@QueryParam("input") String input) throws SQLException {
        Boolean inputBool = Boolean.parseBoolean(input);
        sqli(String.valueOf(inputBool));
    }

    @GetMapping(value = "/safe8")
    public void safe8(@QueryParam("input") String input) throws SQLException {
        sqli(String.valueOf(input.hashCode()));
    }

    private void sqli(String input) throws SQLException {
        try (Statement statement = JDBCConfiguration.getJDBCConnection().createStatement()) {
            statement.executeQuery(input);
        }
    }

    enum Level {
        LOW,
        MEDIUM,
        HIGH
    }
}
