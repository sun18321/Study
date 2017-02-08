package com.sun.mystudy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sun.util.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRefresh;
    private Button mNetRequest;


    private int mPhoneState;
    private int mWrite_storage;
    private int mRead_storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPermission();
//        initView();
    }

    private void initPermission() {
        mPhoneState = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        mWrite_storage = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        mRead_storage = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (mPhoneState != PackageManager.PERMISSION_GRANTED || mWrite_storage != PackageManager.PERMISSION_GRANTED || mRead_storage != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, 2);
//         init();
        } else {
//         init();
            initView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED || grantResults[2] != PackageManager.PERMISSION_GRANTED) {
//            Utils.showCroutonText(MainActivity.this, "没有电话权限,请打开再尝试");
            Toast.makeText(MainActivity.this, "没有电话权限,请打开再尝试", Toast.LENGTH_SHORT).show();
            return;
        } else {
            initView();
        }

    }

    private void initView() {
        mRefresh = (Button) findViewById(R.id.refresh);
        mRefresh.setOnClickListener(this);
        mNetRequest = (Button) findViewById(R.id.net_request);
        mNetRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refresh:
                startActivity(new Intent(this, RefreshActivity.class));
                break;
            case R.id.net_request:
                startActivity(new Intent(this, RequestActivity.class));
                break;
        }
    }
}
