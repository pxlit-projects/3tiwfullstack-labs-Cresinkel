package org.example.service;

import org.example.domain.dto.NotificationRequest;
import org.example.domain.dto.NotificationResponse;

import java.util.List;

public interface INotificationService {
    List<NotificationResponse> getAllNotifications();

    void addNotification(NotificationRequest notificationRequest);
}
