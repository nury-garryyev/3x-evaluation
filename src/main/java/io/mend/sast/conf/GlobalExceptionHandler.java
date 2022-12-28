package io.mend.sast.conf;

import io.mend.sast.model.ErrorResponse;
import io.mend.sast.util.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> internalError(Exception ex, WebRequest wr) {
        logger.error("Unable to handle {}", wr.getDescription(false), ex);

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ApplicationConstants.SERVER_ERROR, details);

        // Unsafe: If the exception contains sensitive information, it will be exposed to the user
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // SINK
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> sqlError(Exception ex, WebRequest wr) {
        logger.error("Unable to handle {}", wr.getDescription(false), ex);

        ErrorResponse error = new ErrorResponse(ApplicationConstants.SERVER_ERROR, Collections.singletonList(ApplicationConstants.USER_ERROR));

        // Safe: Only a hard-coded constant message is added to the error response
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
