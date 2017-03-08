package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EmptyRadiobuttonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_radiobutton);

        init();

    }

    private void init() {
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmptyRadiobuttonActivity.this, "选择了第" + radioGroup.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
