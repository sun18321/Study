package com.sun.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.sun.mystudy.MainActivity;


public class RstartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d("broadcast", "收到广播");


        Intent mStartActivity = new Intent(context, MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, mPendingIntent);
        android.os.Process.killProcess(android.os.Process.myPid());



//        Intent intentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
//        intentForPackage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(intentForPackage);
    }
}
