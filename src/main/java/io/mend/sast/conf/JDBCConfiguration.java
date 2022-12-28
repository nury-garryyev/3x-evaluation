package io.mend.sast.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConfiguration {

    private static Connection JDBCConnection;

    private static final Logger logger = LoggerFactory.getLogger(JDBCConfiguration.class);
    private static final String url = "jdbc:h2:mem:testdb;INIT=CREATE SCHEMA IF NOT EXISTS TESTDB";
    private static final String input = "hard-coded";

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
}

