package com.itcs6112.oas.repository;

import java.util.Optional;

import com.itcs6112.oas.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userInfoRepository")
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
	UserInfo findByEmail(String email);
	Optional<UserInfo> findById(Integer id);
}