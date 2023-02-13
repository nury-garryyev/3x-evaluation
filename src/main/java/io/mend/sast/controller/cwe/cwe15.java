package io.mend.sast.controller.cwe;

import jakarta.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/cwe15")
public class cwe15 {

    @GetMapping(value = "/unsafe")
    public void unsafe(@QueryParam("input") String input) throws IOException {
        System.setProperty("input", input);
        System.setProperty(input, "input");
    }
}
