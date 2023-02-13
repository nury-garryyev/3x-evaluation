package io.mend.sast.controller.cwe;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cwe497")
public class cwe497 {
    
    @GetMapping("/system_properties")
    public ResponseEntity<Map<Object, Object>> getSystemProperties() {
        Map<Object, Object> properties = new HashMap<>(System.getProperties());
        return new ResponseEntity<>(properties, HttpStatus.OK); // SINK
    }

    @GetMapping("/system_env")
    public ResponseEntity<Map<String, Object>> getEnvironmentProperties() {
        Map<String, Object> properties = new HashMap<>(System.getenv());
        return new ResponseEntity<>(properties, HttpStatus.OK); // SINK
    }


}
