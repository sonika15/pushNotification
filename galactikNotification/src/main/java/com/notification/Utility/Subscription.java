package com.notification.Utility;

import com.google.gson.annotations.SerializedName;

public class Subscription {
    @SerializedName("deviceToken")
    private String deviceToken;

    public Subscription(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
