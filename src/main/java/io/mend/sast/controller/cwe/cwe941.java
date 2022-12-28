package io.mend.sast.controller.cwe;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
@RequestMapping("/cwe941")
public class cwe941 {

    private static Connection JDBCConnection;

    private final Logger logger = LoggerFactory.getLogger(cwe941.class);

    @GetMapping(value = "/log")
    public void log(HttpServletRequest request) {
        String url = request.getParameter("url");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        getJDBCConnection(url, user, pass);
    }

    public Connection getJDBCConnection(String url, String user, String pass) {

        if (JDBCConnection == null) {
            try {
                Class.forName ("org.h2.Driver");
                JDBCConnection = DriverManager.getConnection(url, user, pass); // SINK
            } catch (Exception e) {
                logger.error("DB error");
            }
        }
        return JDBCConnection;
    }
}
