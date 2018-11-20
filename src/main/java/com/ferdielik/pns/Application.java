package com.ferdielik.pns;

import com.ferdielik.pns.notification.Notification;
import com.ferdielik.pns.notification.NotificationCategory;
import com.ferdielik.pns.service.NotificationService;
import com.ferdielik.pns.service.impl.ApplePushNotificationServiceImpl;

public class Application
{
    public static void main(String[] args)
    {
        testAppleNotification();
    }

    private static void testAppleNotification()
    {
        String deviceToken = "7f5efa476a8ac25bc1fe48488520724835c86aa4d3fe50ad5a2975650a1ad0c1";
        NotificationService notificationService = ApplePushNotificationServiceImpl.getInstance();
        Notification notification = buildTestNotification();
        notificationService.push(deviceToken, notification);
    }

    private static Notification buildTestNotification()
    {
        Notification notification = new Notification();
        notification.setTitle("Apple Push Notification Service");
        notification.setSubTitle("Lorem simply dummy");
        notification.setBody("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        notification.setCategory(NotificationCategory.DEFAULT);
        return notification;
    }
}
