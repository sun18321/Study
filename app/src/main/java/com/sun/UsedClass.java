package com.sun;

/**
 * Created by sun on 2017/8/9.
 */

public class UsedClass {
    int i = 1009;
    ClassInterface mInterface;

    public void setInterface(ClassInterface mInterface) {
        this.mInterface = mInterface;
    }

    public interface ClassInterface {
        void onInterface(int i);
    }
}
