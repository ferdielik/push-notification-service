package com.ferdielik.pns.service;

import com.ferdielik.pns.notification.Notification;

public interface NotificationService
{
    void push(String deviceToken, Notification notification);
}
