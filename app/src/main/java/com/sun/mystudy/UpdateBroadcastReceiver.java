package com.sun.mystudy;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.net.URI;

/**
 * Created by sun on 2017/5/31.
 */


public class UpdateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long downLOadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        long cacheDownLoadId = sp.getLong("lastDownLoadId", -1);
        if (cacheDownLoadId == downLOadId) {
            Intent install = new Intent(Intent.ACTION_VIEW);
//            File apkFile = queryDownloadApk(context);
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadFileUri = downloadManager.getUriForDownloadedFile(downLOadId);
            install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");

            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }
    }

    public static File queryDownloadApk(Context context) {
        File targetApkFile = null;
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        long downLoadId = sp.getLong("lastDownLoadId", -1);
        if (downLoadId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downLoadId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cursor = downloadManager.query(query);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    String string = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(string)) {
                        targetApkFile = new File(Uri.parse(string).getPath());
                    }
                }
                cursor.close();
            }
        }
        return targetApkFile;
    }
}
