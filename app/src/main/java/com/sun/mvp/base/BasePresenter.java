package com.sun.mvp.base;

import com.sun.mvp.presenter.IPresenter;
import com.sun.mvp.view.IView;

public class BasePresenter<T extends IView> implements IPresenter<T> {
    protected T mView;
    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }
}
