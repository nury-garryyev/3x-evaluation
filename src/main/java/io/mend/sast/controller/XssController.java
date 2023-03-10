package io.mend.sast.controller;

import io.mend.sast.model.SafeObject;
import io.mend.sast.service.GreetingService;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("xss")
public class XssController {

    @Autowired
    GreetingService greetingService;

    @GetMapping(value = "/safeInput")
    public void safeInput(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(request.getParameter("input"));
    }

    @GetMapping(value = "/safeObject1")
    public void safeObject1(@RequestBody SafeObject safeObject, HttpServletResponse response) throws IOException {
        String output = "max: " + safeObject.getMax();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(output);
    }

    @GetMapping(value = "/safeObject2")
    public void safeObject2(@RequestBody SafeObject safeObject, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safeObject.toString());
    }

    @GetMapping(value = "/safeObject3")
    public void safeObject4(@RequestBody SafeObject safeObject, HttpServletResponse response) throws IOException {

        String safe = String.valueOf(safeObject.getMax());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/unsafe1")
    public void unsafe1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String input = request.getParameter("input");

        // Unsafe
        out.println(input); // SINK
    }

    @GetMapping(value = "/unsafe2")
    public ResponseEntity<String> unsafe2(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String input = request.getParameter("input");

        // Unsafe
        return new ResponseEntity<>(input, HttpStatus.OK); // SINK
    }

    @GetMapping(value = "/unsafe3")
    public void unsafe3(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String input = request.getParameter("input");

        // Unsafe
        out.println(greetingService.buildGreetingMessage(input).getMessage()); // SINK
    }

    @GetMapping(value = "/unsafe4")
    public void unsafe4(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String input = request.getParameter("input");

        // Unsafe
        out.println(new GreetingService().buildGreetingMessage(input).getMessage()); // SINK
    }
}
