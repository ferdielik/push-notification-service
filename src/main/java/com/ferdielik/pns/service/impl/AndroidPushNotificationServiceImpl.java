package com.ferdielik.pns.service.impl;

import com.ferdielik.pns.notification.Notification;
import com.ferdielik.pns.payload.ApsPayloadBuilder;
import com.ferdielik.pns.payload.FcmPayloadBuilder;
import com.ferdielik.pns.service.NotificationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AndroidPushNotificationServiceImpl implements NotificationService
{
    // Method to send Notifications from server to client end.
    private final static String AUTH_KEY_FCM = "AUTH_KEY";
    private final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    private static AndroidPushNotificationServiceImpl instance;

    public static NotificationService getInstance()
    {
        if (instance == null)
        {
            instance = new AndroidPushNotificationServiceImpl();
        }
        return instance;
    }

    @Override
    public void push(String deviceToken, Notification notification)
    {

        FcmPayloadBuilder payloadBuilder = FcmPayloadBuilder.newPayload()
                .to(deviceToken)
                .alertTitle(notification.getTitle())
                .alertBody(notification.getBody());

        if (notification.hasCustomData())
        {
            payloadBuilder.customFields(notification.getCustomData());
        }

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL_FCM);

        try
        {
            StringEntity entity = new StringEntity(payloadBuilder.build());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "key=" + AUTH_KEY_FCM);

            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println(response);
            client.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
