package com.sun.mystudy;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.sun.advertise.CardViewPager;

public class AdvertiseActivity extends AppCompatActivity {

    private ViewPager mAd_pager;
    private int[] arr = {R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R.mipmap.p4};
    private AdvertiseAdapter mAdapter;
    private ListView mAd_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

        initView();
    }

    private void initView() {
        mAd_pager = (ViewPager) findViewById(R.id.ad_pager);
        mAdapter = new AdvertiseAdapter();
        mAd_pager.setOffscreenPageLimit(3);
        mAd_pager.setAdapter(mAdapter);

        mAd_list = (ListView) findViewById(R.id.ad_list);
    }

    class AdvertiseAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(AdvertiseActivity.this).inflate(R.layout.page_item, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.page_image);
            imageView.setImageResource(arr[position]);
            container.addView(view);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
