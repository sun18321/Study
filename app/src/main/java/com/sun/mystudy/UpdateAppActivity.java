package com.sun.mystudy;

import android.app.DownloadManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.sun.bean.UpdateBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateAppActivity extends AppCompatActivity {

    final String url = "http://192.168.16.41:8080/verson.txt";
    private long lastDownloadId = 0;
    private MaterialDialog mMaterialDialog;
    public static Uri CONTENT_URL = Uri.parse("content://downloads/my_downloads");
    public static final int DOWNLOAD = 1002;
    public String download_url;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DOWNLOAD:
                    downloadApk(download_url);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_app);

        internet();
    }

    private void internet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient http_update = new OkHttpClient.Builder().connectTimeout(3000, TimeUnit.MILLISECONDS).build();
                Request request = new Request.Builder().get().url(url).build();
                try {
                    Response execute = http_update.newCall(request).execute();
                    String string = execute.body().string();
                    Gson gson = new Gson();
                    UpdateBean updateBean = gson.fromJson(string, UpdateBean.class);
                    System.out.println("新版本" + updateBean.getNewestVerson());
                    if ("2".equals(updateBean.getNewestVerson())) {
                        System.out.println("下载地址:" + updateBean.getUrl());
//                        downloadApk(updateBean.getUrl());

                        download_url = updateBean.getUrl();
                        mHandler.sendEmptyMessage(DOWNLOAD);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void downloadApk(String url) {
        if (mMaterialDialog == null) {
            mMaterialDialog = new MaterialDialog.Builder(UpdateAppActivity.this)
                    .title("dialog版本升级")
                    .content("dialog正在下载")
                    .progress(false, 100, false)
                    .cancelable(false)
                    .show();

        }


        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "news.apk");
        request.setTitle("我的新下载");
        request.setDescription("下载的新功能");
        request.setMimeType("application/vnd.android.package-archive");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.allowScanningByMediaScanner();
        request.setVisibleInDownloadsUi(true);
        lastDownloadId = downloadManager.enqueue(request);
        DownloadChangeObserve downloadObserve = new DownloadChangeObserve(null);
        getContentResolver().registerContentObserver(CONTENT_URL, true, downloadObserve);
    }

    class DownloadChangeObserve extends ContentObserver {
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public DownloadChangeObserve(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(lastDownloadId);
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            Cursor cursor = downloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                int totalColumn = cursor.getColumnIndex(downloadManager.COLUMN_TOTAL_SIZE_BYTES);
                int currentColumn = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                int totalSize = cursor.getInt(totalColumn);
                int currentSize = cursor.getInt(currentColumn);
                float parcent = (float) currentSize / (float) totalSize;
                int progress = Math.round(parcent * 100);
                mMaterialDialog.setProgress(progress);
                if (progress == 100) {
                    mMaterialDialog.dismiss();
                }

            }

        }
    }

}


