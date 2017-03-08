package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ScrollviewAndListviewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private int[] arr_image = {R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4, R.mipmap.g5, R.mipmap.g6, R.mipmap.g7, R.mipmap.g8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_and_listview);

        init();
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter(arr_image);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "我是第" + position + "个", Toast.LENGTH_SHORT).show();
    }

    class MyAdapter extends BaseAdapter {
        int[] arr;
        public MyAdapter(int[] arr) {
            this.arr = arr;
        }

        @Override
        public int getCount() {
            return arr.length;
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
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(parent.getContext(), R.layout.scroll_list_item, null);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.item_image);
                viewHolder.text = (TextView) convertView.findViewById(R.id.item_text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Glide.with(parent.getContext()).load(arr[position]).into(viewHolder.image);
            viewHolder.text.setText("哈哈哈" + position);

            return convertView;
        }

        class ViewHolder {
            ImageView image;
            TextView text;
        }


    }

}
