package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sun.design.FlowLayout;

public class FlowLayoutActivity extends AppCompatActivity {
    private String[] data_string = {"赵丽颖","1987年10月16日出生于河北省廊坊市","中国内地影视女演员",
            "2006年","因获得雅虎搜星比赛冯小刚组冠军而进入演艺圈","同年在冯小刚执导的广告片《跪族篇》中担任女主角",
            "2011年","因在古装剧《新还珠格格》中饰演晴儿一角而被观众认识" ,"2013年","凭借古装剧《陆贞传奇》获得更多关注",
            "2014年10月","在第10届金鹰电视艺术节举办的投票活动中被选为“金鹰女神”","12月",
            "凭借都市爱情剧《杉杉来了获得第5届国剧盛典内地最具人气女演员奖"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flow);


        for (int i = 0; i < data_string.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(data_string[i]);
            textView.setBackground(getResources().getDrawable(R.drawable.bg_text));
            textView.setPadding(5, 5, 5, 5);

            ViewGroup.LayoutParams params = textView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginParams = null;
            if (params instanceof ViewGroup.MarginLayoutParams) {
                marginParams = (ViewGroup.MarginLayoutParams) params;
            }else {
                marginParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            marginParams.setMargins(10, 10, 10, 10);
            textView.setLayoutParams(marginParams);

//            if (textView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
//                Log.d("flowlayout", "是margin");
//                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
//                lp.setMargins(10, 10, 10, 10);
//                textView.setLayoutParams(lp);
//            }else {
//                Log.d("flowlayout", "不是margin");
//
//            }

            flowLayout.addView(textView, marginParams);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
