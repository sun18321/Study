package com.sun.dialog;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sun.mystudy.R;

/**
 * Created by stephensun on 2017/5/2.
 */
public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public MyDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final MyDialog dialog = new MyDialog(context, R.style.Dialog);
            View view = inflater.inflate(R.layout.dialog_normal_layout, null);
            dialog.addContentView(view,new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((TextView) view.findViewById(R.id.title)).setText(title);
            if (positiveButtonText != null) {
                ((Button) view.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) view.findViewById(R.id.positiveButton)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            }else {
                view.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }

            if (negativeButtonText != null) {
                ((Button) view.findViewById(R.id.negativeButton)).setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) view.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                ((Button) view.findViewById(R.id.negativeButton)).setVisibility(View.GONE);
            }
            if (message != null) {
                ((TextView) view.findViewById(R.id.message)).setText(message);
            }
            if (contentView != null) {
                ((LinearLayout)view.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout)view.findViewById(R.id.content)).addView(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            dialog.setContentView(view);
            return dialog;
        }
    }
}
