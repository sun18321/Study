package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class RecyclerviewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        init();
    }

    private void init() {
        for (int i = 'A'; i < 'z'; i++) {
            list.add("哈哈哈" + (char) i);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new MyAdapter());
//        SecondAdapter secondAdapter = new SecondAdapter(this, list);
//        secondAdapter.setRcyclerListener(new SecondAdapter.RecyclerListener() {
//            @Override
//            public void doListen(int postion) {
//
//            }
//        });
//        mRecyclerView.setAdapter(secondAdapter);
//        mRecyclerView.

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(RecyclerviewActivity.this).inflate(R.layout.recycler_item, parent, false));
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
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
