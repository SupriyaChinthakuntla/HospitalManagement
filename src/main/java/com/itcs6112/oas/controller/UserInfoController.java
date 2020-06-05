package com.itcs6112.oas.controller;

// import java.sql.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itcs6112.oas.repository.UserInfoRepository;
import com.itcs6112.oas.model.UserInfo;

@Controller 
@RequestMapping(path = "/users") 
public class UserInfoController {
    
    @Autowired 
    private UserInfoRepository user_info_repository;


    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewPatient(@RequestBody Map<String, Object> request_body){
        
        if(this.parse_request_body(request_body))
            user_info_repository.save(this.create_new_user(request_body));
        else
            return "Received malformed request";
        return "Receieved request to add new user";
    }
    
    @GetMapping(path = "/search")
    public @ResponseBody UserInfo searchForUser(@RequestBody Map<String, Object> request_body) {
        String email = (String) request_body.get("email");
        for (UserInfo u: user_info_repository.findAll()){
            if (u.getEmail().equals(email))
                return u;
        }
        return (UserInfo) null;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<UserInfo> getAllUsers() {
        return user_info_repository.findAll();
    }

    private UserInfo create_new_user(Map<String,Object>request_body){
        UserInfo u = new UserInfo();
        u.setEmail((String)request_body.get("email"));
        u.setFname((String)request_body.get("fname"));
        u.setLname((String)request_body.get("lname"));
        u.setRole((String)request_body.get("role"));
        u.setPassword((String)request_body.get("password"));
        return u;
    }

    private boolean parse_request_body(Map<String,Object>request_body){
        String [] l = {"email","fname","lname","role","password"};
        for (String k : l)
            if(!request_body.containsKey(k))
                return false;
        return true;
    }


}