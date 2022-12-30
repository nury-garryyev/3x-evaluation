package io.mend.sast.controller.cwe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cwe497")
public class cwe497 {

    @Autowired
    private Environment env;

    @Value("${prop.key}")
    private String propKey;

    private static final Logger logger = LoggerFactory.getLogger(cwe497.class);

    @GetMapping(value = "/info")
    public String info() {


        String path = env.getProperty("prop.key");

        return propKey + path;
    }
}
