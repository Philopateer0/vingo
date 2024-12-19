package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Notification;
import com.example.demo.services.NotificationService;


@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Notification>> getUserNotification(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(notificationService.getNotificationsByUserId(id));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> sendNotification(@PathVariable("userId") long userId)
        
    }
    
    @PutMapping("/id")
    public ResponseEntity<Notification> markAsRead(@PathVariable("id") long id) {
        try {
            Notification markedAsRead = notificationService.getNotificationById(id);
            markedAsRead.setRead(true);
            return ResponseEntity.ok(notificationService.updateNotification(id, markedAsRead));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(404).body(null);
        }
    }
}
