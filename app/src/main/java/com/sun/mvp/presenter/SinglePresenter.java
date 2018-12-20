package com.sun.mvp.presenter;

import com.sun.mvp.base.BasePresenter;
import com.sun.mvp.contract.SingleInterfaceContract;

public class SinglePresenter extends BasePresenter<SingleInterfaceContract.View>
implements SingleInterfaceContract.Presenter{

    @Override
    public void getData(int curPage) {

    }
}
