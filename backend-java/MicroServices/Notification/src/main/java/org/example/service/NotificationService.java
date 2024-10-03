package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Notification;
import org.example.domain.dto.NotificationRequest;
import org.example.domain.dto.NotificationResponse;
import org.example.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationResponse> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream().map(this::mapToNotificationResponse).toList();
    }

    private NotificationResponse mapToNotificationResponse(Notification notification) {
        return NotificationResponse.builder()
                .notificationId(notification.getId())
                .from(notification.getFrom())
                .to(notification.getTo())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .build();
    }

    @Override
    public void addNotification(NotificationRequest notificationRequest) {
        Notification newNotification = Notification.builder()
                .from(notificationRequest.getFrom())
                .to(notificationRequest.getTo())
                .subject(notificationRequest.getSubject())
                .message(notificationRequest.getMessage())
                .build();
        notificationRepository.save(newNotification);
    }
}
