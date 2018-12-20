package com.sun.mvp.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sun.mvp.base.BaseMVPActivity;
import com.sun.mvp.bean.ArticleListBean;
import com.sun.mvp.contract.SingleInterfaceContract;
import com.sun.mvp.presenter.SinglePresenter;
import com.sun.mystudy.R;

public class SingleRequestActivity extends BaseMVPActivity<SinglePresenter>
implements SingleInterfaceContract.View{

    private Button mButton;
    private TextView mTextView;

    @Override
    protected SinglePresenter createPresenter() {
        return new SinglePresenter();
    }

    @Override
    protected void init() {
        setContentView(R.layout.activity_guide);
        mButton = (Button) findViewById(R.id.btn_request);
        mTextView = (TextView) findViewById(R.id.text_data);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getData(0);
            }
        });
    }

    @Override
    public void showArticleSuccess(ArticleListBean bean) {

    }

    @Override
    public void showArticleFail(String errorMsg) {

    }
}
