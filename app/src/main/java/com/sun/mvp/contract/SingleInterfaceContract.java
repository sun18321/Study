package com.sun.mvp.contract;

import com.sun.mvp.bean.ArticleListBean;
import com.sun.mvp.view.IView;

public interface SingleInterfaceContract{

    interface View extends IView {
        void showArticleSuccess(ArticleListBean bean);

        void showArticleFail(String errorMsg);
    }

    interface Presenter {
        void getData(int curPage);
    }

}
