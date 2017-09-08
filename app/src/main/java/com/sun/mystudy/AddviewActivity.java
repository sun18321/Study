package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class AddviewActivity extends AppCompatActivity {

    private Button mBtn;
    private RelativeLayout mRl_top;

    private int btn_x;
    private int btn_y;
    private int btn_width;
    private int btn_height;
    private Button mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addview);

        init();
    }




    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        System.out.println("开始获取坐标了");
        int[] loc = new int[2];
        mBtn.getLocationOnScreen(loc);
        btn_x = loc[0];
        btn_y = loc[1];

        btn_height = mBtn.getHeight();
        btn_width = mBtn.getWidth();

        System.out.println(btn_x + "----" + btn_y);
        System.out.println("宽:" + btn_width + "高:" + btn_height);
    }

    private void init() {
        mBtn = (Button) findViewById(R.id.btn);
        mRl_top = (RelativeLayout) findViewById(R.id.rl_top);
        mAdd = (Button) findViewById(R.id.add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button copy_btn = new Button(AddviewActivity.this);
                copy_btn.setText("复制的");
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(btn_width,btn_height );
                layoutParams.addRule(RelativeLayout.BELOW,R.id.btn);
                mRl_top.addView(copy_btn, layoutParams);

            }
        });

    }

}
