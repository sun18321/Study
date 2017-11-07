package com.sun.mystudy;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.sun.application.MyApplication;
import com.sun.bean.WorkUpdateBean;
import com.sun.okhttp.CookiesManager;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WorkUpdateActivity extends AppCompatActivity {
    private String LOGIN_URL = "http://phone.hainantaohua.com/index/login";
    public static Uri CONTENT_URL = Uri.parse("content://downloads/my_downloads");
    private ProgressDialog mProgressDialog;
    private long lastDownloadId = 0;
    private MaterialDialog mMaterialDialog;
    private final int DOWN_APK = 1002;
    private final String SERVER_URL = "http://phone.hainantaohua.com/";
    private final String getLiveListUrl = SERVER_URL + "live/hot";

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DOWN_APK:
                    mProgressDialog = new ProgressDialog(WorkUpdateActivity.this);
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mProgressDialog.setTitle("正在更新");
                    mProgressDialog.show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_update);

        imitateLogin();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookiesManager());

//        checkUpdate();

        initNewCookie();
        findViewById(R.id.btn_live).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(new CookiesManager()).build();
                Request request = new Request.Builder().url(getLiveListUrl).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("company", "new_live" + response.body().string());
                    }
                });

            }
        });
    }

    private void initNewCookie() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(new CookiesManager()).build();
        FormBody formBody = new FormBody.Builder().add("account", "18555556688")
                .add("pwd", "bfd59291e825b5f2bbf1eb76569f8fe7")
                .build();
        Request request = new Request.Builder().post(formBody).url(LOGIN_URL).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("company", "newcookie" + response.body().string());
            }
        });

    }

    private void imitateLogin() {

//        OkHttpClient cookie_client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                System.out.println("cook_url" + url);
////                for (int i = 0; i < cookies.size(); i++) {
////                    System.out.println(cookies.get(i).name());
////                    System.out.println(cookies.get(i).value());
////                }
//
//            }
//
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                return null;
//            }
//        }).build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
                Cookie cookie = cookies.get(0);
                String value = cookie.value();
                System.out.println("value----" + value);//这个就是cookie

                getSharedPreferences("data", MODE_PRIVATE).edit().putString("cookie", value).commit();

                System.out.println("get0---" + cookie);

                System.out.println("应该是cookie了吧" + cookies);

            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        }).build();

        FormBody formBody = new FormBody.Builder().add("account", "18555556688")
                .add("pwd", "bfd59291e825b5f2bbf1eb76569f8fe7")
                .build();
        Request request = new Request.Builder().post(formBody).url(LOGIN_URL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                System.out.println("登录返回" + string);
                Headers headers = response.headers();
                int size = headers.size();
                System.out.println("headers的size" + headers.size());
                System.out.println("header-----" + headers);
//                headers.
            }
        });
    }

    private void checkUpdate() {
        /*new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().get().url("http://phone.52mdb.cc/index/sync?system_name=").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final WorkUpdateBean workUpdateBean = gson.fromJson(string, WorkUpdateBean.class);
                System.out.println("访问body" + string);
                System.out.println("更新网址" + workUpdateBean.getUpnew().getUpdateUrl());
                mHandler.sendEmptyMessage(DOWN_APK);
                downLoadApk(workUpdateBean.getUpnew().getUpdateUrl());
            }
        });

    }

    private void downLoadApk(String url) {
//        mProgressDialog = new ProgressDialog(WorkUpdateActivity.this);
//        mProgressDialog.setCancelable(false);
//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        mProgressDialog.show();
//        System.out.println("progressdialog的");


//        if (mProgressDialog == null) {

   /*     new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/

//        }


//        if (mMaterialDialog == null) {
//            mMaterialDialog = new MaterialDialog.Builder(WorkUpdateActivity.this)
//                    .title("dialog版本升级")
//                    .content("dialog正在下载")
//                    .progress(false, 100, false)  
//                    .cancelable(false)
//                    .show();
//        }

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "new.apk");
        request.setTitle("通知栏更新");
        request.setDescription("通知栏更新介绍");
        request.setMimeType("application/vnd.android.package-archive");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.allowScanningByMediaScanner();
        request.setVisibleInDownloadsUi(true);
        lastDownloadId = downloadManager.enqueue(request);
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong("lastDownLoadId", lastDownloadId).commit();
        System.out.println("储存的downId" + lastDownloadId);
        DownloadObserver downloadObserver = new DownloadObserver(null);
        getContentResolver().registerContentObserver(CONTENT_URL, true, downloadObserver);

    }

    class DownloadObserver extends ContentObserver {
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public DownloadObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(lastDownloadId);
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Cursor cursor = downloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                int totalColumn = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                int currentColumn = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                int totalSize = cursor.getInt(totalColumn);
                int currentSize = cursor.getInt(currentColumn);
                float percent = (float) currentSize / (float) totalSize;
                int progress = Math.round(percent * 100);
                mProgressDialog.setProgress(progress);
                if (progress == 100) {
                    mProgressDialog.dismiss();
                }
                System.out.println("进度" + progress);
            }
        }
    }
}