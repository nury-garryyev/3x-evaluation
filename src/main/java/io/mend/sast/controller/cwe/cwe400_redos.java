package io.mend.sast.controller.cwe;

import io.mend.sast.model.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/cwe400")
public class cwe400_redos {

    private static final Logger logger = LoggerFactory.getLogger(cwe400_redos.class);

    @PostMapping(value = "/regex")
    public void regex(@RequestBody Credentials credentials) {

        // ReDoS issue has been mitigated in Java 9 and later
        // by having additional protections in their implementation of regular expression evaluation.

        String regex = credentials.getUsername(); // "(a|aa)+"
        String input = credentials.getPassword(); // "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaX"

        Pattern.matches(regex, input); // SINK

//        Pattern.matches(regex, "string"); // SINK
//        Pattern.compile(regex).matcher("string").matches(); // SINK
//        "string".matches(regex); // SINK
//        "string".replaceAll(regex,"replacement"); // SINK
//        "string".replaceFirst(regex,"replacement"); // SINK
//        "string".split(regex); // SINK
    }
}
