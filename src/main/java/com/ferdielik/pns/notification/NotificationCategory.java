package com.ferdielik.pns.notification;

public enum NotificationCategory
{
    DEFAULT("default"),
    CUSTOM("custom");

    private String identifier; // The notification’s type. This string must correspond to the identifier of one of the UNNotificationCategory objects you register at launch time.

    NotificationCategory(String identifier)
    {
        this.identifier = identifier;
    }

    public String getIdentifier()
    {
        return identifier;
    }
}
