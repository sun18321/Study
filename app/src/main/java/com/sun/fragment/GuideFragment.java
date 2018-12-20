package com.sun.fragment;

import android.opengl.ETC1;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sun.mystudy.R;

public class GuideFragment extends Fragment {

    public static GuideFragment newInstance(int pageIndex) {
        Bundle args = new Bundle();
        args.putInt("key", pageIndex);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmrnt_guide, container, false);
        TextView tv= (TextView) view.findViewById(R.id.tv_num);
        int pageIndex = getArguments().getInt("key", 0) + 1;
        tv.setText(pageIndex + "");
        return view;
    }
}
