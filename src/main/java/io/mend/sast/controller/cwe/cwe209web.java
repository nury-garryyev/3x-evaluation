package io.mend.sast.controller.cwe;

import io.mend.sast.exception.DatabaseException;
import io.mend.sast.exception.AnotherException;
import io.mend.sast.exception.UserNotFoundException;
import io.mend.sast.model.User;
import io.mend.sast.service.UserSearchService;
import io.mend.sast.util.ApplicationConstants;
import io.mend.sast.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/cwe209web")
public class cwe209web {

    @RequestMapping(value = "/null/{id}", method = RequestMethod.GET)
    public ModelAndView nullTest(@PathVariable("id") String id) throws Exception {

        if(id.equalsIgnoreCase("0")) {
            throw new NullPointerException("id is null");
        }

        return new ModelAndView("index");
    }

    @RequestMapping(value = "/db/{id}", method = RequestMethod.GET)
    public ModelAndView dbTest(@PathVariable("id") String id) {

        if(id.equalsIgnoreCase("0")) {
            throw new NullPointerException("id is null");
        } else {
            try {
                UserSearchService.getUserByNameUnsafe(id);
            } catch (SQLException e) {

                // Unsafe: The SQL exception may contain sensitive data,
                // and this exception type is exposed to the user in GlobalExceptionHandler.
                throw new DatabaseException(e.getMessage());
            }
        }

        return new ModelAndView("index");
    }

    @RequestMapping(value = "/dbsafe/{id}", method = RequestMethod.GET)
    public ModelAndView dbTestSafe(@PathVariable("id") String id) {

        if(id.equalsIgnoreCase("0")) {
            throw new NullPointerException("id is null");
        } else {
            try {
                UserSearchService.getUserByNameUnsafe(id);
            } catch (SQLException e) {


                // Safe: The hardcoded exception message
                throw new DatabaseException(ApplicationConstants.USER_ERROR);
            }
        }

        return new ModelAndView("index");
    }

    @GetMapping(value = "/unsafeResponse")
    public void unsafeResponse(HttpServletRequest request, HttpServletResponse response) {
        String input = request.getParameter("input");

        try {
            User user = UserSearchService.getUserByNameUnsafe(input);

            if(user == null) {
                throw new Exception("Record not found");
            }
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    @GetMapping(value = "/another")
    public void another(HttpServletRequest request, HttpServletResponse response) {
        String input = request.getParameter("input");

        try {
            UserSearchService.getUserByNameUnsafe(input);
        } catch (Exception e) {
            throw new AnotherException(e.getMessage());
        }
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleException(NullPointerException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ex.getMessage()); // SINK
        return modelAndView;
    }

    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleException(DatabaseException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ex.getMessage()); // SINK
        return modelAndView;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody()
    public String handleException(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(AnotherException.class)
    public void handleException(AnotherException ex, final HttpServletResponse response) throws IOException {
        if( Utils.getRandomNumber(10) > 5) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Sanitize Another Exception");
        } else {
            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }
    }
}