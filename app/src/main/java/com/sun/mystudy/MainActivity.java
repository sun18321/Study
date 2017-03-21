package com.sun.mystudy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.sun.util.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRefresh;
    private Button mNetRequest;


    private int mPhoneState;
    private int mWrite_storage;
    private int mRead_storage;
    private Button mScroll_list;
    private Button mCommon_listview;
    private Button mList_itemClick;

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
        mScroll_list = (Button) findViewById(R.id.scroll_listview);
        mScroll_list.setOnClickListener(this);
        mCommon_listview = (Button) findViewById(R.id.common_listview);
        mCommon_listview.setOnClickListener(this);
        mList_itemClick = (Button) findViewById(R.id.itemClick_list);
        mList_itemClick.setOnClickListener(this);
        findViewById(R.id.listview_page).setOnClickListener(this);
        findViewById(R.id.popupwindow).setOnClickListener(this);
        findViewById(R.id.common_radiobutton).setOnClickListener(this);
        findViewById(R.id.empty_radiobutton).setOnClickListener(this);
        findViewById(R.id.spinner).setOnClickListener(this);
        findViewById(R.id.recyclerview).setOnClickListener(this);
        findViewById(R.id.screen_click).setOnClickListener(this);
        findViewById(R.id.listview_many_item).setOnClickListener(this);
        findViewById(R.id.recyclerview_many_item).setOnClickListener(this);
        findViewById(R.id.gallery).setOnClickListener(this);
        findViewById(R.id.listview_load).setOnClickListener(this);
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
            case R.id.scroll_listview:
                startActivity(new Intent(this, ScrollviewAndListviewActivity.class));
                break;
            case R.id.common_listview:

                break;
            case (R.id.itemClick_list):
                startActivity(new Intent(this, ListViewItemClick.class));
                break;
            case R.id.listview_page:
                startActivity(new Intent(this, ListViewPageActivity.class));
                break;
            case R.id.popupwindow:
                startActivity(new Intent(this, PopupWindowActivity.class));
                break;
            case R.id.common_radiobutton:
                startActivity(new Intent(this,CommonRadiobuttonActivity.class));
                break;
            case R.id.empty_radiobutton:
                startActivity(new Intent(this, EmptyRadiobuttonActivity.class));
                break;
            case R.id.spinner:
                startActivity(new Intent(this, SpinnerActivity.class));
                break;
            case R.id.recyclerview:
                startActivity(new Intent(this, RecyclerviewActivity.class));
                break;
            case R.id.screen_click:
                startActivity(new Intent(this, ScreenClickActivity.class));
                break;
            case R.id.listview_many_item:
                startActivity(new Intent(this,ListviewManyItemActivity.class));
                break;
            case R.id.recyclerview_many_item:
                startActivity(new Intent(this, RecyclerviewManyItemActivity.class));
                break;
            case R.id.gallery:
                startActivity(new Intent(this, GalleryActivity.class));
                break;
            case R.id.listview_load:
                studyStartActivity(ListviewLoadActivity.class);
                break;
        }
    }

    public void studyStartActivity(Class clazz) {
        startActivity(new Intent(this,clazz));
    }
}
