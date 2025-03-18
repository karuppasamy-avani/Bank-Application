package com.bankofindia.transaction_service.model.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
	private String responseCode;
	private String message;
	private Object data;
}
