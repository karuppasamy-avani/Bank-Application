package com.bankofindia.account_service.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class GetTheTimeAndDate {
	
    public String getFormattedTime(LocalDateTime time) {
    	DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return time.format(FORMATTER);
    }
}
