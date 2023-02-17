package io.mend.sast.controller.cwe;

import io.mend.sast.model.Credentials;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/cwe400")
public class cwe400_redos {


    @PostMapping(value = "/regex")
    public void regex(@RequestBody Credentials credentials) {

        String regex = credentials.getUsername();
        String input = credentials.getPassword();

//        regex = "(h|h|ih(((i|a|c|c|a|i|i|j|b|a|i|b|a|a|j))+h)ahbfhba|c|i)*";
//        input = "hchcchicihcchciiicichhcichcihcchiihichiciiiihhcchicchhcihchcihiihciichhccciccichcichiihcchcihhicchcciicchcccihiiihhihihihi"+
//        "chicihhcciccchihhhcchichchciihiicihciihcccciciccicciiiiiiiiicihhhiiiihchccchchhhhiiihchihcccchhhiiiiiiiicicichicihcciciihichhhhchihciiihhiccccccciciihh"+
//        "ichiccchhicchicihihccichicciihcichccihhiciccccccccichhhhihihhcchchihihiihhihihihicichihiiiihhhhihhhchhichiicihhiiiiihchccccchichci";


        Pattern.matches(regex, input); // SINK
    }

    @PostMapping(value = "/regex2")
    public void regex2(HttpServletRequest request) {

        String regex = request.getParameter("regex");
        String input = request.getParameter("input");

        Pattern.matches(regex, input); // SINK
        input.matches(regex); // SINK
        input.replaceAll(regex,"replacement"); // SINK
        input.replaceFirst(regex,"replacement"); // SINK
        input.split(regex); // SINK
        input.split(regex, 1); // SINK

        input.matches(Pattern.quote(regex)); // SAFE
    }

    @GetMapping(value = "/regex3")
    public void regex3(HttpServletRequest request) {

        String regex = request.getParameter("regex");
        String input = request.getParameter("input");

        Pattern.compile(regex).matcher(input).matches(); // SINK

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.matches(); // SINK
    }
}
