package com.sun.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.mystudy.R;

import java.util.zip.Inflater;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mFirst;
    private MyDialog.Builder mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        init();
    }

    private void init() {
        mFirst = (Button) findViewById(R.id.first);
        mFirst.setOnClickListener(this);
        findViewById(R.id.second).setOnClickListener(this);
        findViewById(R.id.third).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first:
                doFirst();
                break;
            case R.id.second:
                doSecond();
                break;
            case R.id.third:
                doThird();
                break;
        }
    }

    private void doThird() {
        View view = LayoutInflater.from(DialogActivity.this).inflate(R.layout.dialog_random, null);
        if (mDialog == null) {
            mDialog = new MyDialog.Builder(this);
        }
        mDialog.setTitle("我是第三个")
                .setContentView(view)
                .create().show();
    }

    private void doSecond() {
        View view = LayoutInflater.from(DialogActivity.this).inflate(R.layout.dialog_input, null);
        final EditText text_name = (EditText) view.findViewById(R.id.user_name);
        final EditText text_pwd = (EditText) view.findViewById(R.id.pwd);
        if (mDialog == null) {
            mDialog = new MyDialog.Builder(this);
        }
        mDialog.setTitle("第二个")
               .setContentView(view)
                .setPositiveButton("输入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String name = text_name.getText().toString();
                        String pwd = text_pwd.getText().toString();
                        Toast.makeText(DialogActivity.this, "name:" + name + "-----" + "pwd:" + pwd, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("不输", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(DialogActivity.this, "啥都没干", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();

    }

    private void doFirst() {
        if (mDialog == null) {
            mDialog = new MyDialog.Builder(this);
        }
        mDialog.setTitle("第一个")

            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                }
            })
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        }).create().show();

    }
}
