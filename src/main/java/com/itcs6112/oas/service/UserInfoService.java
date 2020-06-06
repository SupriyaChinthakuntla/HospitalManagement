package com.itcs6112.oas.service;

import com.itcs6112.oas.model.UserInfo;
import com.itcs6112.oas.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoService {

	private UserInfoRepository userInfoRepository;
	
	@Autowired
	public UserInfoService(@Qualifier("userInfoRepository") UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}
	
	public UserInfo findByEmail(String email) {
		return userInfoRepository.findByEmail(email);
	}
	
	public void saveUser(UserInfo user) {
		userInfoRepository.save(user);
	}

	public Iterable<UserInfo> findAll() {
		return userInfoRepository.findAll();
	}
}
