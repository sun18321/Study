package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecyclerviewLoadActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;


    private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};
    private String[] string_text = {"一","二","三","四","五","六","七","八","九","十","壹","贰","叁","肆","伍","陆","柒","捌","玖","拾"};
    private RecyclerLoadAdpter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_load);

        init();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerviewLoadActivity.this, LinearLayoutManager.VERTICAL, false));
        mAdpter = new RecyclerLoadAdpter();


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        mAdpter.setScrollState(false);
                        int childCount = recyclerView.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            LinearLayout linear = (LinearLayout) recyclerView.getChildAt(i).findViewById(R.id.linear);
                            ImageView image = (ImageView) recyclerView.getChildAt(i).findViewById(R.id.image);
                            if (linear.getTag() != null) {
                                Glide.with(RecyclerviewLoadActivity.this).load(linear.getTag()).into(image);
                                linear.setTag(null);
                            }
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        mAdpter.setScrollState(true);
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        mAdpter.setScrollState(true);
                        break;
                }
            }
        });

        mRecyclerView.setAdapter(mAdpter);
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

    }

    class RecyclerLoadAdpter extends RecyclerView.Adapter<RecyclerLoadHolder> {
        private boolean scrollState = true;

        public void setScrollState(boolean scrollState) {
            this.scrollState = scrollState;
        }


        @Override
        public RecyclerLoadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerviewLoadActivity.this).inflate(R.layout.item_load, parent, false);
            RecyclerLoadHolder recyclerLoadHolder = new RecyclerLoadHolder(view);
            return recyclerLoadHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerLoadHolder holder, int position) {
            if (scrollState) {
                Glide.with(RecyclerviewLoadActivity.this).load(R.mipmap.g20).into(holder.image);
                holder.linear.setTag(mStraggeredIcons[position]);
            } else {
                holder.text.setText(string_text[position]);
                Glide.with(RecyclerviewLoadActivity.this).load(mStraggeredIcons[position]).into(holder.image);
                holder.linear.setTag(null);
            }
        }

        @Override
        public int getItemCount() {
            return string_text.length;
        }
    }

    static class RecyclerLoadHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView image;
        LinearLayout linear;
        public RecyclerLoadHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);
            linear = (LinearLayout) itemView.findViewById(R.id.linear);
        }
    }

}
