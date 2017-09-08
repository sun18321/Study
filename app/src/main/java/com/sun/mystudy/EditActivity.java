package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
    }

    private void init() {
        mEdit = (EditText) findViewById(R.id.edit);
        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("edit", "before");
                System.out.println("before");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("edit", "onChange");
                System.out.println("onChange");

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("edit", "after");
                System.out.println("after");
            }
        });

    }
}
