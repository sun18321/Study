package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sun.dialog.LoginDialog;
import com.sun.dialog.MyDialogFragment;

public class DialogFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);

        findViewById(R.id.first).setOnClickListener(this);
        findViewById(R.id.second).setOnClickListener(this);
        findViewById(R.id.third).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first:
                MyDialogFragment fragment = new MyDialogFragment(R.layout.dialogfragment_contentiew);
                fragment.show(getFragmentManager(), "dialog");
                break;
            case R.id.second:
                MyDialogFragment fragment1 = new MyDialogFragment(R.layout.dialogfragment_login);
                fragment1.show(getFragmentManager(),"fragment1");
                break;
            case R.id.third:
                LoginDialog loginDialog = new LoginDialog();
                loginDialog.show(getFragmentManager(), "login");
                break;
        }

    }
}
