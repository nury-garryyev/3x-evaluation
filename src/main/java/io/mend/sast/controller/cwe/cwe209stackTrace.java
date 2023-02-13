package io.mend.sast.controller.cwe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("/cwe209")
public class cwe209stackTrace {

    @GetMapping("/stack_trace")
    public ResponseEntity<String> exposeStackTrace() {
        try {
            throw new Exception();
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            String stackTrace = stringWriter.toString();
            return new ResponseEntity<>(stackTrace, HttpStatus.INTERNAL_SERVER_ERROR); // SINK
        }
    }

    @GetMapping("/to_string")
    public ResponseEntity<String> exposeToString() {
        try {
            throw new Exception();
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR); // SINK
        }
    }

    @GetMapping("/stack_trace_safe1")
    public void exposeStackTraceSafe1() {
        try {
            throw new Exception();
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
        }
    }

    @GetMapping("/stack_trace_safe2")
    public void exposeStackTraceSafe2() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
