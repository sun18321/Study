package com.sun.mystudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sun.bean.HomeBean;
import com.sun.okhttp.AddCookieIntercepter;
import com.sun.okhttp.CookiesManager;
import com.sun.okhttp.ReceiveCookieIntercepter;
import com.sun.widget.MyPopupWindow;

import java.io.IOException;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CompanyActivity extends AppCompatActivity {
    private final String image_url = "http://phone.hainantaohua.com/sms/chkcode?phone=18555556688";
    private String LOGIN_URL_SMS = "http://phone.hainantaohua.com/index/smslogin";
    private final String SERVER_URL = "http://phone.hainantaohua.com/";
    private final String getLiveListUrl = SERVER_URL + "live/hot";
    private final String user_home_url = SERVER_URL + "home";
    private final String uid = "18378762";
    private String LOGIN_URL = "http://phone.hainantaohua.com/index/login";

    private ImageView mIv_code;
    private EditText mEdit;
    private String mCode;
    private Gson mGson;
    private SelectPicPop mSelectPicPop;
    private WindowManager.LayoutParams mLp;
    private Window mWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        initView();
    }

    private void initView() {
        mGson = new Gson();

        mIv_code = (ImageView) findViewById(R.id.iv_code);
        mEdit = (EditText) findViewById(R.id.et_code);

//        mLp = getWindow().getAttributes();
        mWindow = getWindow();

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(new CookiesManager()).build();
                FormBody formBody = new FormBody.Builder().add("account", "18555556688")
                        .add("pwd", "bfd59291e825b5f2bbf1eb76569f8fe7")
                        .build();
                Request request = new Request.Builder().post(formBody).url(LOGIN_URL).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("company", "newcookie" + response.body().string());
                    }
                });

            }
        });

        findViewById(R.id.code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new ReceiveCookieIntercepter(CompanyActivity.this))
                        .addInterceptor(new AddCookieIntercepter(CompanyActivity.this)).build();
                FormBody formBody = new FormBody.Builder().add("account", "18555556688")
                        .add("pwd", "bfd59291e825b5f2bbf1eb76569f8fe7")
                        .build();
                Request request = new Request.Builder().post(formBody).url(LOGIN_URL).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("company", response.body().string());
                    }
                });
            }
        });

        findViewById(R.id.pull_live).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new AddCookieIntercepter(CompanyActivity.this))
                        .addInterceptor(new ReceiveCookieIntercepter(CompanyActivity.this)).build();
                Request request = new Request.Builder().get().url(getLiveListUrl).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("company", "live" + response.body().string());

                    }
                });
            }
        });

        findViewById(R.id.sp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences cookie = CompanyActivity.this.getSharedPreferences("cookie", MODE_PRIVATE);
                Set<String> sp_set = cookie.getStringSet("cookie", null);
                if (sp_set != null) {
                    for (String s : sp_set) {
                        Log.d("company", "for_set" + s);
                    }
                } else {
                    Log.d("company", "sp里set是空的");
                }
            }
        });

        findViewById(R.id.head_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(new CookiesManager()).build();

                Request request = new Request.Builder().get().url(user_home_url + "?u=" + uid).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        Log.d("company", json);
                        final HomeBean homeBean = mGson.fromJson(json, HomeBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(CompanyActivity.this).load(homeBean.getUserinfo().getFace()).into(mIv_code);
                            }
                        });
                    }
                });

            }
        });

        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(CompanyActivity.this).inflate(R.layout.pop_select_pic, null);
                MyPopupWindow window = new MyPopupWindow(CompanyActivity.this, view, mWindow);
                if (mSelectPicPop == null) {
                    Log.d("company", "pop空");
                    mSelectPicPop = new SelectPicPop(window, mIv_code);
                } else {
                    mSelectPicPop.showPop(mIv_code);
                    Log.d("company", "pop show");
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("company", "request" + requestCode + "---" + "result" + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SelectPicPop.CODE_CHOOSE_PIC:
                    Uri uri = data.getData();

                    break;
            }
        }
    }
}
