package com.itcs6112.oas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.UserInfoRepository;

@Service("userInfoService")
public class UserInfoService {

	private UserInfoRepository userInfoRepository;
	
	@Autowired
	public UserInfoService(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}
	
	public UserInfo findByEmail(String email) {
		return userInfoRepository.findByEmail(email);
	}
	
	public void saveUser(UserInfo user) {
		userInfoRepository.save(user);
	}
	
}
