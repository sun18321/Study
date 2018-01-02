package com.sun.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sun.mystudy.R;

/**
 * Created by sun on 2017/12/13.
 */

public class FirstFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_first, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();


    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.first_recycler);
        mToolbar = (Toolbar) mView.findViewById(R.id.first_toolbar);

    }
}
