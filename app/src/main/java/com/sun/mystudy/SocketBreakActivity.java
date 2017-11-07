package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SocketBreakActivity extends AppCompatActivity {

    private Button mBtn_break;
    private GameSocket mGameSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_break);

        init();
    }

    private void init() {
        mBtn_break = (Button) findViewById(R.id.btn_break);
        if (mGameSocket == null) {
            mGameSocket = new GameSocket(this, "139.199.65.19", 9501, "14321521", "14321521");
        }
        mBtn_break.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameSocket.releaseSocket();
                mGameSocket.connect2server();
            }
        });

    }
}
