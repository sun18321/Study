package com.sun.mystudy;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.sun.bean.RefreshData;

import java.util.ArrayList;
import java.util.List;


public class RefreshActivity extends AppCompatActivity {

    private PullToRefreshListView mRefreshListView;
    private List<RefreshData> dataList = new ArrayList<>();
    private MyAdapter mAdapter;
    private int down = 1;
    private int up = 1;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mRefreshListView.onRefreshComplete();
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private PullToRefreshBase.OnRefreshListener2<ListView> mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            RefreshData refreshData = new RefreshData();
            refreshData.first = "first" + i;
            refreshData.second = "second" + i;
            dataList.add(refreshData);
        }
    }

    private void initView() {
        findViewById(R.id.text_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListener.onPullUpToRefresh(null);
//                mRefreshListView.setRefreshing(true);
                System.out.println("点击了button");
                mRefreshListView.setRefreshing();

            }
        });
        mRefreshListView = (PullToRefreshListView) findViewById(R.id.pulltorefrsh);

        mAdapter = new MyAdapter();
        mRefreshListView.setAdapter(mAdapter);
        mRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("点击了listview条目" + position);
            }
        });

        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        initRefresh();


        mListener = new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                RefreshData data = new RefreshData();
                data.first = "下拉刷新" + down;
                data.second = "下拉刷新" + down;
                dataList.add(0, data);
                down++;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                new FinishRefresh().execute();
                mHandler.sendEmptyMessageDelayed(0, 1000);

//                mRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                RefreshData data = new RefreshData();
                data.first = "上拉加载" + up;
                data.second = "上拉加载" + up;
                dataList.add(data);
                up++;
//                try {
//                    Thread.sleep(1000);
//                    mRefreshListView.onRefreshComplete();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                new FinishRefresh().execute();
//                mRefreshListView.onRefreshComplete();
//                mAdapter.notifyDataSetChanged();
//                mRefreshListView.onRefreshComplete();
                mHandler.sendEmptyMessageDelayed(0, 1000);
            }
        };
        mRefreshListView.setOnRefreshListener(mListener);


    }

    private void initRefresh() {
        ILoadingLayout startLabels = mRefreshListView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("下拉正在载入");
        startLabels.setReleaseLabel("下拉松开刷新");


        ILoadingLayout endLabels = mRefreshListView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载");
        endLabels.setRefreshingLabel("上拉正在载入");
        endLabels.setReleaseLabel("上拉松开刷新");
    }

    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result){
//          adapter.notifyDataSetChanged();
            mRefreshListView.onRefreshComplete();
        }
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataList.size();
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
            RefreshData refreshData = dataList.get(position);
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(parent.getContext(), R.layout.listview_item, null);
                viewHolder.first = (TextView) convertView.findViewById(R.id.first_text);
                viewHolder.second = (TextView) convertView.findViewById(R.id.second_text);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.first.setText(refreshData.first);
            viewHolder.second.setText(refreshData.second);

            return convertView;
        }

        class ViewHolder {
            TextView first;
            TextView second;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


}
