package io.mend.sast.controller.cwe;

import io.mend.sast.model.ErrorResponse;
import io.mend.sast.model.User;
import io.mend.sast.service.UserSearchService;
import io.mend.sast.util.ApplicationConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cwe209")
public class cwe209 {

    private static final Logger logger = LoggerFactory.getLogger(cwe209.class);

    @GetMapping(value = "/log")
    public void log(HttpServletRequest request) {
        String input = request.getParameter("input");

        try {
            UserSearchService.getUserByNameUnsafe(input);
        } catch (Exception e) {
            // This error message may contain sensitive data that is NOT accessible to the unauthorised user.
            logger.error(e.getMessage());
        }
    }

    @GetMapping(value = "/exceptionUnsafe")
    public void exceptionUnsafe(HttpServletRequest request) throws Exception {
        String input = request.getParameter("input");

        try {
            UserSearchService.getUserByNameUnsafe(input);
        } catch (Exception e) {

            // Unsafe: The SQL exception may contain sensitive data,
            // and this exception type is exposed to the user in GlobalExceptionHandler.
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping(value = "/exceptionSafeMessage")
    public void exceptionSafeMessage(HttpServletRequest request) throws Exception {
        String input = request.getParameter("input");

        try {
            UserSearchService.getUserByNameUnsafeQuerySafeMessage(input);
        } catch (Exception e) {

            // Safe: The message created in the getUserByNameUnsafeQuerySafeMessage method is safe
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping(value = "/exceptionSafe")
    public void exceptionSafe(HttpServletRequest request) throws SQLException {
        String input = request.getParameter("input");

        try {
            UserSearchService.getUserByNameUnsafe(input);
        } catch (SQLException e) {

            // Safe: The SQL exception may contain sensitive data,
            // but the SQLException error message is not added in the GlobalExceptionHandler.
            // Instead, it adds a hard-coded message that contains no sensitive data.
            throw new SQLException(e.getMessage());
        }
    }

    @GetMapping(value = "/unsafe")
    public void unsafe(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String result;

        String input = request.getParameter("input");

        try {
            User user = UserSearchService.getUserByNameUnsafe2(input);

            result = user == null ? "User not found" : "User name: " + user.getName();

        } catch (Exception e) {

            // Unsafe: The SQL exception may contain sensitive data that is exposed to the user.
            result = "Error: " + e.getLocalizedMessage();
        }

        out.println(result); // SINK
    }

    @GetMapping(value = "/unsafeResponse")
    public ResponseEntity<Object> unsafeResponse(HttpServletRequest request, HttpServletResponse response) {
        String result;
        String input = request.getParameter("input");

        try {
            User user = UserSearchService.getUserByNameUnsafe(input);

            result = user == null ? "User not found" : "User name: " + user.getName();

        } catch (Exception e) {
            List<String> details = new ArrayList<>();
            details.add(e.getLocalizedMessage());
            ErrorResponse error = new ErrorResponse(ApplicationConstants.SERVER_ERROR, details);

            // Unsafe: The SQL exception may contain sensitive data that is exposed to the user.
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // SINK
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
