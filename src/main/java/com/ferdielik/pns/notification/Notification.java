package com.ferdielik.pns.notification;

public class Notification
{
    private int badge = 1; // The number to display in a badge on your app’s icon. Specify 0 to remove the current badge, if any.
    private String title="";
    private String subTitle ="";
    private String body="";
    private String sound = "default";

    private NotificationCategory category;

    public int getBadge()
    {
        return badge;
    }

    public void setBadge(int badge)
    {
        this.badge = badge;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSubTitle()
    {
        return subTitle;
    }

    public void setSubTitle(String subTitle)
    {
        this.subTitle = subTitle;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getSound()
    {
        return sound;
    }

    public void setSound(String sound)
    {
        this.sound = sound;
    }

    public NotificationCategory getCategory()
    {
        return category;
    }

    public void setCategory(NotificationCategory category)
    {
        this.category = category;
    }
}
