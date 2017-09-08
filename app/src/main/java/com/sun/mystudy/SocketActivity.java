package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.sun.bean.CountdownBean;
import com.sun.bean.CreateRoomBean;
import com.sun.bean.GameResultBean;
import com.sun.bean.SendOpenRoomBean;
import com.sun.bean.SendStartGameBean;
import com.sun.bean.StartGameBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class SocketActivity extends AppCompatActivity {
    private static final String ip = "139.199.184.20";
    private static final int port = 9501;

//    private static final String ip = "139.199.184.20";
//    private static final int port = 9501;

    private Socket mSocket;
    private OutputStream mOutputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        findViewById(R.id.re_connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean serverClose = isServerClose(mSocket);
                System.out.println("是否断线" + serverClose);
            }
        });

        findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketThread socket = new SocketThread();
                socket.start();
            }
        });

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendOpenRoomBean openRoomBean = new SendOpenRoomBean();
                openRoomBean.cmd = "create";
                openRoomBean.roomId = "13423256";
                openRoomBean.uid = "13423256";
                Gson gson = new Gson();
                final String json = gson.toJson(openRoomBean);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //发送到服务器
                            System.out.println("json数据" + json);
                            if (json == null) {
                                return;
                            }
                            if (mSocket == null) {
                                return;
                            }
                            mOutputStream = mSocket.getOutputStream();
                            // 步骤2：写入需要发送的数据到输出流对象中
                            String s = json + "\\n";
                            System.out.println("json拼接" + s);
                            mOutputStream.write((s).getBytes("utf-8"));
                            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                            mOutputStream.flush();
                            System.out.println("发送成功了");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        findViewById(R.id.send_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendStartGameBean startGameBean = new SendStartGameBean();
                startGameBean.cmd = "new";
                startGameBean.roomId = "13423256";
                startGameBean.game = "niu";
                startGameBean.uid = "13423256";
                Gson gson = new Gson();
                final String json = gson.toJson(startGameBean);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (mOutputStream != null) {
                            System.out.println("开始游戏的json" + json);
                            try {
                                mOutputStream.write((json + "\\n").getBytes("utf-8"));
                                mOutputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();
            }
        });

    }

    public Boolean isServerClose(Socket socket){
        try{
            if (socket != null) {
                System.out.println("判断断线");
                socket.sendUrgentData(0xFF);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            }
            return false;
        }catch(Exception se){
            return true;
        }
    }


    class SocketThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                System.out.println("开始socket连接");
                mSocket = new Socket(ip, port);

                ReadThread readThread = new ReadThread();
                readThread.start();

            } catch (UnknownHostException e) {
                System.out.println("unknowhost出错" + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("io出错"+e.getMessage());
            }
        }
    }

    class ReadThread extends Thread {
        @Override
        public void run() {
            InputStream inputStream = null;
            try {
                inputStream = mSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ReadThread获取socket的流");
                try {
                    System.out.println("获取socket的流");
//
//                    InputStreamReader isr = new InputStreamReader(inputStream);
//                    System.out.println("new input");
//                    BufferedReader bufferedReader = new BufferedReader(isr);
//                    System.out.println("new buffer");
//                    String reply = null;
////                    boolean b = bufferedReader.readLine() != null;
////                    System.out.println("循环的对错" + b);
//                    while ((reply = bufferedReader.readLine()) != null) {
//                        System.out.println("我在循环里");
//                         System.out.println("得到的数据" + reply);
//                    }



                    InputStreamReader isr = new InputStreamReader(inputStream);
                    char[] charsbuffer = new char[1024];
                    int length = 0 ;
                    int total = 0 ;
                    StringBuffer reply = new StringBuffer();
                    boolean countdownEnd = false;
                    boolean createSuccess = false;
                    boolean startSuccess = false;
                    int len = 0 ;
                    while (length != -1 ){
                        length = isr.read(charsbuffer,0,9);
                        if (length == 9){
                           String head1 = new String(charsbuffer,0,5);
                            if ("head:".equals(head1)) {
                                Log.d("ReadThread","head1" +head1);
                                String head2 = new String(charsbuffer,5,4);
                                Log.d("ReadThread", "head2"+head2);

                                len = Integer.parseInt(head2);
                                while (total < len){
                                    length = isr.read(charsbuffer,total,len);
                                    total += length;
                                }
                                String s = new String(charsbuffer,0,len);
                                Log.d("ReadThread", "read :" +s);
                                System.out.println(s);
                                total = 0 ;
                                len = 0 ;
                            }
                        }

//
////                        reply.append(charsbuffer,total,length);
//////                        total += length ;
////                        String s = reply.toString();
////                        System.out.println(reply.toString());
////                        String json = reply.toString();
////                        Gson gson = new Gson();
////                        if (!createSuccess) {
////                            CreateRoomBean createRoomBean = gson.fromJson(json, CreateRoomBean.class);
////                            if (createRoomBean.getStatus() == 0) {
////                                System.out.println("创建房间成功");
////                                createSuccess = true;
////                            } else if (createRoomBean.getStatus() == 1) {
////                                System.out.println("创建status=1" + createRoomBean.getData().getRoomId());
////                            }
////                        } else if (!startSuccess) {
////                            StartGameBean startGameBean = gson.fromJson(json, StartGameBean.class);
////                            if (startGameBean.getStatus() == 0) {
////                                System.out.println("开始游戏成功");
////                                startSuccess = true;
////                            }
////                        } else {
////                            if (countdownEnd) {
////                                GameResultBean gameResultBean = gson.fromJson(json, GameResultBean.class);
////                                System.out.println("谁赢了" + gameResultBean.getData().getResult().getWin());
////                                List<String> card1 = gameResultBean.getData().getResult().getDetail().get(0).getCards();
////                                for (int i = 0; i < card1.size(); i++) {
////                                    System.out.println("第一个人的牌" + card1.get(i));
////                                }
////                                List<String> card2 = gameResultBean.getData().getResult().getDetail().get(1).getCards();
////                                for (int i = 0; i < card1.size(); i++) {
////                                    System.out.println("第二个人的牌" + card2.get(i));
////                                }
////                                List<String> card3 = gameResultBean.getData().getResult().getDetail().get(2).getCards();
////                                for (int i = 0; i < card1.size(); i++) {
////                                    System.out.println("第三个人的牌" + card1.get(i));
////                                }
////                                countdownEnd = false;
////                                startSuccess = false;
////                            } else if (!countdownEnd) {
////                                CountdownBean countdownBean = gson.fromJson(json, CountdownBean.class);
////                                System.out.println("倒计时" + countdownBean.getData().getTimecount());
////                                if (countdownBean.getData().getTimecount() == 1) {
////                                    countdownEnd = true;
////                                }
////                            }
////                        }
////                        reply.delete(0, reply.length());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("err" + e.toString());
                }
        }
    }
}
