package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Notification;
import com.example.demo.repositories.NotificationRepository;

public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

        public Notification updateNotification(Long id, Notification notificationDetails) {
        Notification notification = getNotificationById(id);

        notification.setMessage(notificationDetails.getMessage());
        notification.setUserId(notificationDetails.getUserId());
        notification.setRead(notificationDetails.getRead());

        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        Notification notification = getNotificationById(id);
        notificationRepository.delete(notification);
    }
}
