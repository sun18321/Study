package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.sun.bean.CreateRoomBean;
import com.sun.bean.ExampleBean;
import com.sun.bean.JsonBean;
import com.sun.bean.JsonBean2;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonActivity extends AppCompatActivity {
    public static final String address = "http://192.168.16.43:8080/example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        call();
    }

    private void call() {
        System.out.println("走了call方法");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder().url(address).get().build();

//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String json = response.body().string();
//                        System.out.println("josn数据" + json);
//                    }
//                });


                try {
                    Response response = client.newCall(request).execute();
                    String json = response.body().string();
                    System.out.println("json--" + json);

                    Gson gson = new Gson();
//                    ExampleBean exampleBean = gson.fromJson(json, ExampleBean.class);
//                    String type = exampleBean.getType();
//                    ExampleBean.ContentBean content = exampleBean.getContent();

//                    boolean b = json.contains("content");
//                    System.out.println("包含" + b);


                    JsonBean2 jsonBean = gson.fromJson(json, JsonBean2.class);
                    System.out.println("内容" + jsonBean.getContent());

                    CreateRoomBean createRoomBean = gson.fromJson(json, CreateRoomBean.class);
                    System.out.println("status" + createRoomBean.getStatus() + "----" + "data" + createRoomBean.getData());


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

}
