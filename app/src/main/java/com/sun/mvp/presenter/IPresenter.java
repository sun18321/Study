package com.sun.mvp.presenter;

import com.sun.mvp.view.IView;

public interface IPresenter<T extends IView> {
    void attachView(T view);

    void detachView();

    boolean isViewAttached();
}
