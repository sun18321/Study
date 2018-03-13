package com.sun.myclass;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by stephensun on 2018/3/13.
 */

public class DownloadAsyncTask extends AsyncTask<String,Integer,Integer> {
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_PAUSED = 1;
    public static final int TYPE_FAILED = 2;
    public static final int TYPE_CANCELED = 3;

    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;
    private DownloadListener listener;

    public DownloadAsyncTask(DownloadListener listener) {
        this.listener = listener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... strings) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;

        long downloadLength = 0;
        String downloadUrl = strings[0];
        String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        file = new File(directory + fileName);
        if (file.exists()) {
            downloadLength = file.length();
        }
        long contentLength = getContentLength(downloadUrl);
        if (downloadLength == 0) {
            return TYPE_FAILED;
        } else if (downloadLength == contentLength) {
            return TYPE_SUCCESS;
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).addHeader("RANGE", "bytes=" + downloadLength + "-").build();
        try {
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else {
                        if (isPaused) {
                            return TYPE_PAUSED;
                        } else {
                            total = total + len;
                            savedFile.write(b, 0, len);
                            int progress = (int) ((total + downloadLength) * 100 / contentLength);
                            publishProgress(progress);
                        }
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.getClass();
                }
            }catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        int progress = values[0];
        if (progress > lastProgress) {
            if (listener != null) {
                listener.onProgress(lastProgress);
            }
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        switch (integer) {
            case TYPE_SUCCESS:
                if (listener != null) {
                    listener.onSuccess();
                }
                break;
            case TYPE_PAUSED:
                if (listener != null) {
                    listener.onPaused();
                }
                break;
            case TYPE_CANCELED:
                if (listener != null) {
                    listener.onCanceled();
                }
                break;
            case TYPE_FAILED:
                if (listener != null) {
                    listener.onFailed();
                }
                break;
        }
    }

    private long getContentLength(String downloadUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        try {
            Response response = client.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return 0;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }
}
