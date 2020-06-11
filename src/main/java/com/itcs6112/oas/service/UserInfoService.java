package com.itcs6112.oas.service;

import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
   
    private Iterable<UserInfo> users;
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo findByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    public UserInfo findById(Integer id) {
        for(UserInfo d : users)
            if(d.getId().equals(id))
                return d;
        return userInfoRepository.findById(id).orElse(null);
        // return userInfoRepository.findById(id).orElse(null);
    }
    
    public void fetchAllUsers() {
        this.users = userInfoRepository.findAll();
    }

    public Iterable<UserInfo> getAllUsers() {
        return this.users;
    }

    public void saveUser(UserInfo user) {
        userInfoRepository.save(user);
    }
	public String getinfostring(UserInfo user){
        // UserInfo user = this.findById(id);
		return user.getFname() + " " + user.getLname() + " " + user.getEmail() ;
    }
    
}
