package com.bankofindia.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankofindia.notification_service.model.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
