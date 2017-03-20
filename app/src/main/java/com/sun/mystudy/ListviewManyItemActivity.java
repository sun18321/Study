package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListviewManyItemActivity extends AppCompatActivity {

    private ListView mListView;
    private static final int ITEM_ONE = 1;
    private static final int ITEM_TWO = 2;
    private static final int ITEM_THREE = 3;
    private int[] arr = {1, 2, 3, 4, 8, 9, 15, 21, 27, 16, 32, 11, 11};


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_many_item);

        init();
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new ManyItemAdapter());
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getAdapter().getItem(position);
                Toast.makeText(ListviewManyItemActivity.this, "" + item,Toast.LENGTH_SHORT).show();
                System.out.println(item);
            }
        });
    }

    class ManyItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public Object getItem(int position) {
            return arr[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                int type = getItemViewType(position);
                switch (type) {
                    case ITEM_ONE:
                        convertView = LayoutInflater.from(ListviewManyItemActivity.this).inflate(R.layout.item1, parent, false);
                        break;
                    case ITEM_TWO:
                        convertView = LayoutInflater.from(ListviewManyItemActivity.this).inflate(R.layout.item2, parent, false);
                        break;
                    case ITEM_THREE:
                        convertView = LayoutInflater.from(ListviewManyItemActivity.this).inflate(R.layout.item3, parent, false);
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            int type;
            int item = arr[position];
            if (item % 2 == 0) {
                type = ITEM_TWO;
            } else if (item % 3 == 0) {
                type = ITEM_THREE;
            } else {
                type = ITEM_ONE;
            }
            return type;
        }
    }

    class ViewHolder {
    }

}
