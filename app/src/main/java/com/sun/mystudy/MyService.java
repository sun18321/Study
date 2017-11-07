package com.sun.mystudy;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by sun on 2017/10/16.
 */

public class MyService extends IntentService {
    public MyService() {
        super("hehe");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
