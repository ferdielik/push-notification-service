package com.ferdielik.pns.service.impl;

import java.io.FileInputStream;

import com.ferdielik.pns.notification.Notification;
import com.ferdielik.pns.payload.ApsPayloadBuilder;
import com.ferdielik.pns.service.NotificationService;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;

public class ApplePushNotificationServiceImpl implements NotificationService
{
    private static final String APS_CERT_FILE = "";
    private static final String APS_CERT_PASSWORD = "";

    private ApnsService apnsService;

    private static ApplePushNotificationServiceImpl instance;

    public static NotificationService getInstance()
    {
        if (instance == null)
        {
            instance = new ApplePushNotificationServiceImpl();
            instance.init();
        }
        return instance;
    }

    private void init()
    {
        try
        {
            String certUrl = this.getClass().getClassLoader().getResource(APS_CERT_FILE).getFile();
            FileInputStream fileInputStream = new FileInputStream(certUrl);

            ApnsServiceBuilder serviceBuilder = APNS.newService()
                    .withCert(fileInputStream, APS_CERT_PASSWORD);

            // serviceBuilder.withProductionDestination(); // For production
            serviceBuilder.withSandboxDestination();
            apnsService = serviceBuilder.build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void push(String deviceToken, Notification notification)
    {

        ApsPayloadBuilder payloadBuilder = ApsPayloadBuilder.newPayload()
                .badge(notification.getBadge())
                .sound(notification.getSound())
                .alertTitle(notification.getTitle())
                .alertBody(notification.getBody())
                .alertSubtitle(notification.getSubTitle());


        if (notification.getCategory() != null)
        {
            payloadBuilder.customField("category", notification.getCategory().getIdentifier());
        }

        if (notification.hasCustomData())
        {
            payloadBuilder.customFields(notification.getCustomData());
        }

        apnsService.push(deviceToken, payloadBuilder.build());
    }
}
