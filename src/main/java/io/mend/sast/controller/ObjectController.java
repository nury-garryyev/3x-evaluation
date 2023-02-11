package io.mend.sast.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.QueryParam;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/object")
public class ObjectController {

    @GetMapping(value = "/unsafe2")
    public void unsafe2(@QueryParam("input") String input, HttpServletResponse response) throws IOException {

        TestClass testClass = new TestClass();
        testClass.setInputString(input);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(testClass); // XSS Sink
    }

    @GetMapping(value = "/unsafe3")
    public void unsafe3(@QueryParam("input") String input, HttpServletResponse response) throws IOException {

        LombokClass lombokClass = new LombokClass();
        lombokClass.setInputString(input);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(lombokClass);  // XSS Sink
    }

    @GetMapping(value = "/unsafe4")
    public void unsafe4(@QueryParam("input") String input, HttpServletResponse response) throws IOException {

        LombokClass lombokClass = new LombokClass();
        lombokClass.setInputString(input);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(lombokClass.getInputString());  // XSS Sink
    }
}

class TestClass {

    private int inputInt;
    private String inputString;

    public TestClass() {
    }

    public TestClass(int inputInt, String inputString) {
        this.inputInt = inputInt;
        this.inputString = inputString;
    }

    public int getInputInt() {
        return inputInt;
    }

    public void setInputInt(int inputInt) {
        this.inputInt = inputInt;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "inputInt=" + inputInt +
                ", inputString='" + inputString + '\'' +
                '}';
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
class LombokClass {
    private int inputInt;
    private String inputString;
}