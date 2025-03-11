package com.bankofindia.account_service.model.externaldto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	private String username;
	
	private String emailId;
	
	private String contactNumber;
}