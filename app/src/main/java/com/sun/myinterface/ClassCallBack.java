package com.sun.myinterface;

/**
 * Created by sun on 2017/10/30.
 */

public class ClassCallBack {
    private ClassListener mClassListener;
    private CallbackInterface mCallbackInterface;

    public void setClassListener(ClassListener classListener) {
        mClassListener = classListener;
    }

    public void classBack() {
        mClassListener.callback(1000);
    }

    public void setInterfaceListener(CallbackInterface callbackInterface) {
        mCallbackInterface = callbackInterface;
    }

    public void interfaceCallback() {
        mCallbackInterface.callback();
    }
}
