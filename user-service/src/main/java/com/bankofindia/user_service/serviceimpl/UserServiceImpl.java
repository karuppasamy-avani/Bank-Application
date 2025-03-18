package com.bankofindia.user_service.serviceimpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bankofindia.user_service.exception.ResourceNotFound;
import com.bankofindia.user_service.kafka.UserEventProducer;
import com.bankofindia.user_service.model.dto.UserDto;
import com.bankofindia.user_service.model.entity.User;
import com.bankofindia.user_service.model.response.Response;
import com.bankofindia.user_service.repository.UserRepository;
import com.bankofindia.user_service.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Value("${spring.application.ok}")
	private String success;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserEventProducer userEventProducer;

	@Override
	public Response createUser(UserDto userDto) {
		
		User user = new User();
		modelMapper.typeMap(UserDto.class, User.class).addMappings(mapper -> mapper.skip(User::setUserId));
		modelMapper.map(userDto, user);
		
		User savedUser = userRepo.save(user); 
		log.info("User saved: {}", user);
		
		userDto.setUserId(savedUser.getUserId());
		userEventProducer.sendUserCreatedEvent(userDto);
		
		return Response.builder()
				.responseCode(success)
				.message("User created successfully")
				.data(user)
				.build();
	}

	@Override
	public boolean fetchUserById(Long userId) {
		return userRepo.existsById(userId);
	}

	@Override
	public Response updateUser(String contactNumber, UserDto userDto) {
		User user = userRepo.findByContactNumber(contactNumber)
				.orElseThrow(() -> new ResourceNotFound("User not found"));

	    // Configure ModelMapper to skip userId
	    modelMapper.typeMap(UserDto.class, User.class).addMappings(mapper -> 
	        mapper.skip(User::setUserId));

	    // Manually map non-null fields
	    modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.map(userDto, user);

		userRepo.save(user);
		return Response.builder()
				.responseCode(success)
				.message("User created successfully")
				.data(user)
				.build();
	}

}
