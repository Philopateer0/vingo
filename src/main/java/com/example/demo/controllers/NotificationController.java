//this new
package com.example.demo.controllers;

import com.example.demo.models.Notification;
import com.example.demo.models.User;
import com.example.demo.services.UserRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
public class NotificationController {
    private final UserRegistrationService userService;
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    public NotificationController(UserRegistrationService userService) {
        this.userService = userService;
    }
    @PutMapping("/user/{userId}/notification/{id}")
    public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long userId, @PathVariable Long id) {
        User user = userService.getUserById(userId.intValue());
        if (user == null) {
            logger.warn("Could not find user with ID: {}", userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Notification> notification = user.getNotifications()
                .stream()
                .filter(n -> n.getId() == id.intValue())
                .findFirst();
        if (notification.isPresent()) {
            notification.get().setRead(true);
            logger.info("Notification {} marked as read for user {}", id, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.warn("Notification {} not found for user {}", id, userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}