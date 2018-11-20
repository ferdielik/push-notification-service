This github repo is implementation of [com.notnoop.apns.ApnsService](https://github.com/notnoop/java-apns). 

Firstly you need create aps certification [here](https://developer.apple.com/documentation/usernotifications/setting_up_a_remote_notification_server/establishing_a_certificate-based_connection_to_apns).
After that install certificate and export p12 file with Keychain app.

And change password and cert file in ApplePushNotificationServiceImpl;


send a push ios notification

``` 
private static String deviceToken = "7f5efa476a8ac25bc1fe48488520724835c86aa4d3fe50ad5a2975650a1ad0c1";

private static void testAppleNotification()
{
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
 ``` 