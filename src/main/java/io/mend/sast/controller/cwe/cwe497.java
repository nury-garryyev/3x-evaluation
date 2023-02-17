package io.mend.sast.controller.cwe;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/system_property1")
    public ResponseEntity<String> getSystemProperties1() {
        String p = System.getProperty("os.name");
        return new ResponseEntity<String>(p, HttpStatus.OK); // SINK
    }

    @GetMapping("/system_property2")
    public ResponseEntity<String> getSystemProperties2() {
        String p = System.getProperty("os.name", "default");
        return new ResponseEntity<String>(p, HttpStatus.OK); // SINK
    }

    @GetMapping("/system_env1")
    public ResponseEntity<String> getEnvironmentProperties1() {
        String p = System.getenv("PATH");
        return new ResponseEntity<String>(p, HttpStatus.OK); // SINK
    }
}
