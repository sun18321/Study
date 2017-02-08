package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sun.util.MobileConfig;
import com.sun.util.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestActivity extends AppCompatActivity {
    private String MY_URl = "http://phone.hainantaohua.com/pay";
    private String MY_LOGIN = "http://phone.hainantaohua.com/index/login";
    private String MY_ATTEN = "http://phone.hainantaohua.com/atten/sync?liveuid=17993425";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

//        init();
//        init2();
        init3();
        init4();
        String login =
                "http://phone."
                        + "hainantaohua"
                        + "/index/sync?system_name="
                        + ""
                        + "&system_version="
                        + ""
                        + "&platform="
                        + ""
                        + "&carrier="
                        + ""
                        + "&udid="
                        + ""
                        + "&app_version="
                        + ""
                        + "&app_channel="
                        + ""
                        + "&app=AULive";
    }

    private void init4() {
        Request request = new Request.Builder().url(MY_ATTEN).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                System.out.println(json);
            }
        });
    }

    private void init3() {
        Request request = new Request.Builder().url(MY_URl).addHeader("Im_token","nAI/cSze7iMU9TnB8CRgrqckDaXYeMDvmFz6f9EggkhUJbZgcwGMRQ75tGAbRSUUlI5RAHSCIj8PDhgys93+xQ==").build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                System.out.println(json);
            }
        });
    }

    private void init2() {
        Toast.makeText(RequestActivity.this, "init2", Toast.LENGTH_SHORT).show();
       RequestBody requestBody  = new FormBody.Builder()
               .add("account","18555556688")
               .add("pwd", Utils.encryption("asd123"))
               .build();

        Request request = new Request.Builder().url(MY_LOGIN).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                System.out.println(json);
            }
        });
    }

    private void init() {
        StringBuilder sb = new StringBuilder(MY_URl);
        MobileConfig config = MobileConfig.getMobileConfig(this);
        Toast.makeText(RequestActivity.this, "init", Toast.LENGTH_SHORT).show();

        Request request = null;
        try {
            request = new Request.Builder().url(MY_LOGIN)
                    .addHeader("account","18555556688")
                    .addHeader("pwd", Utils.encryption("asd123"))
                    .addHeader("udid", config.getIemi())
                    .addHeader("system_name", config.getOS())
                    .addHeader("system_version", config.getMobileOsVersion())
                    .addHeader("platform", URLEncoder.encode(config.getMobileModel(), "utf-8"))
                    .addHeader("carrier", URLEncoder.encode(config.getSimOperatorName(), "utf-8"))
                    .addHeader("app_version", config.getPkgVerCode() + "")
                    .addHeader("app_channel", config.getCurrMarketName())
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(RequestActivity.this, "访问失败了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Toast.makeText(RequestActivity.this, "访问成功了", Toast.LENGTH_SHORT).show();
                String json = response.body().string();
                System.out.println(json);

            }
        });
    }
}
