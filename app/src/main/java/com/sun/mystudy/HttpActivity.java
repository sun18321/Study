package com.sun.mystudy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.http.ParseJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity {

    private final String JSON_DATA = "[{\"id\":\"5\",\"version\":\"5.5\",\"name\":\"Clash of Clans\"},{\"id\":\"6\",\"version\":\"7.0\",\"name\":\"Clash of Clans\"},{\"id\":\"7\",\"version\":\"3.5\",\"name\":\"Clash of Clans\"}]";
    private final String LOG_JSON = "json_object";


    @BindView(R.id.btn_one)
    Button mBtnOne;
    @BindView(R.id.scroll_text)
    TextView mScrollText;
    @BindView(R.id.okhttp)
    Button mOkhttp;
    @BindView(R.id.json_object)
    Button mJsonObject;
    @BindView(R.id.gson)
    Button mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }

    }

    @OnClick({R.id.btn_one, R.id.okhttp,R.id.json_object,R.id.gson})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                myHttp();
                break;
            case R.id.okhttp:
                okhttp();
                break;
            case R.id.json_object:
                parseJsonWithJsonobject();
                break;
            case R.id.gson:
                parseJsonWithGson();
                break;
        }
    }

    private void parseJsonWithGson() {
        Gson gson = new Gson();

        List<ParseJson> list = gson.fromJson(JSON_DATA, new TypeToken<List<ParseJson>>(){}.getType());


    }

    private void parseJsonWithJsonobject() {
        try {
            JSONArray jsonArray = new JSONArray(JSON_DATA);
            Log.d(LOG_JSON, "array长度" + jsonArray.length());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                Object version = jsonObject.get("version");
                String data = id + "--" + name + "--" + version;
                if (i == 0) {
                    sb.append(data);
                } else {
                    sb.append("--").append(data);
                }
                mScrollText.setText(sb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void okhttp() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url("http://www.123u.com").build();
//                    Response response = client.newCall(request).execute();
//                    final String string = response.body().string();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mScrollText.setText(string);
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.123u.com").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mScrollText.setText(string);
                    }
                });

            }
        });


    }

    private void requestPermission() {
        String[] permission = {Manifest.permission.INTERNET};
        ActivityCompat.requestPermissions(this, permission, 1001);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void netHttp() {
        final String realUrl = "http://www.baidu.com";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("阿西吧");

                    //1.通过在 URL 上调用 openConnection 方法创建连接对象
                    URL url = new URL(realUrl);
                    //此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection,
                    //故此处最好将其转化为HttpURLConnection类型的对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    //2.处理设置参数和一般请求属性
                    //2.1设置参数
                    //可以根据请求的需要设置参数
                    conn.setRequestMethod("GET"); //默认为GET 所以GET不设置也行
                    conn.setUseCaches(false);
                    conn.setConnectTimeout(5000); //请求超时时间

                    //2.2请求属性
                    //设置通用的请求属性 更多的头字段信息可以查阅HTTP协议
                    conn.setRequestProperty("accept", "*/*");
                    conn.setRequestProperty("connection", "Keep-Alive");

                    //3.使用 connect 方法建立到远程对象的实际连接。
                    conn.connect();

                    //4.远程对象变为可用。远程对象的头字段和内容变为可访问。
                    //4.1获取HTTP 响应消息获取状态码
                    if (conn.getResponseCode() == 200) {
                        //4.2获取响应的头字段
                        Map<String, List<String>> headers = conn.getHeaderFields();
                        System.out.println("头字段:" + headers); //输出头字段

                        //4.3获取响应正文
                        BufferedReader reader = null;
                        StringBuffer resultBuffer = new StringBuffer();
                        String tempLine = null;

                        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        while ((tempLine = reader.readLine()) != null) {
                            resultBuffer.append(tempLine);
                        }
                        System.out.println("正文:" + resultBuffer);
                        reader.close();
                    }

                } catch (MalformedURLException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void myHttp() {
        new Thread(new Runnable() {
            StringBuilder response = new StringBuilder();

            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://www.123u.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));

                    // 响应code
                    int code = connection.getResponseCode();
                    Log.d("http", "响应头" + code);

                    String message = connection.getResponseMessage();
                    Log.d("http", "message----" + message);

                    //正文
                    String line;
                    while ((line = reader.readLine()) != null) {
                        Log.d("http", "进入循环");
                        response.append(line);
                    }
                    Log.d("http", "网络请求结束");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mScrollText.setText(response.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }
}
