package com.itcs6112.oas.repository;

import com.itcs6112.oas.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userInfoRepository")
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
	UserInfo findByEmail(String email);
}