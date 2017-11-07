package com.sun.advertisetwo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.mystudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2017/9/27.
 */

public class HorizontalPagerAdapter extends PagerAdapter {
    private List<Integer> list = new ArrayList<>();

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;

        list.add(R.mipmap.bg_poker_toast);
        list.add(R.mipmap.bg_poker_toast);
        list.add(R.mipmap.bg_poker_toast);
        list.add(R.mipmap.bg_poker_toast);
        list.add(R.mipmap.bg_poker_toast);
        list.add(R.mipmap.bg_poker_toast);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.ad_item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_item);
        imageView.setImageResource(list.get(position));
//        TextView textView = (TextView) view.findViewById(R.id.txt_item);
//        textView.setText("" + position);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("我是第" + position + "个");
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
