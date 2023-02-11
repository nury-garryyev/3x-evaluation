package io.mend.sast.controller.cwe;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.mend.sast.conf.JDBCConfiguration;
import io.mend.sast.model.User;
import io.mend.sast.service.UserSearchService;
import io.mend.sast.util.ApplicationConstants;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cwe565")
public class cwe565 {

    @GetMapping(value = "/get_cookie/{user_id}")
    public ResponseEntity<String> setCookieUnsafe(HttpServletRequest request, HttpServletResponse response, @PathVariable int user_id) throws Exception {
        User user = UserSearchService.getUserById(user_id);
        if (user == null){
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        
        Cookie user_cookie = new Cookie("user_id", String.valueOf(user_id));
        user_cookie.setPath("/");
        response.addCookie(user_cookie);
        
        Cookie role_cookie = new Cookie("user_role", String.valueOf(user.getRole()));
        role_cookie.setPath("/");
        response.addCookie(role_cookie);

        return new ResponseEntity<String>(user.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/unsafe/get_user")
    public ResponseEntity<String> getUserSafe(
            HttpServletRequest request, 
            HttpServletResponse response, 
            @CookieValue(value="user_id") String user_id //SOURCE
        ) throws Exception {
        User user = UserSearchService.getUserById(Integer.parseInt(user_id));
        
        if (user == null){
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(user.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/unsafe/get_user2")
    public ResponseEntity<String> getUserSafe2( HttpServletRequest request, HttpServletResponse response) throws Exception {
        String user_id = "";
        Cookie[] cookies = request.getCookies();
        
        for (Cookie c : cookies) {
            if (c.getName().equals("user_id")) {
                user_id = c.getValue();
            }
        }
        
        User user = UserSearchService.getUserById(Integer.parseInt(user_id));
        
        if (user == null){
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(user.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/unsafe/create_user")
    public String createUserUnsafe(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int user_id = 0;
        int user_role = 0;
        Cookie[] cookies = request.getCookies();
        
        for (Cookie c : cookies) {
            if (c.getName().equals("user_role")) {
                user_role = Integer.parseInt(c.getValue());
            }
            if (c.getName().equals("user_id")){
                user_id = Integer.parseInt(c.getValue());
            }
        }
        
        if (user_role != ApplicationConstants.ADMIN_ROLE){ // SINK
            response.setStatus(401);
            return "not_allowed"; 
        }
        request.setAttribute("user", UserSearchService.getUserById(user_id));
        return "create_user";
    }

    @GetMapping(value = "/unsafe/get_user3")
    public String getUserUnsafe3(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String user_id = "";
        Cookie[] cookies = request.getCookies();
        
        for(Cookie c : cookies) {
            if(c.getName().equals("user_id")){
                user_id = c.getValue();
                System.out.println(user_id);
            }
        }

        String sqlQuery = "SELECT * FROM \"user\" WHERE id=" + user_id;
        System.out.println(user_id);
        System.out.println(sqlQuery);
        Statement statement = JDBCConfiguration.getJDBCConnection().createStatement();
        
        ResultSet rs = statement.executeQuery(sqlQuery); // SINK
        ResultSetMetaData metaData = rs.getMetaData();
        List<String> results = new ArrayList<>();
        while(rs.next()){
            int i = 1;
            while(i<=metaData.getColumnCount()){
                results.add(rs.getString(i++));
            }
        }
        request.setAttribute("results", results);
        return "user_info";
    }
}