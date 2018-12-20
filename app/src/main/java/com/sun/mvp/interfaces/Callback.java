package com.sun.mvp.interfaces;

public interface Callback<K, V> {
    void onSucess(K data);

    void onFail(V data);
}
