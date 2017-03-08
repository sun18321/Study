package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ListViewPageActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private int page = 0;
    private String[] current_arr;

    String[] arr1 = {"世界","一级","方程","式锦","标赛","简称","F1","是由","国际","汽车运动联合会","举办的最高等级的","年度系列场地赛车比赛"};
    String[] arr2 = {"是当","今世界最高水平的","赛车比赛","与奥运会","世界杯足球赛","并称为","世界三大体育盛事"};
    String[] arr3 = {"F1赛车为单座的特制赛车","座舱是敞露在外的","巨大的轮胎也是暴露在车身外面的","没有翼子板遮挡","F1赛车不能在普通道路上行驶","在汽车厂的流水线也不生产","而是由各赛车公司或车厂的赛车运动部单独设计和制造的"};
    List<String[]> list = new ArrayList<>();
    private MyAdapter mAdapter;
    private TextView mCurrent_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_page);

        init();
    }

    private void init() {
        list.add(arr1);
        list.add(arr2);
        list.add(arr3);

        mCurrent_page = (TextView) findViewById(R.id.current_page);
        mCurrent_page.setText("第" + (page + 1) + "页");
        findViewById(R.id.last_page).setOnClickListener(this);
        findViewById(R.id.next_page).setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.last_page:
                if (page <= 0) {
                    Toast.makeText(this, "这是第一页", Toast.LENGTH_SHORT).show();
                    return;
                }
                page--;
                mCurrent_page.setText("第" + (page + 1) + "页");
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.next_page:
                if (page >= list.size() - 1) {
                    Toast.makeText(this, "没有更多数据了!", Toast.LENGTH_SHORT).show();
                    return;
                }
                page++;
                mCurrent_page.setText("第" + (page + 1) + "页");
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    class MyAdapter extends BaseAdapter {
//        String[] arr;
//
//        public MyAdapter(String[] arr) {
//            this.arr = arr;
//        }

        @Override
        public int getCount() {
            return list.get(page).length;
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
                convertView = View.inflate(parent.getContext(), R.layout.item_list_page, null);
                viewHolder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.text.setText(list.get(page)[position]);
            return convertView;
        }

        class ViewHolder {
            TextView text;
        }
    }

    }
