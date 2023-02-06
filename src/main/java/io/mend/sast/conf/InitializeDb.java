package io.mend.sast.conf;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Statement;

@Component
public class InitializeDb {

    private static final Logger logger = LoggerFactory.getLogger(InitializeDb.class);

    @PostConstruct
    private void InitializeH2Db() {
        try (Statement statement = JDBCConfiguration.getJDBCConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS \"user\"");
            statement.execute("CREATE TABLE \"user\" (id int NOT NULL AUTO_INCREMENT, role int, name varchar(50), PRIMARY KEY (id));");
            statement.execute("INSERT INTO \"user\" (name, role) VALUES ('james', 0)");
            statement.execute("INSERT INTO \"user\" (name, role) VALUES ('rafael', 1)");
            statement.execute("INSERT INTO \"user\" (name, role) VALUES ('carlos', 0)");
        } catch (Exception e) {
            logger.error("db initialization error");
        }
    }
}
