package com.sun.mystudy;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sun.sql.Car;
import com.sun.sql.MySqlBase;
import com.sun.sql.Person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;

public class SaveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        Person person = (Person) getIntent().getSerializableExtra("person");
        Log.d("wusuowei", person.getName() + "--" + person.getAge() + "--" + person.isMale());

        Car car = getIntent().getParcelableExtra("car");
        Log.d("wusuowei", car.getName() + "--" + car.getPrice() + "--" + car.isQuick());


        findViewById(R.id.document).setOnClickListener(new View.OnClickListener() {

            private BufferedWriter mBufferedWriter;
            private FileOutputStream mFileOutputStream;

            @Override
            public void onClick(View v) {
                try {
                    //MODE_PRIVATE 会覆盖原有文件内容    MODE_APPEND 存在就往文件中追加内容，不存在就创建

                    mFileOutputStream = openFileOutput("document", MODE_APPEND);
                    mBufferedWriter = new BufferedWriter(new OutputStreamWriter(mFileOutputStream));
                    mBufferedWriter.newLine();
                    mBufferedWriter.write("nishidashabi");
                    mBufferedWriter.write("\n");
                    mBufferedWriter.write("nijiushidashabi");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (mBufferedWriter != null) {
                        try {
                            mBufferedWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {

            private StringBuilder mSb;
            private BufferedReader mBufferedReader;

            @Override
            public void onClick(View v) {
                try {
                    FileInputStream inputStream = openFileInput("document");
                    mBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    mSb = new StringBuilder();
                    String line = "";
                    while ((line = mBufferedReader.readLine()) != null) {
                        mSb.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (mBufferedReader != null) {
                        try {
                            mBufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.d("save_doc", mSb.toString());
            }
        });

        findViewById(R.id.sql_create).setOnClickListener(new View.OnClickListener() {

            private MySqlBase mMySqlBase;

            @Override
            public void onClick(View v) {
                mMySqlBase = new MySqlBase(SaveDataActivity.this, "BookStore.db", null, 2);
                mMySqlBase.getWritableDatabase();
            }
        });

        findViewById(R.id.sql_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySqlBase mySqlBase = new MySqlBase(SaveDataActivity.this, "BookStore.db", null, 3);
                mySqlBase.getWritableDatabase();
            }
        });

        findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySqlBase mySqlBase = new MySqlBase(SaveDataActivity.this, "BookStore.db", null, 3);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "水浒传");
                contentValues.put("author", "施耐庵");
                contentValues.put("price", "25.38");
                contentValues.put("pagers", "388");
                SQLiteDatabase db = mySqlBase.getWritableDatabase();
                db.insert("Book", null, contentValues);
            }
        });

        findViewById(R.id.retrieve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySqlBase mySqlBase = new MySqlBase(SaveDataActivity.this, "BookStore.db", null, 3);
                SQLiteDatabase db = mySqlBase.getWritableDatabase();


            }
        });
    }
}
