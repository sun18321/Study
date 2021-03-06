package com.sun.mystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sun.design.ClickDrawView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClickDrawActivity extends AppCompatActivity {

    @BindView(R.id.click_view)
    ClickDrawView mClickView;
    @BindView(R.id.btn_click)
    Button mBtnClick;
    @BindView(R.id.btn_rotate)
    Button mBtnRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_draw);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_click, R.id.btn_rotate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_click:
                mClickView.drawNext();
                break;
            case R.id.btn_rotate:
                mClickView.rotate();
                break;
        }
    }
}
