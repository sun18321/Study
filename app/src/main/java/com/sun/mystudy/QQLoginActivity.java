package com.sun.mystudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class QQLoginActivity extends AppCompatActivity {

    private Tencent mTencent;
    private QQinterface mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);

       init();
    }

    private void init(){

        mTencent = Tencent.createInstance("1105974650", this.getApplicationContext());
        mListener = new QQinterface();



        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mTencent.isSessionValid()) {
                mTencent.login(QQLoginActivity.this, "login", mListener);
            }
        }
    });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTencent.logout(QQLoginActivity.this);
            }
        });
    }

    class QQinterface implements IUiListener{

        @Override
        public void onComplete(Object o) {
            Log.d("qq", "授权成功");
            Log.d("qq", o.toString());
        }

        @Override
        public void onError(UiError uiError) {
            Log.d("qq", "授权出错");
        }

        @Override
        public void onCancel() {

            Log.d("qq", "授权取消");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Tencent.onActivityResultData(requestCode, resultCode, data, mListener);
    }
}
