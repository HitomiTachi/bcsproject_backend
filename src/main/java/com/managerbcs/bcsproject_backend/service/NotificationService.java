package com.managerbcs.bcsproject_backend.service;
import com.managerbcs.bcsproject_backend.dao.NotificationRepository;
import com.managerbcs.bcsproject_backend.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Optional<Notification> updateNotification(Integer id, Notification updated) {
        return notificationRepository.findById(id).map(existing -> {
            existing.setClassId(updated.getClassId());
            existing.setSenderId(updated.getSenderId());
            existing.setTitle(updated.getTitle());
            existing.setContent(updated.getContent());
            existing.setAttachment(updated.getAttachment());
            return notificationRepository.save(existing);
        });
    }

    public boolean deleteNotification(Integer id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
