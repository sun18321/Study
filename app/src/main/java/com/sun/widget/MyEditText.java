package com.sun.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by sun on 2017/8/31.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText implements TextWatcher {
    private EditListener mListener;

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mListener != null) {
            mListener.onChange(s);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void setEditListener(EditListener listener) {
        mListener = listener;
    }


    public interface EditListener {
        void onChange(CharSequence text);
    }
}
