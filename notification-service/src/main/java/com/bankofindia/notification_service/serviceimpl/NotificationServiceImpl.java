package com.bankofindia.notification_service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bankofindia.notification_service.model.dto.NotificationDto;
import com.bankofindia.notification_service.repository.NotificationRepository;
import com.bankofindia.notification_service.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public void sendEmail(NotificationDto notificationDto, String eventType) {

		SimpleMailMessage message = new SimpleMailMessage();
		 message.setTo(notificationDto.getEmailId());
		 
		 switch(eventType) {
		 	case "ACCOUNT_CREATED":
		 		message.setSubject("Welcome to Bank Of India!");
		 		message.setText("Dear Customer, Your bank account has been successfully created." + "Your ACC"+ notificationDto.getAccountNumber());
	            break;
		 	default:
                break;
		 }
		 
		 mailSender.send(message);
		

	}

}
