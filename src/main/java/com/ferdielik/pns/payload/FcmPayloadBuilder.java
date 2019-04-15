package com.ferdielik.pns.payload;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * original: com.notnoop.apns.PayloadBuilder
 * <p>
 * PayloadBuilder doesn't allow custom alert title, subtitle and body unfortunately
 * therefore we use custom implementation
 */

public final class FcmPayloadBuilder
{
    private static final Gson gson = new Gson();

    private final Map<String, Object> root;
    private final Map<String, Object> data;
    private final Map<String, Object> notification;

    FcmPayloadBuilder()
    {
        this.root = new HashMap<String, Object>();
        this.data = new HashMap<String, Object>();
        this.notification = new HashMap<String, Object>();
    }

    public FcmPayloadBuilder alertBody(String alert)
    {
        this.notification.put("body", alert);
        return this;
    }

    public FcmPayloadBuilder alertTitle(String alert)
    {
        this.notification.put("title", alert);
        return this;
    }

    public FcmPayloadBuilder alertSubtitle(String alert)
    {
        this.notification.put("subtitle", alert);
        return this;
    }


    public FcmPayloadBuilder sound(String sound)
    {
        this.root.put("sound", sound);
        return this;
    }

    public FcmPayloadBuilder category(String category)
    {
        this.root.put("category", category);
        return this;
    }

    public FcmPayloadBuilder contentAvailable(boolean available)
    {
        this.root.put("content-available", available ? 1 : 0);
        return this;
    }

    public FcmPayloadBuilder mutableContent(boolean available)
    {
        this.root.put("mutable-content", available ? 1 : 0);
        return this;
    }

    public FcmPayloadBuilder badge(int badge)
    {
        this.root.put("badge", badge);
        return this;
    }

    public FcmPayloadBuilder to(String token)
    {
        this.root.put("to", token);
        return this;
    }

    public FcmPayloadBuilder clearBadge()
    {
        return this.badge(0);
    }

    public FcmPayloadBuilder actionKey(String actionKey)
    {
        this.notification.put("action-loc-key", actionKey);
        return this;
    }

    public FcmPayloadBuilder noActionButton()
    {
        return this.actionKey((String) null);
    }

    public FcmPayloadBuilder localizedKey(String key)
    {
        this.notification.put("loc-key", key);
        return this;
    }

    public FcmPayloadBuilder localizedArguments(Collection<String> arguments)
    {
        this.notification.put("loc-args", arguments);
        return this;
    }

    public FcmPayloadBuilder localizedArguments(String... arguments)
    {
        return this.localizedArguments((Collection<String>) Arrays.asList(arguments));
    }

    public FcmPayloadBuilder launchImage(String launchImage)
    {
        this.notification.put("launch-image", launchImage);
        return this;
    }

    public FcmPayloadBuilder customField(String key, Object value)
    {
        this.data.put(key, value);
        return this;
    }

    public FcmPayloadBuilder customFields(Map<String, ? extends Object> values)
    {
        this.data.putAll(values);
        return this;
    }


    public String build()
    {
        this.root.put("data", this.data);
        this.root.put("notification", this.notification);

        try
        {
            return gson.toJson(this.root);
        }
        catch (Exception var2)
        {
            throw new RuntimeException(var2);
        }
    }


    public String toString()
    {
        return this.build();
    }

    private FcmPayloadBuilder(Map<String, Object> root, Map<String, Object> aps, Map<String, Object> notification)
    {
        this.root = new HashMap<String, Object>(root);
        this.data = new HashMap<String, Object>(aps);
        this.notification = new HashMap<String, Object>(notification);
    }

    public FcmPayloadBuilder copy()
    {
        return new FcmPayloadBuilder(this.root, this.root, this.notification);
    }

    public static FcmPayloadBuilder newPayload()
    {
        return new FcmPayloadBuilder();
    }
}