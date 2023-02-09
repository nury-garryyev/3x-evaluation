package io.mend.sast.controller.cwe;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cwe113")
public class cwe113 {
    
    @GetMapping
    @RequestMapping(value = "/unsafe/referer")
    public ResponseEntity<String> handleUnsafe1(HttpServletRequest request, HttpServletResponse response, @RequestParam String referer) {
        response.setHeader("Referer", referer); //SINK
        return ResponseEntity.ok().body("Referer set");
    }

    @GetMapping
    @RequestMapping(value = "/unsafe/language")
    public ResponseEntity<String> handleUnsafe2(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang){
        Cookie cookie = new Cookie("lang", lang); //SINK
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().body("Language properly set");
    }
    
    @GetMapping
    @RequestMapping("/safe/referer")
    public ResponseEntity<String> handleSafe1(HttpServletRequest request, HttpServletResponse response, @RequestParam String referer) {
        String sanitized_referer = referer.replaceAll("c", "d");
        response.setHeader("Referer", sanitized_referer);
        return ResponseEntity.ok().body("Referer set");
    }
    
    @GetMapping
    @RequestMapping("/safe/language")
    public ResponseEntity<String> handleSafe2(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang) {
        String sanitized_lang = lang.replaceAll("a", "b");
        Cookie cookie = new Cookie("lang", sanitized_lang);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().body("Language set");
    }


}
