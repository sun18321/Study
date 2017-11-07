package com.sun.myinterface;

import android.util.Log;

/**
 * Created by sun on 2017/10/30.
 */

public class InterfaceClass implements TestInterface {
    public static final String LOG_INTERFACE = "test_interface";

    @Override
    public void first() {
        Log.d(LOG_INTERFACE, "first方法");
    }

    @Override
    public void second(boolean b) {
        Log.d(LOG_INTERFACE, "second里的" + b);
    }
}
