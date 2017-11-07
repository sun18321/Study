package com.sun.okhttp;

import android.util.Log;

import com.sun.application.MyApplication;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by sun on 2017/10/27.
 */

public class CookiesManager implements CookieJar {
    private final PersistentCookieStore cookieStore = new PersistentCookieStore(MyApplication.mContext);
    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (list != null && list.size() > 0) {
            for (Cookie cookie : list) {
                cookieStore.add(httpUrl, cookie);
                Log.d("company", "addcookie" + cookie);
            }
        } else {
            Log.d("company", "list是空的");
        }

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> cookies = cookieStore.get(httpUrl);
        return cookies;
    }
}
