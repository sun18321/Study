package com.sun.mystudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by stephensun on 2017/3/21.
 */
public class CommonRecyclerviewAdapter extends RecyclerView.Adapter<CommonRecyclerviewAdapter.CommonViewHolder> {
    private Context mContext;
    private int layout;
    private int[] arr;

    private RecyclerClickListener mListener;

    public CommonRecyclerviewAdapter(Context context, int layout, int[] arr) {
        mContext = context;
        this.layout = layout;
        this.arr = arr;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = new CommonViewHolder(LayoutInflater.from(mContext).inflate(layout, parent, false));
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        Glide.with(mContext).load(arr[position]).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }

    class CommonViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public CommonViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public interface RecyclerClickListener {
        void onClick(int position);
    }

    public void setRecyclerListener(RecyclerClickListener listener) {
        mListener = listener;
    }
}






