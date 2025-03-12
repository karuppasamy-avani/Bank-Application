package com.bankofindia.user_service.service;

import java.util.Optional;

import com.bankofindia.user_service.model.dto.UserDto;
import com.bankofindia.user_service.model.entity.User;
import com.bankofindia.user_service.model.response.Response;

public interface UserService {
	
	boolean fetchUserById(Long userId);

	Response createUser(UserDto user);

	Response updateUser(String contactNumber, UserDto userDto);
	
}
