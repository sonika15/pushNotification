package com.notification.Utility;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("code")
    int code;

    @SerializedName("status")
    String status;
}

