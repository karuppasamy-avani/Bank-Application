package com.bankofindia.notification_service.service;

import com.bankofindia.notification_service.model.dto.NotificationDto;

public interface NotificationService {
	void sendEmail(NotificationDto notificationDto, String eventType);
}
