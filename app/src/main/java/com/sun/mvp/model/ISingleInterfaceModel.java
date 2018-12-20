package com.sun.mvp.model;

import com.sun.mvp.interfaces.Callback;

public interface ISingleInterfaceModel extends IModel {
    void getData(int curPage, Callback callback);
}
