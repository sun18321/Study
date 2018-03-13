package com.sun.myclass;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private final String LOG_SERVICE = "service_test";
    private AlarmManager mAlarmManager;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private MyBind mMyBind = new MyBind();

    public MyService() {
    }

    public class MyBind extends Binder {

        public void startBind() {
            Log.d(LOG_SERVICE, "startbind");
        }

        public int getProgress() {
            Log.d(LOG_SERVICE, "bindprogress");
            return 4;
        }

    }



    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(LOG_SERVICE, "oncreate");

        mTimer = new Timer(true);
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(LOG_SERVICE, "timer延时");
            }
        };

        mTimer.schedule(mTimerTask, 5000);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(LOG_SERVICE, "onstartcommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(LOG_SERVICE, "ondestroy");

    }


    @Override
    public IBinder onBind(Intent intent) {
        return mMyBind;
    }
}
