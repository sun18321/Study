package com.sun.mystudy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.sun.bean.WorkUpdateBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CustomDownloadActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_download);

        checkUpdate();
    }

    private void checkUpdate() {
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
                System.out.println("更新url" + workUpdateBean.getUpnew().getUpdateUrl());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downApk(workUpdateBean.getUpnew().getUpdateUrl());
                    }
                });


            }
        });
    }

    private void downApk(String updateUrl) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(CustomDownloadActivity.this);
            mProgressDialog.setTitle("正在更新");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().get().url(updateUrl).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                long totalLength = response.body().contentLength();
                mProgressDialog.setMax((int) totalLength);
                File file = new File(Environment.getExternalStorageDirectory(), "myapk.apk");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] arr = new byte[1024];
                int len;
                int progress = 0;
                while ((len = inputStream.read(arr)) != -1) {
                    fileOutputStream.write(arr, 0, len);
                    progress += len;
                    mProgressDialog.setProgress(progress);
                }
                fileOutputStream.flush();
                mProgressDialog.dismiss();
                installApk(file);
            }
        });



    }

    private void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        Uri data = Uri.fromFile(file);
        intent.setDataAndType(data,"application/vnd.android.package-archive");
        startActivity(intent);
    }
}
