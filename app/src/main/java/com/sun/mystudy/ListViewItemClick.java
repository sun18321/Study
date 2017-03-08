package com.sun.mystudy;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewItemClick extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private int[] arr = {5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95};
    private int oldPosition = -1;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_item_click);

        init();

    }

    private void init() {
        ListView listView = (ListView) findViewById(R.id.listview);
        mAdapter = new MyAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.upDateChecked(position);
//        view.setBackgroundColor(Color.parseColor("#ff0000"));
    }

    class MyAdapter extends BaseAdapter {
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

        public void upDateChecked(int position) {
            if (oldPosition == position) {
                oldPosition = -1;
            } else {
                oldPosition = position;
            }
            mAdapter.notifyDataSetChanged();
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(parent.getContext(), R.layout.item_list_click, null);
                viewHolder.first = (TextView) convertView.findViewById(R.id.first);
                viewHolder.second = (TextView) convertView.findViewById(R.id.second);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
//            if (oldPosition != -1) {
//                convertView.setBackgroundColor(Color.parseColor("#ff0000"));
//                View view = parent.getChildAt(position);
//                view.setBackgroundColor(Color.parseColor("#ff0000"));
//        }
                if (position == oldPosition) {
//                    viewHolder.first.setTextColor(Color.parseColor("#ff0000"));
//                    viewHolder.second.setTextColor(Color.parseColor("#ff0000"));
                    convertView.setBackgroundColor(Color.parseColor("#ff0000"));
                } else {
//                    viewHolder.first.setTextColor(Color.parseColor("#000000"));
//                    viewHolder.second.setTextColor(Color.parseColor("#000000"));
                    convertView.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            viewHolder.first.setText("" + arr[position]);
            viewHolder.second.setText("写代码" + position + 1);
            return convertView;
        }

        class ViewHolder {
            TextView first;
            TextView second;
        }

    }

}
