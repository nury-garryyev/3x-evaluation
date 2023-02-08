package io.mend.sast.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class JDBCConfiguration {

    private static Connection JDBCConnection;

    private static final Logger logger = LoggerFactory.getLogger(JDBCConfiguration.class);
    private static final String url = "jdbc:h2:mem:testdb;INIT=CREATE SCHEMA IF NOT EXISTS TESTDB";
    private static final String input = "hard-coded";



    private final String password;

    @Autowired
    public JDBCConfiguration(@Value("${db.password}") String password) {
        this.password = password;
    }


    public static Connection getJDBCConnection() {
        if (JDBCConnection == null) {
            try {
                Class.forName ("org.h2.Driver");
                JDBCConnection = DriverManager.getConnection(url, "sa", input); // SINK
            } catch (Exception e) {
                logger.error("DB error");
            }
        }
        return JDBCConnection;
    }

    public Connection getJDBCConnection2() {

        if (JDBCConnection == null) {
            try {
                Class.forName ("org.h2.Driver");
                JDBCConnection = DriverManager.getConnection(url, "sa", password); // SINK
            } catch (Exception e) {
                logger.error("DB error");
            }
        }
        return JDBCConnection;
    }
}

