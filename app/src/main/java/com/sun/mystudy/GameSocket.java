package com.sun.mystudy;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.sun.bean.BindBean;
import com.sun.bean.CountdownBean;
import com.sun.bean.CreateRoomBean;
import com.sun.bean.DataTypeBean;
import com.sun.bean.SendBindBean;
import com.sun.bean.SendCloseRoomBean;
import com.sun.bean.SendCreateRoomBean;
import com.sun.bean.SendEnterRoomBean;
import com.sun.bean.SendLeaveBean;
import com.sun.bean.SendStartGameBean;
import com.sun.bean.StartGameBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by sun on 2017/8/17.
 */

public class GameSocket {
    private String ip;
    private int port;
    private String mRoomId;
    private String mUid;
    private Socket mSocket;
    private OutputStream mOutputStream;
    private ReadThread mReadThread;
    private NiuInterface mNiuInterface;
    private DiceInterface mDiceInterface;
    private Gson mGson = new Gson();

    private boolean isHost;
    private final String TYPE_CREATE = "create";
    private final String TYPE_GAME_NEW = "game_new";
    private final String TYPE_GAME_START = "game_poll";
    private final String TYPE_GAME_RESULT = "game_result";
    private final String TYPE_GAME_BET = "game_bet"; //用于统计每个牛的下注多少
    private final String TYPE_ENTER = "enter";
    private final String TYPE_BIND = "bind";
    private final String TYPE_LEAVE = "leave";
    private final String TYPE_BET = "bet"; //用于接受服务器返回玩家下注是否成功
    private final String TYPE_BONUS = "game_bonus";
    private final String TYPE_GAME_CLOSE = "game_close";
    private final String TYPE_NEW = "new";
    private final String TYPE_CLOSEROOM = "closeroom";
    private static boolean hasRead = false;
    public static final String GAME_NIU = "niu";
    public static final String GAME_DICE = "dice";

    private final int HANDLER_HEART = 1010;
    private final int HANDLER_ENTER_ROOM = 1006;
    private final int HANDLER_CREATE_ROOM = 1003;
    private final int HANDLER_CLOSE_ROOM = 1011;
    private final int HANDLER_LEAVE_ROOM = 1012;
    private final int HANDLER_LOAD_GAME = 1013;
    private final int HANDLER_DELAY = 1014;
    private final int HANDLER_RELEASE = 1015;

