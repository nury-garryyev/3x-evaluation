package io.mend.sast.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("many")
public class ManySourceController {

    @GetMapping(value = "/safe0001")
    public void safe0001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0002")
    public void safe0002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0003")
    public void safe0003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0004")
    public void safe0004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0005")
    public void safe0005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0006")
    public void safe0006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0007")
    public void safe0007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0008")
    public void safe0008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0009")
    public void safe0009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe0010")
    public void safe0010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1001")
    public void safe1001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1002")
    public void safe1002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1003")
    public void safe1003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1004")
    public void safe1004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1005")
    public void safe1005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1006")
    public void safe1006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1007")
    public void safe1007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1008")
    public void safe1008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1009")
    public void safe1009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe1010")
    public void safe1010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2001")
    public void safe2001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2002")
    public void safe2002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2003")
    public void safe2003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2004")
    public void safe2004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2005")
    public void safe2005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2006")
    public void safe2006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2007")
    public void safe2007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2008")
    public void safe2008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2009")
    public void safe2009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe2010")
    public void safe2010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3001")
    public void safe3001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3002")
    public void safe3002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3003")
    public void safe3003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3004")
    public void safe3004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3005")
    public void safe3005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3006")
    public void safe3006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3007")
    public void safe3007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3008")
    public void safe3008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3009")
    public void safe3009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe3010")
    public void safe3010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4001")
    public void safe4001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4002")
    public void safe4002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4003")
    public void safe4003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4004")
    public void safe4004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4005")
    public void safe4005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4006")
    public void safe4006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4007")
    public void safe4007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4008")
    public void safe4008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4009")
    public void safe4009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe4010")
    public void safe4010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5001")
    public void safe5001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5002")
    public void safe5002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5003")
    public void safe5003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5004")
    public void safe5004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5005")
    public void safe5005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5006")
    public void safe5006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5007")
    public void safe5007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5008")
    public void safe5008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5009")
    public void safe5009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe5010")
    public void safe5010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6001")
    public void safe6001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6002")
    public void safe6002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6003")
    public void safe6003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6004")
    public void safe6004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6005")
    public void safe6005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6006")
    public void safe6006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6007")
    public void safe6007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6008")
    public void safe6008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6009")
    public void safe6009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe6010")
    public void safe6010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7001")
    public void safe7001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7002")
    public void safe7002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7003")
    public void safe7003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7004")
    public void safe7004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7005")
    public void safe7005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7006")
    public void safe7006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7007")
    public void safe7007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7008")
    public void safe7008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7009")
    public void safe7009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe7010")
    public void safe7010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8001")
    public void safe8001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8002")
    public void safe8002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8003")
    public void safe8003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8004")
    public void safe8004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8005")
    public void safe8005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8006")
    public void safe8006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8007")
    public void safe8007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8008")
    public void safe8008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8009")
    public void safe8009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe8010")
    public void safe8010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9001")
    public void safe9001(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9002")
    public void safe9002(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9003")
    public void safe9003(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9004")
    public void safe9004(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9005")
    public void safe9005(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9006")
    public void safe9006(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9007")
    public void safe9007(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9008")
    public void safe9008(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9009")
    public void safe9009(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }

    @GetMapping(value = "/safe9010")
    public void safe9010(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long safe = Long.parseLong(request.getParameter("input"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(safe);
    }
}
