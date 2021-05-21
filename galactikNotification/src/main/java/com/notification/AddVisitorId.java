package com.notification;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import com.notification.Utility.ApiResponse;
import com.notification.Utility.ApiUtil;
import com.notification.Utility.DeviceSubscribe;
import com.notification.Utility.NetworkService;
import com.notification.Utility.Subscription;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddVisitorId extends Application {
    String deviceToken = "";
    String visitorId;
    Context context;

    public AddVisitorId(Application application) {
        this.context = application;
    }

    public void setVisitorId(String visitorId) {
        Log.wtf("visitorId", visitorId);
        this.visitorId = visitorId;
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                deviceToken = ApiUtil.getToken(context);
                Log.wtf("deviceToken", deviceToken);

                if (!deviceToken.equals("")) {
                    Log.wtf("deviceToken", deviceToken);
                    sendPost();
                } else {
                    Log.wtf("deviceToken", "empty hai ");
                }
            }
        }, 5000);
    }

    public void sendPost() {
        Log.wtf("deviceToken", deviceToken);
        NetworkService service = ApiUtil.getAPIService();
        Subscription subscription = new Subscription(deviceToken);
        DeviceSubscribe deviceSubscribe = new DeviceSubscribe(visitorId, subscription, "android");
        service.savePost(deviceSubscribe).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    //showResponse(response.body().toString());
                    Log.wtf("post submitted to API.", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.wtf("failure", "Unable to submit post to API.");
            }
        });
    }

}
