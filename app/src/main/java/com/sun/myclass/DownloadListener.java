package com.sun.myclass;

/**
 * Created by stephensun on 2018/3/13.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onPaused();
    void onFailed();
    void onCanceled();
}
