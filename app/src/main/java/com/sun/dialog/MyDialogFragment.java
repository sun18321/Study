package com.sun.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sun.mystudy.R;

/**
 * Created by stephensun on 2017/5/3.
 */
public class MyDialogFragment extends DialogFragment {
    private static int mLayout;

//    public MyDialogFragment(int layout) {
//        this.layout = layout;
//    }

    public static MyDialogFragment newInstance(int layout) {
        mLayout = layout;
        return new MyDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(mLayout, container, false);
        return view;
    }


}
