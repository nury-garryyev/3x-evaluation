package io.mend.sast.service;

import io.mend.sast.conf.JDBCConfiguration;
import io.mend.sast.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserSearchService {

    private static final Logger logger = LogManager.getLogger(UserSearchService.class);

    public static User getUserByNameUnsafe(String name) throws SQLException {

        String sqlQuery  = "SELECT * FROM \"user\" WHERE name='" + name + "'";

        try (Statement statement = JDBCConfiguration.getJDBCConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(sqlQuery); // SQLi SINK

            if(rs.next()) {
                return new User(rs.getString("name"));
            }

        } catch (SQLException e) {
            // This error message may contain sensitive data that is not accessible to the unauthorised user.
            logger.error(e.getMessage());

            // This exception may throw sensitive data, but we do not know if it is exposed to the user.
            throw new SQLException(e.getMessage());
        }

        return null;
    }

    public static User getUserByNameUnsafeQuerySafeMessage(String name) throws SQLException {

        String sqlQuery  = "SELECT * FROM \"user\" WHERE name='" + name + "'";

        try (Statement statement = JDBCConfiguration.getJDBCConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(sqlQuery); // SQLi SINK

            if(rs.next()) {
                return new User(rs.getString("name"));
            }

        } catch (SQLException e) {
            logger.error("db error");
            throw new SQLException("db error");
        }

        return null;
    }

    public static User getUserByName(String name) throws SQLException {

        String sqlQuery  = "SELECT * FROM \"user\" WHERE name=?";

        try (PreparedStatement statement = JDBCConfiguration.getJDBCConnection().prepareStatement(sqlQuery)) {

            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return new User(rs.getString("name"));
            }

        } catch (SQLException e) {
            // This error message may contain sensitive data that is not accessible to the unauthorised user.
            logger.error(e.getMessage());

            // This exception may throw sensitive data, but we do not know if it is exposed to the user.
            throw new SQLException(e.getMessage());
        }

        return null;
    }

    public static User getUserByNameUnsafe2(String name) throws SQLException {
        String sqlQuery  = "SELECT * FROM \"user\" WHERE name='" + name + "'";

        Statement statement = JDBCConfiguration.getJDBCConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery); // SQLi SINK

        if(rs.next()) {
            return new User(rs.getString("name"));
        }

        return null;
    }

    public static User getUserById(int id) throws SQLException{
        String sqlQuery = "SELECT * FROM \"user\" WHERE id=" + String.valueOf(id);

        Statement statement = JDBCConfiguration.getJDBCConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);

        if(rs.next()){
            return new User(rs.getString("name"), rs.getInt("role"));
        }

        return null;
    }
}
