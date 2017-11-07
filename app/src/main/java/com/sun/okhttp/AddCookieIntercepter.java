package com.sun.okhttp;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sun on 2017/10/26.
 */

public class AddCookieIntercepter implements Interceptor {
    Context mContext;

    public AddCookieIntercepter(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        SharedPreferences cookie = mContext.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        HashSet<String> cookie_set = (HashSet) cookie.getStringSet("cookie", new HashSet<String>());
        for (String s : cookie_set) {
            builder.addHeader("Cookie", s);
            Log.d("company", "addCookie:" + s);
        }
        return chain.proceed(builder.build());
    }
}
