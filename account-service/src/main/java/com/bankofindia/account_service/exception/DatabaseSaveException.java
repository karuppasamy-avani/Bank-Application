package com.bankofindia.account_service.exception;

public class DatabaseSaveException extends RuntimeException{
	public DatabaseSaveException(String message) {
		super(message);
	}
}
