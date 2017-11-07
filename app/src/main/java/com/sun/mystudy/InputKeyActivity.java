package com.sun.mystudy;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.sun.util.Utils;
import com.sun.widget.InputTextDialog;

public class InputKeyActivity extends AppCompatActivity implements InputTextDialog.OnTextSendListener {

    private LinearLayout mLl_move;
    private LinearLayout mLl_fill;
    private ObjectAnimator mHideAnim;
    private ObjectAnimator mShowAnim;
    private EditText mEdit;
    InputTextDialog mInputTextMsgDialog;
    ImageView showMsg;
    private ListView mListview;
    private int mList_x;
    private int mList_y;
    private int mList_height;
    private ScrollView mLl_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_key);

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

//        AndroidBug5497Workaround.assistActivity(this);

        initView();
    }

    private void initView() {
        mLl_move = (LinearLayout) findViewById(R.id.ll_move);
        mLl_fill = (LinearLayout) findViewById(R.id.ll_fill);
        mEdit = (EditText) findViewById(R.id.edit);
        mLl_edit = (ScrollView) findViewById(R.id.ll_edit);

        findViewById(R.id.btn_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHideAnim.start();
            }
        });

        findViewById(R.id.btn_soft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                inputMethodManager.showSoftInput(mEdit, 0);
                mEdit.requestFocus();
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mEdit, 0);

                mLl_edit.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mLl_edit.smoothScrollTo(0, mLl_edit.getHeight());
                        mLl_edit.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });

                System.out.println("soft");
            }
        });

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShowAnim.start();
            }
        });

        findViewById(R.id.btn_fill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLl_fill.getVisibility() == View.VISIBLE) {
                    mLl_fill.setVisibility(View.GONE);
                } else if (mLl_fill.getVisibility() == View.GONE) {
                    mLl_fill.setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.btn_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUI();
            }
        });

        mHideAnim = ObjectAnimator.ofFloat(mLl_move, "translationY", 0, Utils.dip2px(this, 217));
        mShowAnim = ObjectAnimator.ofFloat(mLl_move, "translationY", Utils.dip2px(this, 217), 0);

        mListview = (ListView) findViewById(R.id.list);
        mListview.setAdapter(new NewAdapter());

    }




    private void initUI() {
        mInputTextMsgDialog = new InputTextDialog(this, R.style.InputDialog);
        mInputTextMsgDialog.setmOnTextSendListener(this);
        showInputMsgDialog();
//        showMsg = (ImageView) findViewById(R.id.btn_message_input);
//        showMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showInputMsgDialog();
////                showMsg.setVisibility(View.GONE);
//            }
//        });
    }


    private void showInputMsgDialog() {
        WindowManager windowManager = getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams lp = mInputTextMsgDialog.getWindow().getAttributes();
        lp.width = (dm.widthPixels); //设置宽度
        mInputTextMsgDialog.getWindow().setAttributes(lp);
        mInputTextMsgDialog.setCancelable(true);
        mInputTextMsgDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mInputTextMsgDialog.show();


        mInputTextMsgDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
//                Log.d(TAG, "------onDismiss------");
//                showMsg.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int[] arr = new int[2];
        mListview.getLocationOnScreen(arr);
        mList_x = arr[0];
        mList_y = arr[1];
        mList_height = mListview.getHeight();

    }

    @Override
    public void onTextSend(String msg, boolean tanmuOpen) {

    }

    class NewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(InputKeyActivity.this);
            textView.setText("" + position);
            return textView;
        }
    }

}
