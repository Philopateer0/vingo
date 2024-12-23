package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Notification;
import com.example.demo.models.User;
import com.example.demo.repositories.NotificationRepository;
import com.example.demo.repositories.UserInfoRepository;

@RestController
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserInfoRepository userRepository;

    @PostMapping("notification")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}/notification")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/notification/{id}")
    public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long userId, @PathVariable Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isEmpty() || notification.get().getUser().getId() != userId) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notification.get().setRead(true);
        notificationRepository.save(notification.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/{userId}/notification/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long userId, @PathVariable Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isEmpty() || notification.get().getUser().getId() != userId) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notificationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("/user/{userId}/notification/{id}")
    public ResponseEntity<Notification> sendNotification(@PathVariable Long userId, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Notification> notification = notificationRepository.findById(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (notification.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        notification.get().setUser(user.get());
        Notification savedNotification = notificationRepository.save(notification.get());
        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }
}
