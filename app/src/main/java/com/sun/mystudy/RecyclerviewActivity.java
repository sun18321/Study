package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.*;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class RecyclerviewActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<>();
    private MyAdapter mAdapter;
    boolean isStagger;
    private List<Integer> mList_height;
    private DividerItemDecoration mDividerItemDecoration;
    private DividerGridItemDecoration mDividerGridItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        init();
    }

    private void init() {
        for (int i = 'A'; i < 'z'; i++) {
            list.add("" + (char) i);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyAdapter();
//        SecondAdapter secondAdapter = new SecondAdapter(this, list);
//        secondAdapter.setRcyclerListener(new SecondAdapter.RecyclerListener() {
//            @Override
//            public void doListen(int postion) {
//
//            }
//        });
//        mRecyclerView.setAdapter(secondAdapter);
//        mRecyclerView.
        findViewById(R.id.lin_vertical).setOnClickListener(this);
        findViewById(R.id.lin_hor).setOnClickListener(this);
        findViewById(R.id.grid_hor).setOnClickListener(this);
        findViewById(R.id.grid_vertical).setOnClickListener(this);
        findViewById(R.id.starg_hor).setOnClickListener(this);
        findViewById(R.id.starg_vertical).setOnClickListener(this);


        mDividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mDividerGridItemDecoration = new DividerGridItemDecoration(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_hor:
                mRecyclerView.removeItemDecoration(mDividerGridItemDecoration);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//                mRecyclerView.setAdapter(mAdapter);
                isStagger = false;
                break;
            case R.id.lin_vertical:
                mRecyclerView.removeItemDecoration(mDividerGridItemDecoration);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addItemDecoration(mDividerItemDecoration);
                isStagger = false;
                break;
            case R.id.grid_hor:
                mRecyclerView.removeItemDecoration(mDividerItemDecoration);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,4, GridLayoutManager.HORIZONTAL, false));
//                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addItemDecoration(mDividerGridItemDecoration);
                isStagger = false;
                break;
            case R.id.grid_vertical:
                mRecyclerView.removeItemDecoration(mDividerItemDecoration);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,4,GridLayoutManager.VERTICAL, false));
//                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addItemDecoration(mDividerGridItemDecoration);
                isStagger = false;
                break;
            case R.id.starg_hor:
                mRecyclerView.removeItemDecoration(mDividerGridItemDecoration);
                mRecyclerView.removeItemDecoration(mDividerItemDecoration);
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
//                mRecyclerView.setAdapter(mAdapter);
                isStagger = true;
                break;
            case R.id.starg_vertical:
                mRecyclerView.removeItemDecoration(mDividerGridItemDecoration);
                mRecyclerView.removeItemDecoration(mDividerItemDecoration);
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
//                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addItemDecoration(mDividerGridItemDecoration);
                isStagger = true;
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Integer> getRandomHeight(int size) {
        mList_height = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mList_height.add((int)(Math.random()*300+100));
        }
        return mList_height;
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(RecyclerviewActivity.this).inflate(R.layout.recycler_item, parent, false));
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            if (isStagger) {
                ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                layoutParams.height = getRandomHeight(list.size()).get(position);
                holder.itemView.setLayoutParams(layoutParams);
            }

            holder.text.setText(list.get(position));
            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RecyclerviewActivity.this, holder.text.getText().toString(), Toast.LENGTH_SHORT).show();
                    System.out.println(holder.text.getText().toString());
                    Log.e("eeee", "aa");
                    Log.w("wwww", holder.text.getText().toString());
                    Log.i("iiii", holder.text.getText().toString());
                    Log.d("dddd", holder.text.getText().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView text;
            public MyViewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.item_text);
            }
        }

//                private RecyclerListener mListener;
//        public interface RecyclerListener {
//            void doListen(int position);
//        }
    }


    class OrignalAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }



}
