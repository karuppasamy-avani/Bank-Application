package com.bankofindia.user_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
	private Long userId;
	
	private String username;
	
	private String emailId;
	
	private String contactNumber;
}
