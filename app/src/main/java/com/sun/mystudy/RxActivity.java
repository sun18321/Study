package com.sun.mystudy;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

    private final String LOG_RX = "rxjava";
    private final String TEXT_MAIN2MAIN = "main2main:";
    private final String TEXT_SON2MAIN = "son2main:";

    @BindView(R.id.main2main)
    Button mMain2main;
    @BindView(R.id.son2main)
    Button mSon2main;
    @BindView(R.id.operation)
    Button mOperation;
    @BindView(R.id.zip)
    Button mZip;
    @BindView(R.id.flowable)
    Button mFlowable;
    @BindView(R.id.read)
    Button mRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.main2main, R.id.son2main, R.id.operation, R.id.zip, R.id.flowable, R.id.read})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main2main:
                main2main();
                break;
            case R.id.son2main:
                son2main();
                break;
            case R.id.operation:
                rxOperation();
                break;
            case R.id.zip:
                zip();
                break;
            case R.id.flowable:
                flowable();
                break;
            case R.id.read:

                break;
        }
    }

    private void readText() {
        AssetManager assetManager = getApplicationContext().getAssets();
        try {
            InputStream inputStream = assetManager.open("sanguo.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            reader.readLine()
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void flowable() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> flowableEmitter) throws Exception {

                Log.d(LOG_RX, "上游:" + flowableEmitter.requested());

//                flowableEmitter.onNext(1);
//                flowableEmitter.onNext(2);
//                flowableEmitter.onNext(3);
//                flowableEmitter.onNext(4);
//                flowableEmitter.onNext(5);
//                flowableEmitter.onNext(6);
//                flowableEmitter.onComplete();
//                for (int i = 0; i < 140; i++) {
//                    flowableEmitter.onNext(i);
//                }

            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            private Subscription mSubscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                mSubscription = subscription;
//                if (mSubscription != null) {
//                    subscription.request(1);
//                }
                Log.d(LOG_RX, "subscription");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(LOG_RX, "flowable:" + integer);
//                if (integer != 4) {
                mSubscription.request(1);
//                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                Log.d(LOG_RX, "complete");
            }
        });
    }

    private void zip() {
        Observable<Integer> observableOne = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                Log.d(LOG_RX, 1 + "");
                observableEmitter.onNext(2);
                Log.d(LOG_RX, 2 + "");
                observableEmitter.onComplete();
                Log.d(LOG_RX, "complete");
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observableTwo = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("one");
                Log.d(LOG_RX, "one");
                observableEmitter.onNext("two");
                Log.d(LOG_RX, "two");
                observableEmitter.onNext("three");
                Log.d(LOG_RX, "three");
                observableEmitter.onComplete();
                Log.d(LOG_RX, "complete");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observableOne, observableTwo, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                Log.d(LOG_RX, s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void rxOperation() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(5);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer + 100 + "--map";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                Log.d(LOG_RX, s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    //子线程耗时操作,主线程接受
    private void son2main() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(5);
                Log.d(LOG_RX, "发送线程:" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                logAndThread(integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void main2main() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
                observableEmitter.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                Log.d(LOG_RX, "onsubscribe");

            }

            @Override
            public void onNext(Integer integer) {
                logAndThread(integer);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(LOG_RX, "error");
            }

            @Override
            public void onComplete() {
                Log.d(LOG_RX, "oncomplete");
            }
        };

        observable.doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(LOG_RX, "doOnNext");
            }
        }).subscribe(observer);

//链式写法
//        observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
//
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable disposable) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
//
//            }
//        }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//
//            }
//        });
    }

    private void logAndThread(int i) {
        Log.d(LOG_RX, "num:" + i + "--Thread:" + Thread.currentThread().getName());
    }
}
