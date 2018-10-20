package com.sun.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.sun.bean.ScreenSize;
import com.sun.mystudy.R;

import java.util.List;

/**
 * Created by sun on 2017/2/20.
 */
public class MyApplication extends Application {
    public static Context mContext;
    private static DisplayImageOptions options;
    private static ScreenSize mScreenSize;

    @Override
    public void onCreate() {
        super.onCreate();

//        mLocationClient = new LocationClient(getApplicationContext());
//        //声明LocationClient类
//        mLocationClient.registerLocationListener( myListener );
//        //注册监听函数
//
//        initLocation();
//        mLocationClient.start();
//
//        initImageLoader();
        mContext = getApplicationContext();
    }

    public static ScreenSize getScreenSize() {
        if (mScreenSize == null) {
            DisplayMetrics metrics = new DisplayMetrics();
            mScreenSize.setHeight(metrics.heightPixels);
            mScreenSize.setWidth(metrics.widthPixels);
        }
        return mScreenSize;
    }



}
