package com.sun.mystudy;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopupWindowActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_mPopupWindow;
    private View mView_parent;
    private PopupWindow mPopupWindow;
    private ListView mListview;
    private int[] arr = {1, 2, 3, 4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
    private int mWidth;
    private int mHeight;
    private TextView mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);

        init();
    }

    private void init() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;
        System.out.println("width" + mWidth);
        System.out.println("height" + mHeight);

        mView_parent = View.inflate(this, R.layout.popup_window, null);
        btn_mPopupWindow = (Button) findViewById(R.id.popupwindow);
        btn_mPopupWindow.setOnClickListener(this);
        mLocation = (TextView) findViewById(R.id.location);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popupwindow:
                showPopupWindow();
//                mPopupWindow.showAtLocation(mView_parent, Gravity.CENTER,0,0);
//                mPopupWindow.showAsDropDown(btn_mPopupWindow);
                mPopupWindow.showAsDropDown(mLocation);
//                mPopupWindow.showAtLocation(mLocation, Gravity., 0, 0);
                break;
        }
    }

    private void showPopupWindow() {
        View view = View.inflate(this, R.layout.item_popupwindow, null);
        mListview = (ListView) view.findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter();
        mListview.setAdapter(adapter);
//        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
//        mPopupWindow = new PopupWindow(view, mWidth / 2, mHeight / 2, true);
        mPopupWindow = new PopupWindow(view, mWidth, mHeight/2, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tx = new TextView(parent.getContext());
            tx.setText("我是操作" + arr[position]);
            return tx;
        }
    }

}
