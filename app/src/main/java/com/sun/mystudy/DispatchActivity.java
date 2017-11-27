package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DispatchActivity extends AppCompatActivity {

    private Button mBtn_click;
    private ImageView mIv_big;
    private final String LOG_DISPATCH = "dispatch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diapatch);

        initView();
    }

    private void initView() {
        mBtn_click = (Button) findViewById(R.id.btn_click);
        mIv_big = (ImageView) findViewById(R.id.iv_big);

        mBtn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_DISPATCH, "btn点击了");
            }
        });

//        mIv_big.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(LOG_DISPATCH, "iv点击了");
//            }
//        });

        findViewById(R.id.iv_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_DISPATCH, "small点了");
            }
        });

    }
}
