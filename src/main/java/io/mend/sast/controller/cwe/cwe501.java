package io.mend.sast.controller.cwe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cwe501")
public class cwe501 {

    @GetMapping(value = "/session")
    public void session(HttpServletRequest request, HttpSession sessionArgument) {
        HttpSession sessionRequest = request.getSession();

        String input = request.getParameter("input");

        sessionArgument.setAttribute("input", input);
        sessionArgument.setAttribute(input, "input");

        sessionRequest.setAttribute("input", input);
        sessionRequest.setAttribute(input, "input");
    }
}
