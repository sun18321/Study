package com.sun.mystudy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewChatActivity extends AppCompatActivity {

    private RecyclerView mRecy_chat;
    private List<String> mList;
    private int mark = 0;
    private ChatAdapter mAdapter;
    private Paint mPaint;
    private LinearGradient linearGradient;
    private int layerId;
    private int mTargetPosition;
    private boolean mShouldScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);

        initView();
    }

    private void initView() {
        mList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            mList.add("这就是数据" + i);
        }
        mRecy_chat = (RecyclerView) findViewById(R.id.recyclerview_chat);
        mRecy_chat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new ChatAdapter();

        mRecy_chat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Log.d("position", "scrollListener");

                if (mShouldScroll) {
                    mShouldScroll = false;
                    upgradeSmooth(mTargetPosition);
                }
            }
        });


        mRecy_chat.setAdapter(mAdapter);
        doTopGradualEffect();

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark++;
                mList.add("新增数据" + mark);
                mAdapter.notifyDataSetChanged();
                mRecy_chat.smoothScrollToPosition(mList.size() - 1);
            }
        });

        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mark > 0) {
                    mark--;
                }
                mList.remove(mList.size() - 1);
                mAdapter.notifyDataSetChanged();
                mRecy_chat.smoothScrollToPosition(mList.size()-1);
            }
        });

        //屏幕可见条目数 mRecy_chat.getChildCount()

        findViewById(R.id.btn_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前可见recyclerview第一条在整个recyclerview中是第几条
                int first = mRecy_chat.getChildLayoutPosition(mRecy_chat.getChildAt(0));
                //当前可见recyclerview最后一条在整个recyclerview中是第几条
                int last = mRecy_chat.getChildLayoutPosition(mRecy_chat.getChildAt(mRecy_chat.getChildCount() - 1));
                Log.d("position", "first--" + first + "--" + "last--" + last + "--" + mRecy_chat.getChildCount());
                int top_3 = mRecy_chat.getChildAt(3).getTop();
                int top_4 = mRecy_chat.getChildAt(4).getTop();
                Log.d("position", "top_3--" + top_3 + "--" + "top_4" + top_4);
            }
        });

        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mRecy_chat.smoothScrollToPosition(5);
                upgradeSmooth(5);
            }
        });

    }

    private void upgradeSmooth(int position) {
        int first = mRecy_chat.getChildLayoutPosition(mRecy_chat.getChildAt(0));
        int last = mRecy_chat.getChildLayoutPosition(mRecy_chat.getChildAt(mRecy_chat.getChildCount() - 1));

        Log.d("position", "upgrade");

        if (position < first) {
            mRecy_chat.smoothScrollToPosition(position);
        } else if (position >= first && position <= last) {
            int movePosition = position - first;
            int top = mRecy_chat.getChildAt(movePosition).getTop();
            mRecy_chat.smoothScrollBy(0, top);
        } else {
            mRecy_chat.smoothScrollToPosition(position);
            mTargetPosition = position;
            mShouldScroll = true;
        }
    }


    public void doTopGradualEffect(){
        if(mRecy_chat == null){
            return ;
        }

        mPaint = new Paint();
        final Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint.setXfermode(xfermode);
        linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, 100.0f, new int[]{0, Color.BLACK}, null, Shader.TileMode.CLAMP);

        mRecy_chat.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(canvas, parent, state);

                mPaint.setXfermode(xfermode);
                mPaint.setShader(linearGradient);
                canvas.drawRect(0.0f, 0.0f, parent.getRight(), 200.0f, mPaint);
                mPaint.setXfermode(null);
                canvas.restoreToCount(layerId);
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                layerId = c.saveLayer(0.0f, 0.0f, (float) parent.getWidth(), (float) parent.getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
    }




    class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
        @Override
        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(NewChatActivity.this).inflate(R.layout.chat_adapter_item, parent, false);
            return new ChatHolder(view);
        }

        @Override
        public void onBindViewHolder(ChatHolder holder, int position) {
            holder.tv_chat.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }



    static class ChatHolder extends RecyclerView.ViewHolder {
        TextView tv_chat;
        public ChatHolder(View itemView) {
            super(itemView);

            tv_chat = (TextView) itemView.findViewById(R.id.tv_chat);
        }
    }

}

