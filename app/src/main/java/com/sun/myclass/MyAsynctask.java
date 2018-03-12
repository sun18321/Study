package com.sun.myclass;

import android.os.AsyncTask;

/**
 * Created by stephensun on 2018/3/12.
 */

public class MyAsynctask extends AsyncTask<Void, Integer, Boolean> {

    //后台任务执行之前调用(类似显示对话框等等)
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //所有都在子线程运行,完成后return返回(asynctask的第三个参数)
    //通过publishProgress通知onProgressUpdate来更新界面
    @Override
    protected Boolean doInBackground(Void... voids) {

//        publishProgress();

        return null;

    }

    //对界面进行更新
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    //后台任务(doinbackground)return时调用
    //主线程
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
