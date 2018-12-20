package com.sun.mystudy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.sun.fragment.GuideFragment;
import com.sun.widget.NewTransformPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {
    private int[] mArrPng = {R.mipmap.p1, R.mipmap.p2, R.mipmap.p3,R.mipmap.p4,R.mipmap.p5};
    private List<Fragment> mList = new ArrayList<>();

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);

        for (int i = 0; i < 15; i++) {
            mList.add(GuideFragment.newInstance(i));
        }


        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setPageTransformer(true, new NewTransformPage(40));
//        mViewpager.setAdapter(new CustomFragmentAdapter(getSupportFragmentManager(), mList, null));
        mViewpager.setAdapter(new Adapter());


        Button btn = (Button) findViewById(R.id.btn_log);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewPagerActivity.this, mViewpager.getCurrentItem() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class Adapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mArrPng.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ViewPagerActivity.this).inflate(R.layout.page_item_new, container, false);
            ImageView image = (ImageView) view.findViewById(R.id.page_img);
            image.setImageResource(mArrPng[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    class CustomFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments;
        private String[] mTitles;

        public CustomFragmentAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }
    }
}
