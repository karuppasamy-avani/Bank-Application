package com.bankofindia.account_service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bankofindia.account_service.model.externaldto.UserDto;

@FeignClient(name = "user-service")
public interface UserService {
	
	@GetMapping("/boi/user/{id}")
	ResponseEntity<Boolean> fetchUserById(@PathVariable Long userId);
}
