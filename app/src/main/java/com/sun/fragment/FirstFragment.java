package com.sun.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
        Log.d("fragment", "onCreateView");
        mView = inflater.inflate(R.layout.fragment_second, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        Log.d("fragment", "onViewCreate");
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.first_recycler);
        mToolbar = (Toolbar) mView.findViewById(R.id.first_toolbar);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("fragment", "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("fragment", "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("fragment", "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("fragment", "onDestroy");
    }
}
