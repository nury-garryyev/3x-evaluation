package io.mend.sast.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/loop")
public class InfiniteLoopController {

    @GetMapping(value = "/unsafe")
    public void unsafe(@QueryParam("input") String input, HttpServletResponse response) throws IOException {

        input = method1(input);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(input); // XSS Sink
    }

    private String method1(String input) {
        return method2(input);
    }

    private String method2(String input) {
        return method1(input);
    }
}
