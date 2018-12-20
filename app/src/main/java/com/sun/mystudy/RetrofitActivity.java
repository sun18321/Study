package com.sun.mystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.sun.http.DataBean;
import com.sun.http.NetService;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    private final String TAG = "retrofit";

    @BindView(R.id.retrofit)
    Button mRetrofit;
    @BindView(R.id.data)
    TextView mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        Log.d(TAG, "main thread" + Thread.currentThread());
    }

    @OnClick(R.id.retrofit)
    public void onViewClicked() {
////        .client可不加
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.douban.com/v2/").client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        NetService netService = retrofit.create(NetService.class);
//        Call<DataBean> call = netService.getSearchBook("金瓶梅", null, 0, 1);
//        call.enqueue(new Callback<DataBean>() {
//            @Override
//            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
//                Log.d(TAG, response.body().getBooks().get(0).getAlt_title());
//            }
//
//            @Override
//            public void onFailure(Call<DataBean> call, Throwable t) {
//
//            }
//        });


        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https:api.douban.com/v2/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        NetService netService = retrofit.create(NetService.class);
        netService.getRXSearchBook("金瓶梅", null, 0, 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DataBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DataBean dataBean) {
                Log.d(TAG, dataBean.getBooks().get(0).getAlt_title());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
