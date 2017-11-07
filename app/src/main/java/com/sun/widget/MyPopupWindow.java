package com.sun.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.sun.mystudy.CompanyActivity;
import com.sun.mystudy.R;

/**
 * Created by sun on 2017/11/3.
 */

public class MyPopupWindow {
    private Context mContext;
    private View mView;
    private PopupWindow mPopupWindow;
    private Window mWindow;
    private final WindowManager.LayoutParams mAttr;

    public MyPopupWindow(Context context, View view, Window window) {
        mContext = context;
        mView = view;
        mWindow = window;
        mAttr = mWindow.getAttributes();

        mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setContentView(mView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mAttr.alpha = 1.0f;
                mWindow.setAttributes(mAttr);
            }
        });
    }

    public View findViewById(int id) {
        return mView.findViewById(id);
    }

    public Context backContext() {
        return mContext;
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            mAttr.alpha = 0.5f;
            mWindow.setAttributes(mAttr);
            mPopupWindow.showAtLocation(parent, gravity, x, y);
        }
    }

    public boolean isShowing() {
        if (mPopupWindow != null) {
            return mPopupWindow.isShowing();
        } else {
            return false;
        }
    }

    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }
}
