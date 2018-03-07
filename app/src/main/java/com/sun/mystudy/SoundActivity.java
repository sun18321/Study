package com.sun.mystudy;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class SoundActivity extends AppCompatActivity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mSoundPool.stop(currentSound);
                    break;
            }
        }
    };


    private Button mBtn_first;
    private Button mBtn_second;
    private Button mBtn_third;
    private Button mBtn_fourth;
    private SoundPool mSoundPool;
    private int mSound_success;
    private int mSound_faild;
    private int mSound_bet;
    private int mSound_water;
    private int currentSound;
    private int mSound_bet_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        initSound();
        init();
    }

    private void initSound() {
        mSoundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 0);
        mSound_success = mSoundPool.load(this, R.raw.guesssuccesssound, 0);
        mSound_faild = mSoundPool.load(this, R.raw.guessnosound,0);
        mSound_bet = mSoundPool.load(this, R.raw.redpocketsound, 0);
        mSound_water = mSoundPool.load(this, R.raw.winflygoldsound, 0);
//        mSound_bet_new = mSoundPool.load(this, R.raw.ove_bet, 0);

    }

    private void init() {
        mBtn_first = (Button) findViewById(R.id.btn_first);
        mBtn_second = (Button) findViewById(R.id.btn_second);
        mBtn_third = (Button) findViewById(R.id.btn_third);
        mBtn_fourth = (Button) findViewById(R.id.btn_fourth);

        mBtn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSound = mSoundPool.play(mSound_bet_new, 1, 1, 1, 0, 1);
                Log.d("sound_success", "" + currentSound);
//                mHandler.sendEmptyMessageDelayed(0, 5000);
            }
        });

        mBtn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSound = mSoundPool.play(mSound_faild, 1, 1, 1, -1, 1);
                Log.d("sound_failed", "" + currentSound);
                mHandler.sendEmptyMessageDelayed(0, 5000);
            }
        });

        mBtn_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.release();

                System.out.println(mSoundPool);

            }
        });

    }
}
