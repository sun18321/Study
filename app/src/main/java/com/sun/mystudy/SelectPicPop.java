package com.sun.mystudy;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.sun.widget.MyPopupWindow;

/**
 * Created by sun on 2017/11/3.
 */

public class SelectPicPop {
    private Context mContext;
    private MyPopupWindow mPopupWindow;
    private Button mBtn_album;
    private Button mBtn_camera;
    private View mBtn_cancel;
    private View mLocView;
    private final CompanyActivity mActivity;
    public static final int CODE_CHOOSE_PIC = 1001;

    public SelectPicPop(MyPopupWindow popupWindow, View locationView) {
        mPopupWindow = popupWindow;
        mLocView = locationView;
        mContext = mPopupWindow.backContext();
        mActivity = (CompanyActivity) mContext;
        initView();
    }

    private void initView() {
        mBtn_album = (Button) mPopupWindow.findViewById(R.id.btn_pop_album);
        mBtn_camera = (Button) mPopupWindow.findViewById(R.id.btn_pop_camera);
        mBtn_cancel = mPopupWindow.findViewById(R.id.btn_pop_cancel);

        mBtn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mActivity.startActivityForResult(intent,CODE_CHOOSE_PIC);
                mPopupWindow.dismiss();
            }
        });

        mBtn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

        mBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.showAtLocation(mLocView, Gravity.BOTTOM, 0, 0);
    }

    public void showPop(View view) {
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }



}
