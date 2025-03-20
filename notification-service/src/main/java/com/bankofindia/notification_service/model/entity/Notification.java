package com.bankofindia.notification_service.model.entity;

import java.time.LocalDateTime;

import com.bankofindia.notification_service.model.NotificationStatus;
import com.bankofindia.notification_service.model.NotificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    
    private String phoneNumber;
    
    private String message;
    
    private NotificationType notificationType; 
    
    private NotificationStatus notificationStatus;
    
    private LocalDateTime time;
}
