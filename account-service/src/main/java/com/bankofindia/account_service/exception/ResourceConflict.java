package com.bankofindia.account_service.exception;


public class ResourceConflict extends RuntimeException {
	public ResourceConflict(String message) {
		super(message);
	}
}
