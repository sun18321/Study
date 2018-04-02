package com.sun.myclass;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.sun.mystudy.DownloadServiceActivity;
import com.sun.mystudy.R;

import java.io.File;

public class DownloadService extends Service {
    private DownloadAsyncTask mDownloadAsyncTask;
    private String downloadUrl;


    public DownloadService() {
    }


    private DownloadListener mListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            mDownloadAsyncTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadService.this,"Download success",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onFailed() {
            mDownloadAsyncTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            mDownloadAsyncTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Download Canceled",Toast.LENGTH_SHORT).show();
        }
    };

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, DownloadServiceActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

   public class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (mDownloadAsyncTask == null) {
                downloadUrl = url;
                mDownloadAsyncTask = new DownloadAsyncTask(mListener);
                mDownloadAsyncTask.execute(downloadUrl);
                startForeground(1, getNotification("Downloading...", 0));
                Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        }

       public void pauseDownload() {
           if (mDownloadAsyncTask != null) {
               mDownloadAsyncTask.pauseDownload();
           }
       }

       public void cancelDownload() {
           if (mDownloadAsyncTask != null) {
               mDownloadAsyncTask.cancelDownload();
           } else {
               if (downloadUrl != null) {
                   String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                   String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                   File file = new File(directory + fileName);
                   if (file.exists()) {
                       file.delete();
                   }
                   getNotificationManager().cancel(1);
                   stopForeground(true);
                   Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
               }
           }
       }
    }


    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
