package com.sun.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sun.mvp.presenter.IPresenter;
import com.sun.mvp.view.IView;

public abstract class BaseMVPActivity<T extends IPresenter> extends AppCompatActivity implements IView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPresenter();
        init();
    }

    private void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract T createPresenter();

    protected abstract void init();
}
