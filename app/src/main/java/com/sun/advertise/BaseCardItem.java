package com.sun.advertise;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by sun on 2017/9/27.
 */

public class BaseCardItem<T> extends Fragment {

    protected static final String ARGUMENTS_HANDLER = "cards_handler";
    protected static final String ARGUMENTS_DATA = "cards_data";
    protected static final String ARGUMENTS_POSITION = "cards_position";

    protected Context mContext;

    @CardViewPager.TransformerMode
    int currentMode;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void bindHandler(CardHandler<T> handler) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            setArguments(bundle);
        }
        bundle.putSerializable(ARGUMENTS_HANDLER, handler);
    }
}
