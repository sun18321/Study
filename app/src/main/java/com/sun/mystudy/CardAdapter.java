package com.sun.mystudy;

import android.content.Context;
import android.content.res.ObbInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by sun on 2017/11/8.
 */

public class CardAdapter extends BaseAdapter {
    private String[] arr = {"http://video.51aso.cn/cover/195459571711080858238.jpg/320","http://video.51aso.cn/cover/159420281711080636539.jpg/320"
            ,"http://video.51aso.cn/cover/118143801711080611539.jpg/320","http://video.51aso.cn/cover/118143801711080607422.jpg/320"};
    private Context mContext;

    public CardAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_card, parent, false);
            holder = new CardHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.card_image);

            ImageLoader.getInstance().displayImage(arr[position], holder.image);
            convertView.setTag(holder);

        } else {
            holder = (CardHolder) convertView.getTag();
        }

        return convertView;
    }

    class CardHolder {
        ImageView image;
    }
}
