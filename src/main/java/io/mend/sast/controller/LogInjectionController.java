package io.mend.sast.controller;

import io.mend.sast.model.SafeObject;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogInjectionController {

    private static final Logger logger = LoggerFactory.getLogger(LogInjectionController.class);

    @GetMapping("/internal-error")
    public ResponseEntity<String> internalError() {
        throw new InternalError("internal error");
    }

    @GetMapping("/sanitizer-equals/{input}")
    @ResponseBody
    public String equals(@PathVariable String input) {

        if(input.equals("safe")) {
            logger.info("Log safe user input: " + input);
        }

        return "log injection sanitized with equals";
    }

    @GetMapping("/sanitizer-contains/{input}")
    @ResponseBody
    public String contains(@PathVariable String input) {

        List<String> allowList = new ArrayList<>();
        allowList.add("safe");

        if(allowList.contains(input)) {
            logger.info("Log safe user input: " + input);
        }

        return "log injection sanitized with contains";
    }

    @GetMapping(value = "/safeObject1")
    public void safeObject1(@RequestBody SafeObject safeObject) {
        logger.info("Log safe user input: " + safeObject);
    }

    @GetMapping(value = "/safeObject2")
    public void safeObject2(@RequestBody SafeObject safeObject) {
        log(safeObject.getMax());
    }

    @GetMapping(value = "/safeInput1")
    public void safeInput1(HttpServletRequest request) {
        try {
            boolean safe = Boolean.parseBoolean(request.getParameter("input"));
        } catch(Exception e) {
            logger.info(request.getParameter("input"));
        }
    }

    @GetMapping(value = "/safeInput2")
    public void safeInput2(HttpServletRequest request) {
        String input = request.getParameter("input");
        if(input.length() == 0) {
            input = input + "safe";
            logger.info(input);
        }
    }

    @GetMapping(value = "/safeInput3")
    public void safeInput3(HttpServletRequest request) {
        String input = request.getParameter("input");
        logger.info(String.valueOf(input.getBytes()));
    }

    @GetMapping(value = "/unsafeInput")
    public void unsafeInput(HttpServletRequest request) {
        String input = request.getParameter("input");

        // Unsafe
        logger.info(parseBytes(input)); // SINK
    }

    private void log(Object object) {
        logger.info("Log safe user input: " + object);
    }

    private String parseBytes(String input) {
        StringBuilder output = new StringBuilder();

        for (byte x: input.getBytes()) {
            output.append((char)x);
        }

        return output.toString();
    }
}
