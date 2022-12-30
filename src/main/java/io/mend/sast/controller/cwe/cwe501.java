package io.mend.sast.controller.cwe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cwe501")
public class cwe501 {

    private static final Logger logger = LoggerFactory.getLogger(cwe501.class);

    @GetMapping(value = "/session")
    public void session(HttpServletRequest request, HttpSession session) {
        String input = request.getParameter("input");
        String a = request.getParameter("a");
        String b = request.getParameter("b");

        session.setAttribute("input", input);
        session.setAttribute(input, "input");
        session.setAttribute(a, b);

        HttpSession session1 = request.getSession();
        session1.setAttribute("input", input);
        session1.setAttribute(input, "input");
        session1.setAttribute(a, b);
    }
}
