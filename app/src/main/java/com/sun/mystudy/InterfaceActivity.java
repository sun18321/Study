package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sun.myinterface.CallbackInterface;
import com.sun.myinterface.ClassCallBack;
import com.sun.myinterface.ClassListener;
import com.sun.myinterface.InterfaceClass;
import com.sun.myinterface.TestInterface;

public class InterfaceActivity extends AppCompatActivity implements CallbackInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        initView();
    }

    private void initView() {
        final ClassCallBack callBack = new ClassCallBack();
        callBack.setClassListener(new ClassListener() {
            @Override
            public void callback(int num) {
                Log.d(InterfaceClass.LOG_INTERFACE, "类回调" + num);
            }
        });

        callBack.setInterfaceListener(this);


        findViewById(R.id.btn_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterfaceClass in = new InterfaceClass();
                in.first();
                in.second(true);


            }
        });

        findViewById(R.id.btn_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.classBack();
                callBack.interfaceCallback();
            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new ClickListener());

        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };


//        onClickListener.onClick();

        InterfaceClass interfaceClass = new InterfaceClass();

        new TestInterface() {
            @Override
            public void first() {

            }

            @Override
            public void second(boolean b) {
                Log.d(InterfaceClass.LOG_INTERFACE, "new的接口");
            }
        };
        Log.d(InterfaceClass.LOG_INTERFACE, "initView走完了");

        ClickListener classListener = new ClickListener();
    }

    @Override
    public void callback() {
        Log.d(InterfaceClass.LOG_INTERFACE, "接口回调");
    }



    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }

}
