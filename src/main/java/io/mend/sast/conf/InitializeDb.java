package io.mend.sast.conf;

import javax.annotation.PostConstruct;
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
            statement.execute("CREATE TABLE \"user\" (id int NOT NULL AUTO_INCREMENT, name varchar(50), PRIMARY KEY (id));");
            statement.execute("INSERT INTO \"user\" (name) VALUES ('james')");
        } catch (Exception e) {
            logger.error("db initialization error");
        }
    }
}
