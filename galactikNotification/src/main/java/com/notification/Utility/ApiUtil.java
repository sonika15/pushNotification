package com.notification.Utility;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class ApiUtil {
    public static final String MyPREFERENCES = "MyPrefs";

    private ApiUtil() {
    }

    public static final String BASE_URL = "https://transasia-api.galaktic.co:8443/web-b2c/";

    public static NetworkService getAPIService() {

        return RetrofitClass.getClient(BASE_URL).create(NetworkService.class);
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).getString("deviceToken", "");
    }

}