    //是否断线过
    private boolean haveBreak = false;
    public String CURRENT_GAME = null;
    private boolean threadOver = false;
    public boolean firstLoad = true;
    public boolean isGaming = false;
    private String game_type;
    private String game_body;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case HANDLER_CREATE_ROOM:
                    createRoom();
                    break;
                case HANDLER_ENTER_ROOM:
                    enterRoom();
                    break;
                case HANDLER_HEART:
                    Boolean serverClose = isServerClose(mSocket);
                    if (serverClose) {
                        haveBreak = true;
                        mSocket = null;
                        mReadThread = null;
                        releaseSocket();
                        connect2server();
                    } else {
                        mHandler.sendEmptyMessageDelayed(HANDLER_HEART, 5000);
                    }
                    break;
                case HANDLER_CLOSE_ROOM:
                    releaseSocket();
                    break;
                case HANDLER_LEAVE_ROOM:
                    releaseSocket();
                    break;
                case HANDLER_LOAD_GAME:
                    firstLoad = false;
                    mHandler.sendEmptyMessageDelayed(HANDLER_DELAY, 1500);
                    break;
                case HANDLER_DELAY:
                    deliverMessage(game_type, game_body);
                    break;
                case HANDLER_RELEASE:
                    firstLoad = true;
                    deliverMessage("close_handler", "");
                    break;
            }
        }
    };
    private InputStream mInputStream;

    public void releaseSocket() {
        if (mSocket != null) {
            try {
//                mSocket.shutdownInput();
//                mSocket.shutdownOutput();
//                InputStream inputStream = mSocket.getInputStream();
//                OutputStream outputStream = mSocket.getOutputStream();
                mInputStream.close();
                mOutputStream.close();
                mSocket.close();
                mSocket = null;
                mHandler.removeMessages(HANDLER_HEART);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public GameSocket(Context context, String ip, int port, String roomId, String uid) {
        this.ip = ip;
        this.port = port;
        this.mRoomId = roomId;
        this.mUid = uid;

        if (mRoomId != null && mRoomId.equals(mUid)) {
            isHost = true;
        } else {
            isHost = false;
        }
        connect2server();
    }

    public void createRoom() {
        SendCreateRoomBean createRoomBean = new SendCreateRoomBean();
        createRoomBean.cmd = "create";
        createRoomBean.roomId = mRoomId;
        createRoomBean.uid = mUid;
        final String json = mGson.toJson(createRoomBean);
        send2servicer(json);
//        firstCrete = false;
    }

    public void enterRoom() {
        SendEnterRoomBean sendEnterRoomBean = new SendEnterRoomBean();
        sendEnterRoomBean.cmd = "enter";
        sendEnterRoomBean.roomId = mRoomId;
        sendEnterRoomBean.uid = mUid;
        final String json = mGson.toJson(sendEnterRoomBean);
        send2servicer(json);
    }

    //发送心跳包
    public Boolean isServerClose(Socket socket){
        System.out.println("我是心跳");
        try{
            if (socket != null) {
                socket.sendUrgentData(0xFF);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            }
            return false;
        }catch(Exception se){
            return true;
        }
    }

    public void connect2server() {
        if (mSocket == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("socket是空的");
                    try {
                            mSocket = new Socket(ip, port);
                            System.out.println("创建socket连接");

//                        if (mReadThread == null) {
                            System.out.println("thread是空的");
                            hasRead = true;
                            mReadThread = new ReadThread();
                            mReadThread.start();
                            bindServer();
//                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            }
    }

    private void bindServer(){
        SendBindBean sendBindBean = new SendBindBean();
        if (isHost) {
            sendBindBean.uid = mRoomId;
        } else {
            sendBindBean.uid = mUid;
        }
        sendBindBean.cmd = "bind";
//        String token = SharedPreferenceTool.getInstance().getString(SharedPreferenceTool.GAME_TOKEN, "");
        String token = "310c6cfbbf47022086d079467772a474";
        if (!"".equals(token)) {
            sendBindBean.token = token;
        }
        System.out.println("绑定的token" + token);
        final String json = mGson.toJson(sendBindBean);
        send2servicer(json);
    }

    public void startGame() {
        SendStartGameBean startGameBean = new SendStartGameBean();
        startGameBean.cmd = "new";
        startGameBean.roomId = mRoomId;
        startGameBean.game = CURRENT_GAME;
        startGameBean.uid = mUid;
        final String json = mGson.toJson(startGameBean);
        send2servicer(json);
    }

    public void leaveRoom() {
        threadOver = true;
        closeHandler();
        SendLeaveBean sendLeaveBean = new SendLeaveBean();
        sendLeaveBean.cmd = "leave";
        sendLeaveBean.roomId = mRoomId;
        sendLeaveBean.uid = mUid;
        String json = mGson.toJson(sendLeaveBean);
        send2servicer(json);
    }

    public void closeRoom() {
        threadOver = true;
        closeHandler();
        SendCloseRoomBean sendCloseRoomBean = new SendCloseRoomBean();
        sendCloseRoomBean.cmd = "closeroom";
        sendCloseRoomBean.roomId = mRoomId;
        final String json = mGson.toJson(sendCloseRoomBean);
        send2servicer(json);
    }

    public void gameClose() {
        SendCloseRoomBean sendCloseRoomBean = new SendCloseRoomBean();
        sendCloseRoomBean.cmd = "close";
        sendCloseRoomBean.roomId = mRoomId;
        final String json = mGson.toJson(sendCloseRoomBean);
        send2servicer(json);
    }

    private void closeHandler() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }

    }

    public void send2servicer(final String json) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mSocket != null) {
                        mOutputStream = mSocket.getOutputStream();
                    }
                    String s = json + "\\n";
                    System.out.println("json拼接" + s);
                    if (mOutputStream != null) {
                        mOutputStream.write((s).getBytes("utf-8"));
                        // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                        mOutputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Socket getSocket() {
        return mSocket;
    }


    public void setNiuInterface(NiuInterface niuInterface) {
        this.mNiuInterface = niuInterface;
    }

    public void setDiceInterface(DiceInterface diceInterface) {
        this.mDiceInterface = diceInterface;
    }

    public interface NiuInterface {
        void niuMessage(String type, String body);
    }

    public interface DiceInterface {
        void diceMessage(String type, String body);
    }

    class ReadThread extends Thread {
        Gson gson = null;
        @Override
        public void run() {
            mInputStream = null;
            try {
                mInputStream = mSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ReadThread获取socket的流");
            while (mSocket != null && !threadOver) {
                try {
                    InputStreamReader isr = new InputStreamReader(mInputStream);
                    char[] charsbuffer = new char[1024];
                    int length = 0;
                    int total = 0;
                    int len = 0;
                    String json = null;
                    StringBuffer reply = new StringBuffer();
                    boolean countdownEnd = false;
                    boolean createSuccess = false;
                    boolean startSuccess = false;

                    while (length != -1) {
                        length = isr.read(charsbuffer, 0, 9);
                        if (length == 9) {
                            String head = new String(charsbuffer, 0, 5);
                            if ("head:".equals(head)) {
                                String head_len = new String(charsbuffer, 5, 4);
                                len = Integer.parseInt(head_len);
                                System.out.println("json长度" + len);
                            }
                            while (total < len) {
                                length = isr.read(charsbuffer, total, len);
                                total += length;
                            }
                            json = new String(charsbuffer, 0, len);
                            total = 0;
                            len = 0;
                        }
                        System.out.println("json数据" + json);
                        if (gson == null) {
                            gson = new Gson();
                        }
                        if (json != null) {
                            try {
                                DataTypeBean typeBean = gson.fromJson(json, DataTypeBean.class);
                                String type = typeBean.getType();
                                System.out.println("返回类型---" + type);
                                String body = typeBean.getBody();
                                System.out.println("返回内容" + body);
                                switch (type) {
                                    case TYPE_CREATE:
                                        CreateRoomBean createRoomBean = gson.fromJson(body, CreateRoomBean.class);
                                        if (createRoomBean.getStatus() == 0) {
                                            System.out.println("创建房间成功");
                                        } else {
                                            mHandler.sendEmptyMessage(HANDLER_ENTER_ROOM);
                                        }
                                        break;
                                    case TYPE_BIND:
                                        BindBean bindBean = gson.fromJson(body, BindBean.class);
                                        if (bindBean.getStatus() == 0) {
                                            System.out.println("绑定成功");
                                            if (haveBreak) {
                                                mHandler.sendEmptyMessage(HANDLER_ENTER_ROOM);
                                            } else {
                                                if (isHost) {
                                                    mHandler.sendEmptyMessage(HANDLER_CREATE_ROOM);
                                                } else {
                                                    mHandler.sendEmptyMessage(HANDLER_ENTER_ROOM);
                                                }
                                            }
                                            mHandler.sendEmptyMessageDelayed(HANDLER_HEART, 5000);
                                        } else {
                                            if (isHost && haveBreak) {
                                                mHandler.sendEmptyMessage(HANDLER_CREATE_ROOM);
                                            }
                                        }
                                        break;
                                    case TYPE_CLOSEROOM:
                                        mHandler.sendEmptyMessage(HANDLER_CLOSE_ROOM);
                                        break;
                                    case TYPE_GAME_NEW:
                                        isGaming = true;
                                        StartGameBean startGameBean = gson.fromJson(body, StartGameBean.class);
//                                        CURRENT_GAME = startGameBean.getData().getGame();
                                        if (firstLoad) {
                                            mHandler.sendEmptyMessage(HANDLER_LOAD_GAME);
                                            game_type = type;
                                            game_body = body;
                                        } else {
                                            deliverMessage(type,body);
                                        }
                                        break;
                                    case TYPE_GAME_CLOSE:
                                        mHandler.sendEmptyMessage(HANDLER_RELEASE);
                                        break;
                                    case TYPE_GAME_START:
                                        if (CURRENT_GAME == null) {
                                            CountdownBean countdownBean = mGson.fromJson(body, CountdownBean.class);
//                                            CURRENT_GAME = countdownBean.getData().getGame();

                                            if (firstLoad) {
                                                mHandler.sendEmptyMessage(HANDLER_LOAD_GAME);
                                                game_type = type;
                                                game_body = body;
                                            }
                                        }
                                        deliverMessage(type, body);
                                        break;
                                    default:
                                        if (type != null && body != null && CURRENT_GAME != null) {
                                            deliverMessage(type, body);
                                        }
                                }
                            } catch (Exception e) {
                                System.out.println("gson崩了" + e.getMessage());
                            }
                        }
                        reply.delete(0, reply.length());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("err" + e.toString());
                }
            }
            System.out.println("线程结束了");
        }
    }

    public void deliverMessage(String type, String body) {
        if (CURRENT_GAME != null) {
        switch (CURRENT_GAME) {
            case GAME_NIU:
                if (mNiuInterface != null) {
                    mNiuInterface.niuMessage(type, body);
                }
                break;
            case GAME_DICE:
                if (mDiceInterface != null) {
                    mDiceInterface.diceMessage(type, body);
                }
                break;
        }
        }
    }
}
