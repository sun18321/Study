package com.sun.mystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sun.design.StockView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StockActivity extends AppCompatActivity {

    @BindView(R.id.stockview)
    StockView mStockview;
    @BindView(R.id.draw)
    Button mDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        ButterKnife.bind(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        Log.d("stock", "actW:" + displayMetrics.widthPixels + "actH:" + displayMetrics.heightPixels);

    }

    @OnClick({R.id.stockview, R.id.draw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stockview:
                break;
            case R.id.draw:
//                mStockview.doDraw();
                break;
        }
    }
}
