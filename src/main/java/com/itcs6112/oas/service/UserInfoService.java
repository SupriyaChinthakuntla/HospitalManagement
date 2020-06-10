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

    public UserInfo findByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    public UserInfo findById(Integer id) {
        return userInfoRepository.findById(id).orElse(null);
    }

    public Iterable<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    public void saveUser(UserInfo user) {
        userInfoRepository.save(user);
    }
	public String getinfostring(UserInfo user){
        // UserInfo user = this.findById(id);
		return user.getFname() + " " + user.getLname() + " " + user.getEmail() ;
    }
    
}
