package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.NotificationRequest;
import org.example.service.INotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final INotificationService notificationService;

    @GetMapping
    public ResponseEntity getAllNotifications() {
        return new ResponseEntity(notificationService.getAllNotifications(), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNotification(@RequestBody NotificationRequest notificationRequest) {
         notificationService.addNotification(notificationRequest);
    }
//    @GetMapping("/{id}") findById
//    @GetMapping("/department/{departmentId}") findByDepartment
//    @GetMapping("/organization/{organizationId}") findByOrganization

}
