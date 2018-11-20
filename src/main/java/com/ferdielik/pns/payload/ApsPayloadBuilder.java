package com.ferdielik.pns.payload;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * original: com.notnoop.apns.PayloadBuilder
 * <p>
 * PayloadBuilder doesn't allow custom alert title, subtitle and body unfortunately
 * therefore we use custom implementation
 */

public final class ApsPayloadBuilder
{
    private static final Gson gson = new Gson();

    private final Map<String, Object> root;
    private final Map<String, Object> aps;
    private final Map<String, Object> customAlert;

    ApsPayloadBuilder()
    {
        this.root = new HashMap<String, Object>();
        this.aps = new HashMap<String, Object>();
        this.customAlert = new HashMap<String, Object>();
    }

    public ApsPayloadBuilder alertBody(String alert)
    {
        this.customAlert.put("body", alert);
        return this;
    }

    public ApsPayloadBuilder alertTitle(String alert)
    {
        this.customAlert.put("title", alert);
        return this;
    }

    public ApsPayloadBuilder alertSubtitle(String alert)
    {
        this.customAlert.put("subtitle", alert);
        return this;
    }


    public ApsPayloadBuilder sound(String sound)
    {
        this.aps.put("sound", sound);
        return this;
    }

    public ApsPayloadBuilder badge(int badge)
    {
        this.aps.put("badge", badge);
        return this;
    }

    public ApsPayloadBuilder clearBadge()
    {
        return this.badge(0);
    }

    public ApsPayloadBuilder actionKey(String actionKey)
    {
        this.customAlert.put("action-loc-key", actionKey);
        return this;
    }

    public ApsPayloadBuilder noActionButton()
    {
        return this.actionKey((String) null);
    }

    public ApsPayloadBuilder localizedKey(String key)
    {
        this.customAlert.put("loc-key", key);
        return this;
    }

    public ApsPayloadBuilder localizedArguments(Collection<String> arguments)
    {
        this.customAlert.put("loc-args", arguments);
        return this;
    }

    public ApsPayloadBuilder localizedArguments(String... arguments)
    {
        return this.localizedArguments((Collection<String>) Arrays.asList(arguments));
    }

    public ApsPayloadBuilder launchImage(String launchImage)
    {
        this.customAlert.put("launch-image", launchImage);
        return this;
    }

    public ApsPayloadBuilder customField(String key, Object value)
    {
        this.root.put(key, value);
        return this;
    }

    public ApsPayloadBuilder customFields(Map<String, ? extends Object> values)
    {
        this.root.putAll(values);
        return this;
    }


    public String build()
    {
        if (!this.customAlert.isEmpty() && !this.customAlert.equals(this.aps.get("alert")))
        {
            if (this.aps.containsKey("alert"))
            {
                String alertBody = (String) this.aps.get("alert");
                this.customAlert.put("body", alertBody);
            }

            this.aps.put("alert", this.customAlert);
        }

        this.root.put("aps", this.aps);

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

    private ApsPayloadBuilder(Map<String, Object> root, Map<String, Object> aps, Map<String, Object> customAlert)
    {
        this.root = new HashMap<String, Object>(root);
        this.aps = new HashMap<String, Object>(aps);
        this.customAlert = new HashMap<String, Object>(customAlert);
    }

    public ApsPayloadBuilder copy()
    {
        return new ApsPayloadBuilder(this.root, this.aps, this.customAlert);
    }

    public static ApsPayloadBuilder newPayload()
    {
        return new ApsPayloadBuilder();
    }
}