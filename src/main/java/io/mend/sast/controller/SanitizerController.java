package io.mend.sast.controller;

import io.mend.sast.conf.JDBCConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
@RequestMapping("sanitizer")
public class SanitizerController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = "/db")
    public void safe(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String input = request.getParameter("input");

        String sqlQuery  = "SELECT name FROM \"user\" WHERE name=?";

        try (PreparedStatement statement = JDBCConfiguration.getJDBCConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, input);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                // the input is sanitized by checking it from the DB
                out.println(input);
            } else {
                out.println("no user");
            }
        } catch (Exception e) {
            logger.error("db error");
        }
    }
}
