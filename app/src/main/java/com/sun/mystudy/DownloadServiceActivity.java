package com.sun.mystudy;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sun.myclass.DownloadService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadServiceActivity extends AppCompatActivity {

    @BindView(R.id.start)
    Button mStart;
    @BindView(R.id.pause)
    Button mPause;
    @BindView(R.id.cancel)
    Button mCancel;

    private DownloadService.DownloadBinder mDownloadBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_service);
        ButterKnife.bind(this);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    @OnClick({R.id.start, R.id.pause, R.id.cancel})
    public void onClick(View view) {
        if (mDownloadBinder == null) {
            return;
        }

        switch (view.getId()) {
            case R.id.start:
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                mDownloadBinder.startDownload(url);
                break;
            case R.id.pause:
                mDownloadBinder.pauseDownload();
                break;
            case R.id.cancel:
                mDownloadBinder.cancelDownload();
                break;
        }
    }
}
