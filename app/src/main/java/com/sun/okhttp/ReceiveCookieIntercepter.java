package com.sun.okhttp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by sun on 2017/10/26.
 */

public class ReceiveCookieIntercepter implements Interceptor {
    public static final String SET_COOKIE = "Set-Cookie";

    Context mContext;
    private HashSet<String> mCookie_set;

    public ReceiveCookieIntercepter(Context context) {
        mContext = context;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers(SET_COOKIE).isEmpty()) {
            mCookie_set = new HashSet<>();
            List<String> list = response.headers(SET_COOKIE);
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
//                mCookie_set.add(s);
                sb.append(s);
                Log.d("company", "receiveCookie:" + s);
            }
            String[] split = sb.toString().split(";");
            Log.d("company", "split" + split[0]);
            mCookie_set.add(split[0]);
        }
        SharedPreferences sp = mContext.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet("cookie", mCookie_set).apply();
        return response;
    }
}
