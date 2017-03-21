package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

public class ListviewLoadActivity extends AppCompatActivity {

    private ListView mListView;
    private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_load);

        init();
        ListLoadAdapter adapter = new ListLoadAdapter();
        mListView.setAdapter(adapter);
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.listview);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int end;
            private int start;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        for (; start < end; start++) {
                            ImageView image = (ImageView) mListView.findViewWithTag(start);
//                            Glide.with(ListviewLoadActivity.this).load(mStraggeredIcons[start]).into(image);
                            image.setBackground(getResources().getDrawable(mStraggeredIcons[start]));
                        }

                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                start = firstVisibleItem;
                end = firstVisibleItem + visibleItemCount;
            }
        });
    }

    class ListLoadAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mStraggeredIcons.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LoadViewHolder holder;
            if (convertView == null) {
                holder = new LoadViewHolder();
                convertView = LayoutInflater.from(ListviewLoadActivity.this).inflate(R.layout.item_load, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (LoadViewHolder) convertView.getTag();
            }
            holder.image.setTag(position);
            return convertView;
        }
    }

    class LoadViewHolder {
        ImageView image;
    }


}
