package com.itcs6112.oas.service;

import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service("userInfoService")
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public boolean addNewUser(Map<String,Object> requestBody){
        return createNewUser(requestBody);
    }

    public UserInfo findByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    public Iterable<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    public void saveUser(UserInfo user) {
        userInfoRepository.save(user);
    }
    
    // returns true if a new user is successfully created and added to the database
    private boolean createNewUser(Map<String,Object>requestBody){
        // check to see if the request is properly formed, and create new user
        // Minimal error checking done
        if (checkNewUserRequest(requestBody)){
            UserInfo u = new UserInfo();
            u.setEmail((String)requestBody.get("email"));
            u.setFname((String)requestBody.get("fname"));
            u.setLname((String)requestBody.get("lname"));
            u.setRole((String)requestBody.get("role"));
            u.setPassword((String)requestBody.get("password"));
            this.userInfoRepository.save(u);
            // saveUser(u);
            System.out.println(u);
            return true;
        }
        return false;
    }
    // helper function to determine if new user request has all data fields
    private boolean checkNewUserRequest(Map<String,Object>requestBody){
        String [] l = {"email","fname","lname","role","password"};
        for (String k : l)
            if(!requestBody.containsKey(k))
                return false;
        return true;
    }
}
