package com.bankofindia.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankofindia.user_service.model.dto.UserDto;
import com.bankofindia.user_service.model.entity.User;
import com.bankofindia.user_service.model.response.Response;
import com.bankofindia.user_service.service.UserService;

@RestController
@RequestMapping("/boi/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createUser(@RequestBody UserDto user){
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{contactNumber}")
	public ResponseEntity<Response> updateUser(@PathVariable String contactNumber, @RequestBody UserDto userDto){
		return new ResponseEntity<>(userService.updateUser(contactNumber, userDto), HttpStatus.OK);
		
	}
	
//	@GetMapping("/")
//	public ResponseEntity<String> readUserByAccountId(){
//		return null;
//		
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Boolean> fetchUserById(@PathVariable("id") Long userId){
		return ResponseEntity.ok(userService.fetchUserById(userId));
	}
	
	
}
